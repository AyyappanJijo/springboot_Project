package com.infy.infyinterns.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Data Transfer Object for Mentor")
public class MentorDTO {

    @NotNull(message = "{mentor.id.required}")
    @Schema(description = "Mentor's unique ID", example = "1")
    private Integer mentorId;

    @Schema(description = "Mentor's full name", example = "Alice Smith",
            accessMode = Schema.AccessMode.READ_ONLY)
    private String mentorName;

    @Schema(description = "Number of projects currently mentored",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Integer numberOfProjectsMentored;

    public MentorDTO() {
        super();
    }

    public MentorDTO(Integer mentorId, String mentorName, Integer numberOfProjectsMentored) {
        super();
        this.mentorId = mentorId;
        this.mentorName = mentorName;
        this.numberOfProjectsMentored = numberOfProjectsMentored;
    }

    public Integer getMentorId() { return mentorId; }
    public void setMentorId(Integer mentorId) { this.mentorId = mentorId; }

    public String getMentorName() { return mentorName; }
    public void setMentorName(String mentorName) { this.mentorName = mentorName; }

    public Integer getNumberOfProjectsMentored() { return numberOfProjectsMentored; }
    public void setNumberOfProjectsMentored(Integer numberOfProjectsMentored) {
        this.numberOfProjectsMentored = numberOfProjectsMentored;
    }
}