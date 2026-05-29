package com.infy.infyinterns.api;

import com.infy.infyinterns.dto.MentorDTO;
import com.infy.infyinterns.dto.ProjectDTO;
import com.infy.infyinterns.exception.InfyInternException;
import com.infy.infyinterns.service.ProjectAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/infyinterns")
@Validated
public class ProjectAllocationAPI {

    @Autowired
    private ProjectAllocationService service;

    // POST /api/infyinterns/project
    @PostMapping("/project")
    public ResponseEntity<String> allocateProject(
            @Valid @RequestBody ProjectDTO project) throws InfyInternException {

        Integer id = service.allocateProject(project);
        return new ResponseEntity<>("Project Allocated With Id: " + id, HttpStatus.CREATED);
    }

    // GET /api/infyinterns/mentor/{count}
    // BUG FIX: original had @PathVariable Integer numberOfProjectsMentored
    // but path variable name is {count} — they must match exactly
    @GetMapping("/mentor/{count}")
    public ResponseEntity<List<MentorDTO>> getMentors(
            @PathVariable("count") @Min(value = 0, message = "Count must be 0 or more") Integer count)
            throws InfyInternException {

        return new ResponseEntity<>(service.getMentors(count), HttpStatus.OK);
    }

    // PUT /api/infyinterns/project/{projectId}/mentor/{mentorId}
    @PutMapping("/project/{projectId}/mentor/{mentorId}")
    public ResponseEntity<String> updateProjectMentor(
            @PathVariable Integer projectId,
            @PathVariable Integer mentorId) throws InfyInternException {

        service.updateProjectMentor(projectId, mentorId);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    // DELETE /api/infyinterns/project/{projectId}
    @DeleteMapping("/project/{projectId}")
    public ResponseEntity<String> deleteProject(
            @PathVariable Integer projectId) throws InfyInternException {

        service.deleteProject(projectId);
        return new ResponseEntity<>("Project Deleted Successfully", HttpStatus.OK);
    }
}