package com.jobtracker.controller;

import com.jobtracker.dto.JobApplicationDTO;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.entity.Status;
import com.jobtracker.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService service;

    public JobController(JobService service) {
        this.service = service;
    }

    @PostMapping
    public JobApplication create(@Valid @RequestBody JobApplicationDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<JobApplication> getAll() {
        return service.getAll();
    }

    @GetMapping("/page")
    public Page<JobApplication> getPaginated(@RequestParam int page, @RequestParam int size) {
        return service.getPaginated(page, size);
    }

    @GetMapping("/status")
    public List<JobApplication> filterByStatus(@RequestParam Status status) {
        return service.filterByStatus(status);
    }

    @GetMapping("/search")
    public List<JobApplication> searchByCompany(@RequestParam String company) {
        return service.searchByCompany(company);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id, @Valid @RequestBody JobApplicationDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
