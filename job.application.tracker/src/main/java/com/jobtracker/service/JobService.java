package com.jobtracker.service;

import com.jobtracker.dto.JobApplicationDTO;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.Status;
import com.jobtracker.repository.JobRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    private final JobRepository repo;

    public JobService(JobRepository repo) {
        this.repo = repo;
    }

    public JobApplication create(JobApplicationDTO dto) {
        JobApplication job = mapToEntity(dto);
        job.setCreatedAt(LocalDateTime.now());
        return repo.save(job);
    }

    private JobApplication mapToEntity(JobApplicationDTO dto) {
        JobApplication job = new JobApplication();

        job.setCompanyName(dto.getCompanyName());
        job.setRole(dto.getRole());
        job.setStatus(dto.getStatus() != null ? dto.getStatus() : Status.APPLIED);
        job.setNotes(dto.getNotes());
        job.setAppliedDate(dto.getAppliedDate());

        return job;
    }

    public List<JobApplication> getAll() {
        return repo.findAll();
    }

    public Page<JobApplication> getPaginated(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    public List<JobApplication> filterByStatus(Status status) {
        return repo.findByStatus(status);
    }

    public List<JobApplication> searchByCompany(String company) {
        return repo.findByCompanyNameContainingIgnoreCase(company);
    }

    public JobApplication update(Long id, JobApplicationDTO dto) {
        JobApplication job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setCompanyName(dto.getCompanyName());
        job.setRole(dto.getRole());
        job.setStatus(dto.getStatus());
        job.setNotes(dto.getNotes());
        job.setAppliedDate(dto.getAppliedDate());
        job.setUpdatedAt(LocalDateTime.now());

        return repo.save(job);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Job not found with id: " + id);
        }
        repo.deleteById(id);
    }
}