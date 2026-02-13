package com.infy.infyinterns.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.infyinterns.entity.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer>{
    
	List<Mentor> findByNumberOfProjectsMentored(Integer count);
}
