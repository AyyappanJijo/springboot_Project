package com.Infy.Interns_Management.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Infy.Interns_Management.Entity.Mentor;
import com.Infy.Interns_Management.Service.MentorService;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {
	
	private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    // Create Mentor (Admin adds mentor)
    @PostMapping
    public Mentor addMentor(@RequestBody Mentor mentor) {
        return mentorService.addMentor(mentor);
    }

    // Get All Mentors
    @GetMapping
    public List<Mentor> getAllMentors() {
        return mentorService.getAllMentors();
    }

    // Get Mentor By ID
    @GetMapping("/{mentorId}")
    public Mentor getMentorById(@PathVariable Long mentorId) {
        return mentorService.getMentorById(mentorId);
    }

    // Delete Mentor
    @DeleteMapping("/{mentorId}")
    public String deleteMentor(@PathVariable Long mentorId) {
        mentorService.deleteMentor(mentorId);
        return "Mentor deleted successfully";
    }
    
    @PutMapping("/{mentorId}")
    public Mentor updateMentor(@PathVariable Long mentorId,
                               @RequestBody Mentor mentor) {
        return mentorService.updateMentor(mentorId, mentor);
    }

}
