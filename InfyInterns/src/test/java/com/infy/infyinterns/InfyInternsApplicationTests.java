package com.infy.infyinterns;

import com.infy.infyinterns.repository.MentorRepository;
import com.infy.infyinterns.service.ProjectAllocationService;
import com.infy.infyinterns.service.ProjectAllocationServiceImpl;


public class InfyInternsApplicationTests {

	
	private MentorRepository mentorRepository;

	
	private ProjectAllocationService projectAllocationService = new ProjectAllocationServiceImpl(mentorRepository, null);

	
	public void allocateProjectCannotAllocateTest() throws Exception {

		

	}

	
	public void allocateProjectMentorNotFoundTest() throws Exception {
	

	}
}