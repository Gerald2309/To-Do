package com.example.to_do.controller;

import com.example.to_do.model.Task;
import com.example.to_do.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @GetMapping()
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Bienvenido a la API de gestión de tareas");
    }

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId)
               .map(ResponseEntity::ok)
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Task> saveTask(@RequestBody Task task){
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task taskDetails){
        return taskService.getTaskById(taskId)
               .map(t -> {
                    t.setTitle(taskDetails.getTitle());
                    t.setDescription(taskDetails.getDescription());
                    t.setCompleted(taskDetails.isCompleted());
                    return ResponseEntity.ok(taskService.saveTask(t));
                })
               .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId){
        return taskService.getTaskById(taskId)
               .map(task -> {
                    taskService.deleteTask(taskId);
                    return ResponseEntity.noContent().build();
                })
               .orElseGet(() -> ResponseEntity.notFound().build());


    }

}
