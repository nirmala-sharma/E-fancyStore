package com.example.demo.enittiy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class product {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int product_id;
private String name;

@ManyToOne(fetch=FetchType.LAZY,cascade= CascadeType.MERGE)
@JoinColumn(name="id",referencedColumnName="id")
private Category category;

/*
 * @OneToOne
 * 
 * @JoinColumn(name="id",referencedColumnName="id") private Rating rating;
 */
private double price;
private int quantity;
private String description;
@Lob
private String imageName;
public int getProduct_id() {
	return product_id;
}
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getImageName() {
	return imageName;
}
public void setImageName(String imageName) {
	this.imageName = imageName;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}


	
}












