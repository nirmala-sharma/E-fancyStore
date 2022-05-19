package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.enittiy.Category;

@Service
public class categoryservices {

	//category
	@Autowired
	CategoryRepository categoryrepo;
	public List<Category> getAllCategory(){
		return categoryrepo.findAll();
	}
	
	public void addCategory(Category category) {
		categoryrepo.save(category);
	}
	
	public void deleteCategoryById(int id) {
		categoryrepo.deleteById(id);
	}
	public Optional<Category> getCategoryById(int id) {
		return categoryrepo.findById(id);
	}
	


}
