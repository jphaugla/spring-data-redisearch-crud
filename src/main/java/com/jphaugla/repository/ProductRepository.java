package com.jphaugla.repository;

import com.jphaugla.domain.ProductEntity;
import com.rnbwarden.redisearch.client.RediSearchClient;
import com.rnbwarden.redisearch.client.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<ProductEntity> findByColumn1(String value) {
		Map<String, String> column1Map = new HashMap<String, String>() {
			{
				put(ProductEntity.COLUMN1, value);
			}};
		SearchResults<ProductEntity> searchResults = productEntityRediSearchClient.findByFields(column1Map);
        searchResults.getTotalResults();
        return productEntityRediSearchClient.deserialize(searchResults);
	}
	public List<ProductEntity> findByColumns(String column1Value, String column2Value) {
     	Map<String, String> fieldNameValues = new HashMap<String, String>() {
			{
			put(ProductEntity.COLUMN1, column1Value);
			put(ProductEntity.COLUMN2, column2Value);
			}};
		SearchResults<ProductEntity> searchResults = productEntityRediSearchClient.findByFields(fieldNameValues);
		searchResults.getTotalResults();
		return productEntityRediSearchClient.deserialize(searchResults);
	}
}
