package com.example.demo.services;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.productRepository;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.product;

@Service
public class ProductService {
 
	@Autowired
	productRepository productrepo;
	public List<product> getAllProduct()
	{
		return productrepo.findAll();
	}
	public void addProduct(product products)
		{
		
		productrepo.save(products);
		
		}
	public void deleteProductById(int id) {
		productrepo.deleteById(id);
	}
	public List<product> getAllProductByCategoryId(int id) {
		return productrepo.findAllByCategory_Id(id);
	}
	public Optional<product> getProductById(int id) {
		return productrepo.findById(id);
	}
	public List<product> searchByProductName(String key) {
 if(key!=null) {
	 try {
		return productrepo.findAllProduct(key);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
		return productrepo.findAll();
	}
}
