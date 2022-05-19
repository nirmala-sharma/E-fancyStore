package com.example.demo.enittiy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;


@Entity
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer role_id;
	@Column(nullable=false,unique=true)
	private String name;
	@ManyToMany(mappedBy="roles")
	private List<User> users=new ArrayList<>();;
	public Integer getId() {
		return role_id;
	}
	public void setId(Integer id) {
		this.role_id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Role(Integer id, String name, List<User> users) {
		super();
		this.role_id = id;
		this.name = name;
		this.users = users;
	}
	

}
