package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import javax.persistence.criteria.Path;

import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.productDto;
import com.example.demo.enittiy.Category;
import com.example.demo.enittiy.Rating;
import com.example.demo.services.ProductService;
import com.example.demo.services.RatingService;
import com.example.demo.services.categoryservices;
import com.example.demo.enittiy.product;

@Controller
public class AdminController {
	public static String uploadDir = System.getProperty("user.dir")
			+ "/src/main/resources/static/productImages";
	@Autowired
	categoryservices servicescategory;
	@Autowired
	ProductService productservice;
     @Autowired
	RatingService ratingservice;
	/*
	 * @Autowired productDto productDTO;
	 */
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
//	Category section

	@GetMapping("/admin/categories")
	public String categories(Model model) {
		model.addAttribute("categories", servicescategory.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getcategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postcategory(@ModelAttribute("category") Category category) {
		servicescategory.addCategory(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategoryById(@PathVariable int id) {
		servicescategory.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCategoryById(@PathVariable int id, Model model) {
		Optional<Category> category = servicescategory.getCategoryById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
	}

	// product
	@GetMapping("/admin/products")
	public String product(Model model) {
		model.addAttribute("product", productservice.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String getproduct(Model model) {
		model.addAttribute("product", new productDto());
		model.addAttribute("categories", servicescategory.getAllCategory());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String saveProduct(@ModelAttribute("product") productDto productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {
		product products = new product();
		products.setProduct_id(productDTO.getProduct_id());
		products.setName(productDTO.getName());
		products.setCategory(servicescategory.getCategoryById(productDTO.getCategoryId()).get());

		products.setDescription(productDTO.getDescription());
		
		products.setPrice(productDTO.getPrice());
        String fileName; 
		  if(!file.isEmpty()) {
		  fileName=file.getOriginalFilename();
			  
		  java.nio.file.Path
		fileNameAndPath=Paths.get(uploadDir, fileName); 
		  Files.write(fileNameAndPath,
		 file.getBytes());
		 
		} else { fileName=imgName; }
		 
		products.setImageName(fileName);
		productservice.addProduct(products);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String deleteProductById(@PathVariable int id) {
		productservice.deleteProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable int id, Model model) {
		productDto productDTO = new productDto();
		product product = productservice.getProductById(id).get();
		productDTO.setProduct_id(product.getProduct_id());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setImageName(product.getImageName());
		model.addAttribute("categories", servicescategory.getAllCategory());
		model.addAttribute("product", productDTO);
		return "productsAdd";
	}

}
