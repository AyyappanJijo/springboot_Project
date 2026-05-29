package com.infy.infyinterns.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;

    @Column(nullable = false, length = 100)
    private String projectName;

    @Column(nullable = false)
    private Integer ideaOwner;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    /* ── Getters & Setters ── */

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public Integer getIdeaOwner() { return ideaOwner; }
    public void setIdeaOwner(Integer ideaOwner) { this.ideaOwner = ideaOwner; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public Mentor getMentor() { return mentor; }
    public void setMentor(Mentor mentor) { this.mentor = mentor; }

    /* ── equals / hashCode / toString ── */

    @Override
    public int hashCode() { return 31; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project other = (Project) obj;
        if (projectId == null) return other.projectId == null;
        return projectId.equals(other.projectId);
    }

    @Override
    public String toString() {
        return "Project [projectId=" + projectId
                + ", projectName=" + projectName
                + ", ideaOwner=" + ideaOwner
                + ", releaseDate=" + releaseDate + "]";
    }
}