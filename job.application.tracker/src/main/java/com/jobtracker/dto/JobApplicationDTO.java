package com.jobtracker.dto;

import com.jobtracker.entity.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JobApplicationDTO {

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Role is required")
    private String role;

    private Status status;
    private String notes;
    private String appliedDate;
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAppliedDate() {
		return appliedDate;
	}
	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}
	@Override
	public String toString() {
		return "JobApplicationDTO [companyName=" + companyName + ", role=" + role + ", status=" + status + ", notes="
				+ notes + ", appliedDate=" + appliedDate + "]";
	}
    
    
    
}