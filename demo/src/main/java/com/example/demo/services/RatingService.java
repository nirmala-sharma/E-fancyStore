package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RatingRepository;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.Rating;

@Service
public class RatingService {

	@Autowired
	RatingRepository ratingrepo;
	public List<Rating> getAllRating()
	{
		return ratingrepo.findAll();
	}
	public void addRating(Rating rate)
		{
		
		ratingrepo.save(rate);
		
		}
	public Optional<Rating> getRatingById(int id) {
		return ratingrepo.findById(id);
	}
	


	/*
	 * public int totalRatingByProductId1(int productid) {
	 * 
	 * 
	 * return ratingrepo.totalRateByProduct_id(productid);
	 * 
	 * 
	 * 
	 * }
	 */
}



