package com.infy.infyinterns.repository;

import com.infy.infyinterns.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    /*
     * Find all projects assigned to a specific mentor.
     * Used if you ever need to show a mentor's project list.
     */
    List<Project> findByMentorMentorId(Integer mentorId);

    /*
     * Find projects by idea owner (employee ID).
     */
    @Query("SELECT p FROM Project p WHERE p.ideaOwner = :ideaOwner")
    List<Project> findByIdeaOwner(@Param("ideaOwner") Integer ideaOwner);
}