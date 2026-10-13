package com.complainttracker.complainttracker.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.complainttracker.complainttracker.model.Complaint;
import com.complainttracker.complainttracker.repository.ComplaintRepository;

@Service
public class ComplaintService {
	private final ComplaintRepository complaintRepository;
	
	public ComplaintService(ComplaintRepository complaintRepository) {
		this.complaintRepository=complaintRepository;
	}
	public Complaint addComplaint(Complaint complaint) {
		complaint.setStatus("Pending");
		return complaintRepository.save(complaint);
	}
	public List<Complaint> getAllComplaints(){
		return complaintRepository.findAll(
				org.springframework.data.domain.Sort.by(
						org.springframework.data.domain.Sort.Direction.ASC,"id"
						)
				);
	}
	public Complaint getComplaintByTd(Long id) {
		return complaintRepository.findById(id).orElse(null);
	}
	public Complaint updateComplaint(Long id,Complaint complaint) {
		Complaint existingComplaint=complaintRepository.findById(id).orElse(null);
		if(existingComplaint!=null) {
			existingComplaint.setName(complaint.getName());
			existingComplaint.setEmail(complaint.getEmail());
			existingComplaint.setCategory(complaint.getCategory());
			existingComplaint.setDescription(complaint.getDescription());
			existingComplaint.setStatus(complaint.getStatus());
			
			return complaintRepository.save(existingComplaint);
		}
		return null;
	}
	public void deleteComplaint(Long id) {
		complaintRepository.deleteById(id);
	}

}
