package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class ratingDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String rate;
	private String description;
private int productId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getRate() {
	return rate;
}
public void setRate(String rate) {
	this.rate = rate;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}


}
