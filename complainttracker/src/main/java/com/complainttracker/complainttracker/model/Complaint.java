package com.complainttracker.complainttracker.model;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="complaints")
public class Complaint {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private String email;
	private String category;
	private String description;
	private String status;
	private java.time.LocalDateTime createdAt;
	
	public Complaint() {
		
	}
	@PrePersist
	public void setCreatedAtAutomatically() {
		createdAt=java.time.LocalDateTime.now();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public java.time.LocalDateTime getCreatedAt(){
		return createdAt;
	}
	public void setCreatedAt(java.time.LocalDateTime createdAt) {
		this.createdAt=createdAt;
	}

}
