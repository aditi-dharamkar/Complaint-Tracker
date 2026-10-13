package com.complainttracker.complainttracker.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.complainttracker.complainttracker.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
	Optional<Admin> findByEmail(String email);
}
