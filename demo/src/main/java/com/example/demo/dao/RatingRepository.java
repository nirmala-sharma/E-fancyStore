
 package com.example.demo.dao;
 
 import java.util.List;
import java.util.Optional;

 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.enittiy.Rating; 
import com.example.demo.enittiy.product;
 public interface RatingRepository extends JpaRepository<Rating,Integer>{


		/*
		 * List<Rating> findAllByproduct_Id(int id);
		 */		 
			/*
			 * @Query("select sum(c.rate) from (select c.pro,sum(c.rate) from Rating c where c.pro=id group by c.pro) as sumrating "
			 * ) public int totalRateByProduct_id(int id);
			 */
 }
