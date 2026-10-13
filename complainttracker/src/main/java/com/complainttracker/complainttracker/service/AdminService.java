package com.complainttracker.complainttracker.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.complainttracker.complainttracker.model.Admin;
import com.complainttracker.complainttracker.repository.AdminRepository;

@Service
public class AdminService {
	private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
	public AdminService(AdminRepository adminRepository,PasswordEncoder passwordEncoder) {
		this.adminRepository=adminRepository;
		this.passwordEncoder = passwordEncoder;
	}
	public boolean login(String email,String password) {
		Admin admin=adminRepository.findByEmail(email).orElse(null);
		if(admin==null || password==null ) {
			return false;
		}
		String storedPassword = admin.getPassword();
		if(storedPassword == null) {
		return false;
		}
		if (storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$")) {
            return passwordEncoder.matches(password, storedPassword);
        }
		if (storedPassword.equals(password)) {
            admin.setPassword(passwordEncoder.encode(password));
            adminRepository.save(admin);
            return true;
        }
		return false;

	}
}
