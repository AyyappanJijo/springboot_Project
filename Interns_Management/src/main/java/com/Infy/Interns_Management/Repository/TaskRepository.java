package com.Infy.Interns_Management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Infy.Interns_Management.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
