package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.RatingRepository;
import com.example.demo.enittiy.Rating;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.ProductService;
import com.example.demo.services.categoryservices;

@Controller
public class HomeController {

	@Autowired
	categoryservices categoryservice;
	
	@Autowired 
	RatingRepository ratingrepo;
	
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
	

	@GetMapping("/rate")
	public String rateProduct(){
		return "rating";
	}
	@PostMapping("/ratesubmit")
	public String submitRating(@ModelAttribute("rating") Rating rate,Model model) {
		ratingrepo.save(rate);

		return "redirect:/shop";
	}
	@GetMapping("/viewRating")
	
	public String viewRating(Model model) {
		model.addAttribute("rating", ratingrepo.findAll());
return "showrate";
	}
	
	
}
