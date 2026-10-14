package com.complainttracker.complainttracker.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
import com.complainttracker.complainttracker.PublicComplaintResponse;

import com.complainttracker.complainttracker.model.Complaint;
import com.complainttracker.complainttracker.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")

public class ComplaintController {
	private final ComplaintService complaintService;

	public ComplaintController(ComplaintService complaintService) {
		this.complaintService = complaintService;
	}

	private void requireAdmin(HttpSession session) {
		if (!Boolean.TRUE.equals(
				session.getAttribute("adminAuthenticated"))) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED,
					"Admin login required");
		}
	}

	// Add complaint
	@PostMapping
	public Complaint addComplaint(@RequestBody Complaint complaint) {
		return complaintService.addComplaint(complaint);
	}

	@GetMapping("/public")
	public List<PublicComplaintResponse> getPublicComplaints() {
		return complaintService.getAllComplaints()
				.stream()
				.map(complaint -> new PublicComplaintResponse(
						complaint.getId(),
						complaint.getCategory(),
						complaint.getStatus(),
						complaint.getCreatedAt()))
				.collect(Collectors.toList());
	}

	// Get all complaints
	@GetMapping
	public List<Complaint> getAllComplaints(HttpSession session) {
		requireAdmin(session);
		return complaintService.getAllComplaints();
	}

	// Get complaint by Id
	@GetMapping("/{id}")
	public Complaint getComplaintById(@PathVariable Long id, HttpSession session) {
		requireAdmin(session);
		return complaintService.getComplaintByTd(id);
	}

	// Update complaint
	@PutMapping("/{id}")
	public Complaint updateComplaint(
			@PathVariable Long id,
			@RequestBody Complaint complaint,
			HttpSession session) {
		requireAdmin(session);
		return complaintService.updateComplaint(id, complaint);
	}

	// Delete complaint
	@DeleteMapping("/{id}")
	public String deleteComplaint(@PathVariable Long id, HttpSession session) {
		requireAdmin(session);
		complaintService.deleteComplaint(id);
		return "Complaint deleted successfully";
	}
}
