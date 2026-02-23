package com.Infy.Interns_Management.Dto;

import java.time.LocalDate;

public class InternDTO {
	
	private String internId;
    private String internName;
    private String email;
    private String phoneNumber;
    private String college;
    private String domain;
    private LocalDate internshipStartDate;
    private LocalDate internshipEndDate;
    private String status;
    private Long mentorId;
    
    
    
	public String getInternId() {
		return internId;
	}
	public void setInternId(String internId) {
		this.internId = internId;
	}
	public String getInternName() {
		return internName;
	}
	public void setInternName(String internName) {
		this.internName = internName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public LocalDate getInternshipStartDate() {
		return internshipStartDate;
	}
	public void setInternshipStartDate(LocalDate internshipStartDate) {
		this.internshipStartDate = internshipStartDate;
	}
	public LocalDate getInternshipEndDate() {
		return internshipEndDate;
	}
	public void setInternshipEndDate(LocalDate internshipEndDate) {
		this.internshipEndDate = internshipEndDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getMentorId() {
		return mentorId;
	}
	public void setMentorId(Long mentorId) {
		this.mentorId = mentorId;
	}
	
	@Override
	public String toString() {
		return "InternDTO [internId=" + internId + ", internName=" + internName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", college=" + college + ", domain=" + domain + ", internshipStartDate="
				+ internshipStartDate + ", internshipEndDate=" + internshipEndDate + ", status=" + status
				+ ", mentorId=" + mentorId + "]";
	}
    
    

}
