package com.infy.infyinterns.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;

@RestController
@RequestMapping("/api/infyinterns")
public class ProjectAllocationAPI{
	
	@Autowired
	private ProjectAllocationService service;

    @PostMapping("/project")
    public ResponseEntity<String> allocateProject(@RequestBody ProjectDTO project) throws InfyInternException{
    	
    	      Integer id = service.allocateProject(project);
	      return new ResponseEntity<String>("Project Allocated With Id: " + id, HttpStatus.CREATED);
    }

    @GetMapping("/mentor/{count}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException{
    	
	     return new ResponseEntity<>(service.getMentors(numberOfProjectsMentored), HttpStatus.OK);
    }

    
    @PutMapping("project/{projectId}/mentor/{mentorId}")
    public ResponseEntity<String> updateProjectMentor(@PathVariable Integer projectId,
						      @PathVariable Integer mentorId) throws InfyInternException{
    	
    	   service.updateProjectMentor(projectId, mentorId);
   
	   return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException{
    	
      	service.deleteProject(projectId);
	    return new ResponseEntity<>("Project Deleted Successfully", HttpStatus.OK);
    }

}
