package com.infy.infyinterns.service;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {

    private final ProjectRepository projectRepo;
    private final MentorRepository  mentorRepo;

    // Constructor injection — preferred over @Autowired on fields
    @Autowired
    public ProjectAllocationServiceImpl(MentorRepository mentorRepo,
                                        ProjectRepository projectRepo) {
        this.mentorRepo  = mentorRepo;
        this.projectRepo = projectRepo;
    }

    // ─────────────────────────────────────────────────────────────
    //  ALLOCATE PROJECT
    // ─────────────────────────────────────────────────────────────
    @Override
    @CacheEvict(value = "mentors", allEntries = true)
    public Integer allocateProject(ProjectDTO dto) throws InfyInternException {

        // 1. Check mentor exists
        Mentor mentor = mentorRepo.findById(dto.getMentorDTO().getMentorId())
                .orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));

        // 2. Check mentor capacity
        if (mentor.getNumberOfProjectsMentored() >= 3)
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");

        // 3. Build and save the project
        Project project = new Project();
        project.setProjectName(dto.getProjectName());
        project.setIdeaOwner(dto.getIdeaOwner());
        project.setReleaseDate(dto.getReleaseDate());
        project.setMentor(mentor);

        projectRepo.save(project);

        // 4. Increment mentor count
        mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);

        return project.getProjectId();
    }

    // ─────────────────────────────────────────────────────────────
    //  GET MENTORS BY PROJECT COUNT
    // ─────────────────────────────────────────────────────────────
    @Override
    @Cacheable(value = "mentors", key = "#numberOfProjectsMentored")
    @Transactional(readOnly = true)
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {

        List<Mentor> mentors = mentorRepo.findByNumberOfProjectsMentored(numberOfProjectsMentored);

        if (mentors.isEmpty())
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");

        List<MentorDTO> list = new ArrayList<>();
        for (Mentor m : mentors) {
            MentorDTO dto = new MentorDTO();
            dto.setMentorId(m.getMentorId());
            dto.setMentorName(m.getMentorName());
            dto.setNumberOfProjectsMentored(m.getNumberOfProjectsMentored());
            list.add(dto);
        }
        return list;
    }

    // ─────────────────────────────────────────────────────────────
    //  UPDATE PROJECT MENTOR
    //  BUG FIX: original never decremented the old mentor's count
    // ─────────────────────────────────────────────────────────────
    @Override
    @CacheEvict(value = "mentors", allEntries = true)
    public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {

        // 1. Validate new mentor
        Mentor newMentor = mentorRepo.findById(mentorId)
                .orElseThrow(() -> new InfyInternException("Service.MENTOR_NOT_FOUND"));

        if (newMentor.getNumberOfProjectsMentored() >= 3)
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");

        // 2. Find project
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));

        // 3. Decrement old mentor's count (only if different mentor)
        Mentor oldMentor = project.getMentor();
        if (oldMentor != null && !oldMentor.getMentorId().equals(mentorId)) {
            oldMentor.setNumberOfProjectsMentored(oldMentor.getNumberOfProjectsMentored() - 1);
        }

        // 4. Assign new mentor and increment count
        project.setMentor(newMentor);
        newMentor.setNumberOfProjectsMentored(newMentor.getNumberOfProjectsMentored() + 1);
    }

    // ─────────────────────────────────────────────────────────────
    //  DELETE PROJECT
    // ─────────────────────────────────────────────────────────────
    @Override
    @CacheEvict(value = "mentors", allEntries = true)
    public void deleteProject(Integer projectId) throws InfyInternException {

        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new InfyInternException("Service.PROJECT_NOT_FOUND"));

        Mentor mentor = project.getMentor();
        if (mentor != null)
            mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() - 1);

        projectRepo.delete(project);
    }
}