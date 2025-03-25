package com.example.to_do.service;

import com.example.to_do.model.Task;
import com.example.to_do.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(long id) {
        if (taskRepository.findById(id).isPresent()) {
            return taskRepository.save(taskRepository.findById(id).get());
        }
        return taskRepository.findById(id).orElse(null);
    }

    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }




}
