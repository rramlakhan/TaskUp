package com.taskUp.services;

import com.taskUp.entities.Task;
import com.taskUp.repositories.TaskRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task addTask(Task task) {
        return taskRepository.insert(task);
    }

    public Optional<Task> getTaskById(ObjectId id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(ObjectId id, Task task) {
        Optional<Task> savedTaskOptional = taskRepository.findById(id);
        if (savedTaskOptional.isPresent()) {
            Task savedTask = savedTaskOptional.get();
            savedTask.setTaskName(task.getTaskName() != null && !task.getTaskName().isEmpty() ? task.getTaskName(): savedTask.getTaskName());
            savedTask.setDescription(task.getDescription() != null && !task.getDescription().isEmpty() ? task.getDescription(): savedTask.getDescription());
            return taskRepository.save(savedTask);
        }
        return null;
    }

    public Boolean deleteTask(ObjectId id) {
        taskRepository.deleteById(id);
        return true;
    }
}
