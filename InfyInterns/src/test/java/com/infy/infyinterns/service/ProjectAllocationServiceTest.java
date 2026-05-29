package com.infy.infyinterns.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;	
import org.mockito.MockitoAnnotations;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

/**
 * Unit tests for ProjectAllocationServiceImpl.
 * Run with:  mvn test
 */
class ProjectAllocationServiceTest {

    @Mock private MentorRepository  mentorRepo;
    @Mock private ProjectRepository projectRepo;

    @InjectMocks
    private ProjectAllocationServiceImpl service;

    private Mentor     mentor;
    private ProjectDTO projectDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mentor = new Mentor();
        mentor.setMentorId(1);
        mentor.setMentorName("Alice");
        mentor.setNumberOfProjectsMentored(1);

        MentorDTO mentorDTO = new MentorDTO(1, "Alice", 1);
        projectDTO = new ProjectDTO(null, "AI Project", 101,
                LocalDate.now().plusMonths(3), mentorDTO);
    }

    /* ── allocateProject ── */

    @Test
    @DisplayName("allocateProject: success → returns generated project ID")
    void allocateProject_Success() throws InfyInternException {
        when(mentorRepo.findById(1)).thenReturn(Optional.of(mentor));
        when(projectRepo.save(any(Project.class))).thenAnswer(inv -> {
            Project p = inv.getArgument(0);
            p.setProjectId(10);
            return p;
        });

        Integer id = service.allocateProject(projectDTO);

        assertNotNull(id);
        assertEquals(10, id);
        assertEquals(2, mentor.getNumberOfProjectsMentored());
        verify(projectRepo, times(1)).save(any(Project.class));
    }

    @Test
    @DisplayName("allocateProject: mentor not found → InfyInternException")
    void allocateProject_MentorNotFound() {
        when(mentorRepo.findById(1)).thenReturn(Optional.empty());
        assertThrows(InfyInternException.class, () -> service.allocateProject(projectDTO));
    }

    @Test
    @DisplayName("allocateProject: mentor at capacity (3 projects) → InfyInternException")
    void allocateProject_MentorAtCapacity() {
        mentor.setNumberOfProjectsMentored(3);
        when(mentorRepo.findById(1)).thenReturn(Optional.of(mentor));
        assertThrows(InfyInternException.class, () -> service.allocateProject(projectDTO));
    }

    /* ── getMentors ── */

    @Test
    @DisplayName("getMentors: found → returns mapped DTOs")
    void getMentors_Success() throws InfyInternException {
        when(mentorRepo.findByNumberOfProjectsMentored(1)).thenReturn(List.of(mentor));

        List<MentorDTO> result = service.getMentors(1);

        assertFalse(result.isEmpty());
        assertEquals("Alice", result.get(0).getMentorName());
        assertEquals(1, result.get(0).getNumberOfProjectsMentored());
    }

    @Test
    @DisplayName("getMentors: none found → InfyInternException")
    void getMentors_NoneFound() {
        when(mentorRepo.findByNumberOfProjectsMentored(5)).thenReturn(List.of());
        assertThrows(InfyInternException.class, () -> service.getMentors(5));
    }

    /* ── updateProjectMentor ── */

    @Test
    @DisplayName("updateProjectMentor: success → old mentor decremented, new mentor incremented")
    void updateProjectMentor_Success() throws InfyInternException {
        Mentor oldMentor = new Mentor();
        oldMentor.setMentorId(99);
        oldMentor.setNumberOfProjectsMentored(2);

        Mentor newMentor = new Mentor();
        newMentor.setMentorId(1);
        newMentor.setNumberOfProjectsMentored(1);

        Project project = new Project();
        project.setProjectId(5);
        project.setMentor(oldMentor);

        when(mentorRepo.findById(1)).thenReturn(Optional.of(newMentor));
        when(projectRepo.findById(5)).thenReturn(Optional.of(project));

        service.updateProjectMentor(5, 1);

        assertEquals(1, oldMentor.getNumberOfProjectsMentored()); // decremented
        assertEquals(2, newMentor.getNumberOfProjectsMentored()); // incremented
    }

    /* ── deleteProject ── */

    @Test
    @DisplayName("deleteProject: success → mentor count decremented and project deleted")
    void deleteProject_Success() throws InfyInternException {
        Project project = new Project();
        project.setProjectId(1);
        project.setMentor(mentor);

        when(projectRepo.findById(1)).thenReturn(Optional.of(project));

        service.deleteProject(1);

        assertEquals(0, mentor.getNumberOfProjectsMentored());
        verify(projectRepo, times(1)).delete(project);
    }

    @Test
    @DisplayName("deleteProject: project not found → InfyInternException")
    void deleteProject_NotFound() {
        when(projectRepo.findById(99)).thenReturn(Optional.empty());
        assertThrows(InfyInternException.class, () -> service.deleteProject(99));
    }
}