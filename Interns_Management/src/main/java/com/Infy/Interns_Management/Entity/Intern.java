package com.Infy.Interns_Management.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "intern")
public class Intern {
	
	@Id
    @Column(unique = true)
    private String internId;   // Excel Unique ID

    private String internName;

    @Column(unique = true)
    private String email;

    private String phoneNumber;
    private String college;
    private String domain;

    private LocalDate internshipStartDate;
    private LocalDate internshipEndDate;

    private String status; // ACTIVE / COMPLETED / DROPPED

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = true)
    @JsonIgnore
    private Mentor mentor;

    @OneToMany(mappedBy = "intern")
    private List<Task> tasks;

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

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Intern [internId=" + internId + ", internName=" + internName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", college=" + college + ", domain=" + domain + ", internshipStartDate="
				+ internshipStartDate + ", internshipEndDate=" + internshipEndDate + ", status=" + status + ", mentor="
				+ mentor + ", tasks=" + tasks + "]";
	}
    
    
     

}
