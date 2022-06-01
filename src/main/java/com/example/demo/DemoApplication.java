package com.example.demo;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.AdminController;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		new File(AdminController.uploadDir).mkdir();
		
//ApplicationContext context=
//(ApplicationContext) 
SpringApplication.run(DemoApplication.class, args);	
//UserRepository userRepository=((BeanFactory) context).getBean(UserRepository.class);
//	class1 user=new class1();
//	user.setName("niru");
//user.setCity("dhn");
//class1 user1=userRepository.save(user);
//	System.out.println(user1);
	}

}
