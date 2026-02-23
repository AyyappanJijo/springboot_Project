package com.Infy.Interns_Management.Service;

import java.util.List;
import com.Infy.Interns_Management.Entity.Task;

public interface TaskService {
	
	Task createTask(Task task, Long mentorId, String internId);

    List<Task> getAllTasks();

    void deleteTask(Long taskId);

}
