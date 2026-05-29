package com.infy.infyinterns.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mentor")
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mentorId;

    @Column(nullable = false, length = 100)
    private String mentorName;

    @Column(nullable = false)
    private Integer numberOfProjectsMentored;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Project> project = new ArrayList<>();

    /* ── Getters & Setters ── */

    public Integer getMentorId() { return mentorId; }
    public void setMentorId(Integer mentorId) { this.mentorId = mentorId; }

    public String getMentorName() { return mentorName; }
    public void setMentorName(String mentorName) { this.mentorName = mentorName; }

    public Integer getNumberOfProjectsMentored() { return numberOfProjectsMentored; }
    public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
        this.numberOfProjectsMentored = numberOfProjectsMentored;
    }

    public List<Project> getProject() { return project; }
    public void setProject(List<Project> project) { this.project = project; }

    /* ── equals / hashCode / toString ── */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mentorId == null) ? 0 : mentorId.hashCode());
        result = prime * result + ((mentorName == null) ? 0 : mentorName.hashCode());
        result = prime * result + ((numberOfProjectsMentored == null) ? 0
                : numberOfProjectsMentored.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mentor other = (Mentor) obj;
        if (mentorId == null) return other.mentorId == null;
        return mentorId.equals(other.mentorId);
    }

    @Override
    public String toString() {
        return "Mentor [mentorId=" + mentorId
                + ", mentorName=" + mentorName
                + ", numberOfProjectsMentored=" + numberOfProjectsMentored + "]";
    }
}