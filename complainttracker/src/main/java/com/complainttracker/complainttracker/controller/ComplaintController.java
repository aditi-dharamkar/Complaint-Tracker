package com.complainttracker.complainttracker.controller;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complainttracker.complainttracker.model.Complaint;
import com.complainttracker.complainttracker.service.ComplaintService;

@RestController
@RequestMapping("/api/complaints")

public class ComplaintController {
	private final ComplaintService complaintService;
	public ComplaintController(ComplaintService complaintService){
		this.complaintService=complaintService;
	}
	
	//Add complaint
	@PostMapping
	public Complaint addComplaint(@RequestBody Complaint complaint) {
		return complaintService.addComplaint(complaint);
	}
	
	//Get all complaints
	@GetMapping
	public List<Complaint> getAllComplaints(){
		return complaintService.getAllComplaints();
	}
	
	//Get complaint by Id
	@GetMapping("/{id}")
	public Complaint getComplaintById(@PathVariable Long id) {
		return complaintService.getComplaintByTd(id);
	}
	
	//Update complaint
	@PutMapping("/{id}")
	public Complaint updateCoomplaint(
			@PathVariable Long id,
			@RequestBody Complaint complaint) {
		return complaintService.updateComplaint(id, complaint);
	}
	
	//Delete complaint
	@DeleteMapping("/{id}")
	public String deleteComplaint(@PathVariable Long id) {
		complaintService.deleteComplaint(id);
		return "Complaint deleted successfully";
	}

}
