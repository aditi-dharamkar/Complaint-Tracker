package com.complainttracker.complainttracker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complainttracker.complainttracker.service.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins="http://127.0.0.1:5500")
public class AdminController {
	
	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService=adminService;
	}
	@PostMapping("/login")
	public String login(@RequestBody AdminLoginRequest request) {
		boolean result=adminService.login(
				request.getEmail(), 
				request.getPassword()
				);
		        if(result) {
		        	return "Login successful";
		        }
		        return "Invalid email or password";
	}
	public static class AdminLoginRequest{
		private String email;
		private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	}

}
