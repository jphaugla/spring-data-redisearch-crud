package com.jphaugla.controller;

import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jphaugla.repository.ProductRepository;
import com.jphaugla.domain.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@DependsOn("RediSearchAutoConfiguration")

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("/save_product")
	public String saveProduct() {
		ProductEntity productEntity = new ProductEntity();
		productRepository.save(productEntity);
		productEntity = new ProductEntity();
		productRepository.save(productEntity);
		return "Done";
	}

	@GetMapping("/get_ProductEntity_first_last")
	public List<ProductEntity> getProductEntity(@RequestParam String firstName, @RequestParam String lastName) {
		return productRepository.findByColumn1("jason");
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
