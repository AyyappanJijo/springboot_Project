package com.infy.infyinterns.repository;

import com.infy.infyinterns.entity.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {

    /*
     * Derived query — Spring Data generates the SQL automatically.
     * SELECT * FROM mentor WHERE number_of_projects_mentored = ?
     */
    List<Mentor> findByNumberOfProjectsMentored(Integer count);

    /*
     * Custom JPQL example — useful when you need mentors who still
     * have capacity (less than 3 projects).
     */
    @Query("SELECT m FROM Mentor m WHERE m.numberOfProjectsMentored < :limit ORDER BY m.numberOfProjectsMentored ASC")
    List<Mentor> findMentorsWithCapacity(@Param("limit") Integer limit);
}