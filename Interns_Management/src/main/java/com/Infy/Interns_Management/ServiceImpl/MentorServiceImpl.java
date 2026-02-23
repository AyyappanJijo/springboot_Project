package com.Infy.Interns_Management.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Infy.Interns_Management.Entity.Mentor;
import com.Infy.Interns_Management.Exception.ResourceNotFoundException;
import com.Infy.Interns_Management.Repository.MentorRepository;
import com.Infy.Interns_Management.Service.MentorService;

@Service
public class MentorServiceImpl implements MentorService{
	
	@Autowired
	private MentorRepository mentorRepository;

    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    @Override
    public Mentor addMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

    @Override
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor getMentorById(Long mentorId) {
        return mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + mentorId));
    }

    @Override
    public void deleteMentor(Long mentorId) {
        Mentor mentor = getMentorById(mentorId);
        mentorRepository.delete(mentor);
    }

    @Override
    public Mentor updateMentor(Long mentorId, Mentor updatedMentor) {

        Mentor mentor = getMentorById(mentorId);

        mentor.setName(updatedMentor.getName());
        mentor.setEmail(updatedMentor.getEmail());
        mentor.setRole(updatedMentor.getRole());

        return mentorRepository.save(mentor);
    }

}
