package com.Infy.Interns_Management.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Infy.Interns_Management.Entity.Intern;
import com.Infy.Interns_Management.Entity.Mentor;
import com.Infy.Interns_Management.Entity.Task;
import com.Infy.Interns_Management.Exception.ResourceNotFoundException;
import com.Infy.Interns_Management.Repository.InternRepository;
import com.Infy.Interns_Management.Repository.MentorRepository;
import com.Infy.Interns_Management.Repository.TaskRepository;
import com.Infy.Interns_Management.Service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
    private MentorRepository mentorRepository;
	@Autowired
    private InternRepository internRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           MentorRepository mentorRepository,
                           InternRepository internRepository) {
        this.taskRepository = taskRepository;
        this.mentorRepository = mentorRepository;
        this.internRepository = internRepository;
    }

    @Override
    public Task createTask(Task task, Long mentorId, String internId) {

        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found"));

        Intern intern = internRepository.findById(internId)
                .orElseThrow(() -> new ResourceNotFoundException("Intern not found"));

        task.setMentor(mentor);
        task.setIntern(intern);

        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

}
