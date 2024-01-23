package com.reynoso.ToDoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reynoso.ToDoApp.exceptions.ToDoExceptions;
import com.reynoso.ToDoApp.mapper.TaskInDTOToTask;
import com.reynoso.ToDoApp.persistence.entities.Task;
import com.reynoso.ToDoApp.persistence.entities.TaskStatus;
import com.reynoso.ToDoApp.persistence.repository.TaskRepository;
import com.reynoso.ToDoApp.service.dto.TaskInDTO;

@Service
public class TaskService {
    

    private final TaskRepository taskRepository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository taskRepository, TaskInDTOToTask mapper) {
        this.taskRepository = taskRepository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.taskRepository.save(task);
    }

    public List<Task> findAllTasks(){
        return this.taskRepository.findAll();
    }

    public List<Task> findAllTasksByStatus(TaskStatus status){
        return  this.taskRepository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskFinished(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.markTaskAsFinished(id);
    }

    public void deleteTaskById(Long id){
        Optional<Task> optionalTask = this.taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.taskRepository.deleteById(id);
    }

}
