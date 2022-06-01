package com.example.demo.enittiy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;



@Entity

public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
    private String rate;
    private String description;
    
	/*
	 * @OneToOne(cascade= CascadeType.MERGE,fetch=FetchType.LAZY) private product
	 * product;
	 */
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
	
	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rating(int id, String rate, String desciption) {
		super();
		this.id = id;
		this.rate = rate;
		this.description = desciption;
	}
	
    
    
}
