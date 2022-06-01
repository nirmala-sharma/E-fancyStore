
 package com.example.demo.dao;
 
 import java.util.Optional;

 import org.springframework.data.jpa.repository.JpaRepository;
 
 import com.example.demo.enittiy.Rating; 
import com.example.demo.enittiy.product;
 public interface RatingRepository extends JpaRepository<Rating,Integer>{

		/*
		 * Optional<Rating> findByProduct_Id(int id);
		 */
 }
