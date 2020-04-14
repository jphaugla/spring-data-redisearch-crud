package com.jphaugla.controller;

import java.util.List;
import java.util.Map;

import com.jphaugla.domain.Brand;
import com.jphaugla.domain.SkuEntity;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jphaugla.repository.ProductRepository;
import com.jphaugla.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DependsOn("RediSearchAutoConfiguration")

@RestController
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/save_product")
	public String saveProduct() {
		logger.warn("entering save_Product");
		ProductEntity product1 = new ProductEntity("id123", "FALCON01", Brand.NIKE,
				List.of(new SkuEntity("f01", Map.of("color", "black", "price", "99.99")),
						new SkuEntity("f02", Map.of("color", "white", "price", "99.99"))));

		ProductEntity product2 = new ProductEntity("id234", "BLAZE-X", Brand.NIKE,
				List.of(new SkuEntity("b01", Map.of("color", "red", "price", "79.99")),
						new SkuEntity("b02", Map.of("color", "orange", "price", "79.99"))));
		productRepository.save(product1);
		productRepository.save(product2);
		logger.warn("leaving save_Product");
		return "Done";
	}


	@GetMapping("/get_Article")
	public List<ProductEntity> getProductEntity(@RequestParam String article) {
		return productRepository.findByArticleNumber(article);
	}

	@GetMapping("/put_records")
	public boolean putRecords(@RequestParam int max, @RequestParam String namePrefix, @RequestParam Brand brand) {
		return productRepository.saveProductsInRange(max, namePrefix, brand);
	}


	@PutMapping("/put_ProductEntity")
	public String putProductEntity(@RequestBody ProductEntity productEntity) {
		productRepository.save(productEntity);
		return "Done";
	}

	@DeleteMapping("/delete")
	public String deleteProductEntity(@RequestParam String id) {
		productRepository.delete(id);
		return "Done";
	}
}
