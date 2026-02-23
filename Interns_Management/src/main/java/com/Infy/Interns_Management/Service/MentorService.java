package com.Infy.Interns_Management.Service;

import java.util.List;

import com.Infy.Interns_Management.Entity.Mentor;

public interface MentorService {
	
	Mentor addMentor(Mentor mentor);

    List<Mentor> getAllMentors();

    Mentor getMentorById(Long mentorId);

    void deleteMentor(Long mentorId);
    
    Mentor updateMentor(Long mentorId, Mentor mentor);

}
