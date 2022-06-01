package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.enittiy.Role;
import com.example.demo.enittiy.User;
import com.example.demo.globalAccess.GlobalData;
import com.example.demo.services.EmailService;

@Controller
public class LoginController {

	Random random=new Random(1000);
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	UserRepository userRepository;
	
	Role role=new Role();
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		//every time when user login then the cart will be empty
	GlobalData.cart.clear();
		return "login";
		
	}
	@GetMapping("/register")
	public String register(){
		return "register";
		
	}
	@PostMapping("/register")
	public String registerHere(@ModelAttribute("user") User user,HttpServletRequest request) throws ServletException{
		String password=user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(password));
		List<Role> roles=new ArrayList<>();
		roles.add(roleRepository.findById(2).get());
		user.setRoles(roles);
		userRepository.save(user);
		request.login(user.getEmail(),password);
	    return "redirect:/";
	}
	@GetMapping("/forgotpassword")
	public String fogetPassword() {
		return "forgot_email_form";
	}
	
	@PostMapping("/send-otp")
	public String sendOtp(@RequestParam("email") String email,HttpSession session) {
		
		System.out.println("email"+email);
//		generating OTp of 4 digit
		int otp=random.nextInt(999999);
		System.out.println("otp"+otp);
		
//		code for sending otp 
		
		String subject="OTP from niru";
		String message="OTP="+otp+" ";
		String to=email;
		
		boolean flag=this.emailService.sendEmail(subject, message, to);
		if(flag) {
			session.setAttribute("otp",otp);
			session.setAttribute("email",email);
			
			return "verify_otp";
		}
		else {
//			m.addAttribute("message","check your email id!!");
			return "forgot_email_form";
			
		}
		
		
		
		
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam("otp") int otp ,HttpSession session)
	{
		int myOtp=(int) session.getAttribute("otp");
		String email=(String) session.getAttribute("email");
		User user=this.userRepository.getUserByEmail(email);
		
		if(myOtp==otp)
		{
			if(user==null) {
				session.setAttribute("message","User doesnot exists with this email!!" );
			return "forgot_email_form";
			
			}
			else {
			
			return "password_change_form";
		}
		}
		
		else {
			session.setAttribute("message","You have entered wrong otp ");
			return "verify_otp";
		}
		
	}
	
	@PostMapping("/NewPassword")
	public String changePassword(@RequestParam("Newpassword") String newPassword,HttpSession session)
	{
		String email=(String) session.getAttribute("email");
		User user=this.userRepository.getUserByEmail(email);
		user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		this.userRepository.save(user);
		return "redirect:/login";
		
		
	}
	
}
