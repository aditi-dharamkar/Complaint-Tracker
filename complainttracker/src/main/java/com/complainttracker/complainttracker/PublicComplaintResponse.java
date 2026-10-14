package com.complainttracker.complainttracker;
import java.time.LocalDateTime;

public class PublicComplaintResponse {
	  private Long id;
	    private String category;
	    private String status;
	    private LocalDateTime createdAt;

	    public PublicComplaintResponse(
	            Long id,
	            String category,
	            String status,
	            LocalDateTime createdAt) {
	        this.id = id;
	        this.category = category;
	        this.status = status;
	        this.createdAt = createdAt;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

}
