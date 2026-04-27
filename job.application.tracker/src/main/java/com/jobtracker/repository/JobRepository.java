package com.jobtracker.repository;

import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(Status status);
    List<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName);
}