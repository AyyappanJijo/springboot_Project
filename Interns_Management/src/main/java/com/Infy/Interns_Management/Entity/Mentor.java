package com.Infy.Interns_Management.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mentor")
public class Mentor {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long mentorId;

	    private String name;

	    @Column(unique = true)
	    private String email;

	    private String role; // ADMIN or MENTOR

	    @OneToMany(mappedBy = "mentor")
	    @JsonIgnore
	    private List<Intern> interns;

	    @OneToMany(mappedBy = "mentor")
	    @JsonIgnore
	    private List<Task> tasks;

		public Long getMentorId() {
			return mentorId;
		}

		public void setMentorId(Long mentorId) {
			this.mentorId = mentorId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public List<Intern> getInterns() {
			return interns;
		}

		public void setInterns(List<Intern> interns) {
			this.interns = interns;
		}

		public List<Task> getTasks() {
			return tasks;
		}

		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}

		
		@Override
		public String toString() {
			return "Mentor [mentorId=" + mentorId + ", name=" + name + ", email=" + email + ", role=" + role
					+ ", interns=" + interns + ", tasks=" + tasks + "]";
		}
	    
	    

}
