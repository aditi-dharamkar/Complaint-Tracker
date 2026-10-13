package com.complainttracker.complainttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.complainttracker.complainttracker.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>{

}
