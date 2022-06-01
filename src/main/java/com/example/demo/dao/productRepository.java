package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enittiy.product;

public interface productRepository extends JpaRepository<product,Integer>{
	List<product> findAllByCategory_Id(int id);

//	List<product> findAllById(int id);
	
	@Query("select p from product p where p.name like %?1%"
			+ "OR p.category like %?1%"
			+ "OR p.price like %?1%"
			+ "OR p.description like %?1%")	
	public List<product> findAllProduct(String key);

}
