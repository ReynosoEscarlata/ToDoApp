package com.reynoso.ToDoApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.reynoso.ToDoApp.persistence.entities.Task;
import com.reynoso.ToDoApp.persistence.entities.TaskStatus;
import com.reynoso.ToDoApp.service.TaskService;
import com.reynoso.ToDoApp.service.dto.TaskInDTO;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    private final TaskService taskService;
    
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDto) {
        return this.taskService.createTask(taskInDto);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return this.taskService.findAllTasks();
    }

    @GetMapping("/status/{status}")
    public List<Task> getAllTasksByStatus(@PathVariable("status") TaskStatus status) {
        return this.taskService.findAllTasksByStatus(status);
    }

    @PatchMapping("/mark-finished/{id}")
    public ResponseEntity<Void> markTaskFinished(@PathVariable("id") Long id) {
        this.taskService.updateTaskFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        this.taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
