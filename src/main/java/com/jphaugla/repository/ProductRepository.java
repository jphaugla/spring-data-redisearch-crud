package com.jphaugla.repository;

import com.jphaugla.domain.Brand;
import com.jphaugla.domain.ProductEntity;
import com.rnbwarden.redisearch.client.RediSearchClient;
import com.rnbwarden.redisearch.client.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import static java.util.Collections.emptyList;

@Repository
public class ProductRepository {
	@Autowired
	private RediSearchClient<ProductEntity> productEntityRediSearchClient;
	public void save(ProductEntity product) {
     	productEntityRediSearchClient.save(product);
	 }
	public void getByKey(String key) {
		productEntityRediSearchClient.findByKey(key);
	}
	public void delete(String key) {
		productEntityRediSearchClient.delete(key);
	}

	public List<ProductEntity> findByArticleNumber(String value) {
		Map<String, String> column1Map = new HashMap<String, String>() {
			{
				put(ProductEntity.ARTICLE_NUMBER, value);
			}};
		SearchResults<ProductEntity> searchResults = productEntityRediSearchClient.findByFields(column1Map);
        searchResults.getTotalResults();
        return productEntityRediSearchClient.deserialize(searchResults);
	}
	public List<ProductEntity> findByColumns(String brand) {
     	Map<String, String> fieldNameValues = new HashMap<String, String>() {
			{
			put(ProductEntity.BRAND, brand);
			}};
		SearchResults<ProductEntity> searchResults = productEntityRediSearchClient.findByFields(fieldNameValues);
		searchResults.getTotalResults();
		return productEntityRediSearchClient.deserialize(searchResults);
	}
	public boolean saveProductsInRange(int max, String namePrefix, Brand brand) {

		IntStream.range(0, max)/*.parallel()*/.forEach(i -> {
			ProductEntity product = new ProductEntity("id" + i, namePrefix + i, brand, Collections.emptyList());
			productEntityRediSearchClient.save(product);
		});
		return true;
	}


}
