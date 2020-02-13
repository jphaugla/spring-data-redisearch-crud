package com.jphaugla.repository;

import com.jphaugla.domain.ProductEntity;
import com.rnbwarden.redisearch.client.RediSearchClient;
import com.rnbwarden.redisearch.autoconfiguration.RediSearchClientAutoConfiguration;
import com.rnbwarden.redisearch.client.SearchResults;
import com.rnbwarden.redisearch.entity.RediSearchEntity;
import com.rnbwarden.redisearch.entity.RediSearchField;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.DependsOn;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Repository
@DependsOn("RediSearchAutoConfiguration")

public class  ProductRepository {
	@Autowired
	private RediSearchClient<ProductEntity> rediSearchClient;
	public void save(ProductEntity product) {
     	rediSearchClient.save(product);
	 }
	public void getByKey(String key) {
		rediSearchClient.findByKey(key);
	}
	public void delete(String key) {
		rediSearchClient.delete(key);
	}

	public List<ProductEntity> findByColumn1(String value) {
		Map<String, String> column1Map = new HashMap<String, String>() {
			{
				put(ProductEntity.COLUMN1, value);
			}};
		SearchResults<ProductEntity> searchResults = rediSearchClient.findByFields(column1Map);
        searchResults.getTotalResults();
        return rediSearchClient.deserialize(searchResults);
	}
	public List<ProductEntity> findByColumns(String column1Value, String column2Value) {
     	Map<String, String> fieldNameValues = new HashMap<String, String>() {
			{
			put(ProductEntity.COLUMN1, column1Value);
			put(ProductEntity.COLUMN2, column2Value);
			}};
		SearchResults<ProductEntity> searchResults = rediSearchClient.findByFields(fieldNameValues);
		searchResults.getTotalResults();
		return rediSearchClient.deserialize(searchResults);
	}
}
