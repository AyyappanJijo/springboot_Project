package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;

import java.util.List;

public interface ProjectAllocationService {

    /**
     * Allocates a new project and assigns a mentor to it.
     *
     * @param projectAllocation DTO containing project details and mentor ID
     * @return the auto-generated project ID
     * @throws InfyInternException if mentor not found or already at 3-project capacity
     */
    Integer allocateProject(ProjectDTO projectAllocation) throws InfyInternException;

    /**
     * Returns all mentors who are currently mentoring exactly N projects.
     *
     * @param numberOfProjectsMentored the exact count to filter by
     * @return list of matching MentorDTOs
     * @throws InfyInternException if no mentors found for the given count
     */
    List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException;

    /**
     * Re-assigns a project to a different mentor.
     * Decrements the old mentor's count and increments the new one.
     *
     * @param projectId ID of the project to reassign
     * @param mentorId  ID of the new mentor
     * @throws InfyInternException if project/mentor not found or new mentor at capacity
     */
    void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException;

    /**
     * Deletes a project and decrements the assigned mentor's project count.
     *
     * @param projectId ID of the project to delete
     * @throws InfyInternException if project not found
     */
    void deleteProject(Integer projectId) throws InfyInternException;
}