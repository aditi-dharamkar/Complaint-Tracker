package com.complainttracker.complainttracker.service;

import org.springframework.stereotype.Service;
import com.complainttracker.complainttracker.model.Admin;
import com.complainttracker.complainttracker.repository.AdminRepository;

@Service
public class AdminService {
	private final AdminRepository adminRepository;
	public AdminService(AdminRepository adminRepository) {
		this.adminRepository=adminRepository;
	}
	public boolean login(String email,String password) {
		Admin admin=adminRepository.findByEmail(email).orElse(null);
		if(admin!=null && admin.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
}
