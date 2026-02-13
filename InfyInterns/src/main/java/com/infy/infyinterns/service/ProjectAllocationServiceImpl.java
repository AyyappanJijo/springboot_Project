package com.infy.infyinterns.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.entity.Mentor;
import com.infy.infyinterns.entity.Project;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.repository.ProjectRepository;

@Service
@Transactional
public class ProjectAllocationServiceImpl implements ProjectAllocationService {
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private MentorRepository mentorRepo;
	
	public ProjectAllocationServiceImpl(MentorRepository mentorRepo, ProjectRepository projectRepo) {
		this.mentorRepo = mentorRepo;
		this.projectRepo = projectRepo;
	}

	@Override
	public Integer allocateProject(ProjectDTO dto) throws InfyInternException {
		
		Mentor mentor = mentorRepo.findById(dto.getMentorDTO().getMentorId())
				.orElseThrow(()-> new InfyInternException("Service.MENTOR_NOT_FOUND"));
		
		if(mentor.getNumberOfProjectsMentored()>=3)
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		
		
		Project project = new Project();
		project.setProjectName(dto.getProjectName());
		project.setIdeaOwner(dto.getIdeaOwner());
		project.setReleaseDate(dto.getReleaseDate());
		project.setMentor(mentor);
		
		projectRepo.save(project);
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored() + 1);
		
		return project.getProjectId();
	}

	
	@Override
	public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {
		
		List<Mentor> mentor = mentorRepo.findByNumberOfProjectsMentored(numberOfProjectsMentored);
		
		if(mentor.isEmpty())
			throw new InfyInternException("Service.MENTOR_NOT_FOUND");
		
		List<MentorDTO> list = new ArrayList<MentorDTO>();
		
		for(Mentor m : mentor) {
			
			MentorDTO dto = new MentorDTO();
			dto.setMentorId(m.getMentorId());
			dto.setMentorName(m.getMentorName());
			dto.setNumberOfProjectsMentored(m.getNumberOfProjectsMentored());
			
			list.add(dto);
		}
		
		
		return list;
	
	}


	@Override
	public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {
		
		Mentor mentor = mentorRepo.findById(mentorId)
				.orElseThrow(()-> new InfyInternException("Service.MENTOR_NOT_FOUND"));
		
		if(mentor.getNumberOfProjectsMentored()>=3)
			throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new InfyInternException("Service.PROJECT_NOT_FOUND"));
		
		project.setMentor(mentor);
		
		mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()+1);
		
	}

	@Override
	public void deleteProject(Integer projectId) throws InfyInternException {
		
		Project project = projectRepo.findById(projectId)
				.orElseThrow(()-> new InfyInternException("Service.PROJECT_NOT_FOUND"));
		
		Mentor mentor = project.getMentor();
		
		if(mentor != null) 
			mentor.setNumberOfProjectsMentored(mentor.getNumberOfProjectsMentored()-1);
			
		projectRepo.delete(project);
		
	}
}