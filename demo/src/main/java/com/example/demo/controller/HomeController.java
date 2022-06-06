package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.RatingRepository;
import com.example.demo.dto.productDto;
import com.example.demo.dto.ratingDTO;
import com.example.demo.enittiy.Rating;
import com.example.demo.enittiy.product;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ProductService;
import com.example.demo.services.RatingService;
import com.example.demo.services.categoryservices;

@Controller
public class HomeController {

	@Autowired
	categoryservices categoryservice;
	
	@Autowired 
	RatingService ratingService;
	
	@Autowired
	ProductService productservice;
 
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		

		return "index";
	}
	@GetMapping("/shop")
	public String shop(Model model)
	{
		model.addAttribute("categories", categoryservice.getAllCategory());
	    model.addAttribute("products", productservice.getAllProduct());
		model.addAttribute("cartCount",GlobalData.cart.size());
			return "shop";	
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id) {
	model.addAttribute("categories", categoryservice.getAllCategory());
	model.addAttribute("products", productservice.getAllProductByCategoryId(id));
	model.addAttribute("cartCount",GlobalData.cart.size());

		
		
		return "shop";	
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable int id) {
		model.addAttribute("product", productservice.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());

			
		return "viewProduct";
	}
	
	

	@GetMapping("/search")
	public String SearchByProductName(Model model,@Param("key") String key) {
	model.addAttribute("categories", categoryservice.getAllCategory());
	model.addAttribute("products", productservice.searchByProductName(key));
	model.addAttribute("cartCount",GlobalData.cart.size());

		
		
		return "shop";	
	}
	

	@GetMapping("/rate/{id}")
	public String rateProduct(Model model,@PathVariable int id){
		model.addAttribute("product", productservice.getProductById(id).get());
		model.addAttribute("ratings", new ratingDTO());
		
		
		
		return "rating";
	}
	
	@PostMapping("/rating")
	
	public String submitRating(@ModelAttribute("ratings") ratingDTO  rate) {
	
		Rating rating=new Rating();
		rating.setId(rate.getId());
		rating.setDescription(rate.getDescription());
		rating.setRate(rate.getRate());
		rating.setPro(productservice.getProductById(rate.getProductId()).get());
		
		ratingService.addRating(rating);

		return "redirect:/shop";
	}
	@GetMapping("/viewRating")
	
	public String viewRating(Model model) {
	
				model.addAttribute("rating", ratingService.getAllRating());
return "showrate";
	}
	
	//to show the total rating by product_id 
	
	/*
	 * @GetMapping("viewRate/{id}") public String viewRating(Model
	 * model,@PathVariable int id) {
	 */
			/*
			 * model.addAttribute("viewrate",ratingService.totalRatingByProductId1(id));
			 */	
			/*
			 * return "test"; }
			 */	 
	
}
