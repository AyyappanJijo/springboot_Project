package com.infy.infyinterns.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Schema(description = "Data Transfer Object for Project")
public class ProjectDTO {

    @Schema(description = "Auto-generated project ID",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer projectId;

    @NotBlank(message = "{project.name.required}")
    @Size(min = 3, max = 100, message = "{project.name.size}")
    @Schema(description = "Project name", example = "AI Chatbot")
    private String projectName;

    @NotNull(message = "{project.ideaOwner.required}")
    @Schema(description = "Employee ID of the idea owner", example = "1001")
    private Integer ideaOwner;

    @NotNull(message = "{project.releaseDate.required}")
    @Future(message = "{project.releaseDate.future}")
    @Schema(description = "Expected release date (must be a future date)", example = "2025-12-31")
    private LocalDate releaseDate;

    @NotNull(message = "{project.mentor.required}")
    @Valid
    @Schema(description = "Mentor assigned to this project")
    private MentorDTO mentorDTO;

    public ProjectDTO() {
        super();
    }

    public ProjectDTO(Integer projectId, String projectName,
                      Integer ideaOwner, LocalDate releaseDate,
                      MentorDTO mentorDTO) {
        super();
        this.projectId   = projectId;
        this.projectName = projectName;
        this.ideaOwner   = ideaOwner;
        this.releaseDate = releaseDate;
        this.mentorDTO   = mentorDTO;
    }

    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }

    public Integer getIdeaOwner() { return ideaOwner; }
    public void setIdeaOwner(Integer ideaOwner) { this.ideaOwner = ideaOwner; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public MentorDTO getMentorDTO() { return mentorDTO; }
    public void setMentorDTO(MentorDTO mentorDTO) { this.mentorDTO = mentorDTO; }
}