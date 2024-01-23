package com.reynoso.ToDoApp.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.reynoso.ToDoApp.persistence.entities.Task;
import com.reynoso.ToDoApp.persistence.entities.TaskStatus;
import com.reynoso.ToDoApp.service.dto.TaskInDTO;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{

    @Override
    public Task map(TaskInDTO in){
        System.out.println(in.getDescription());
        Task task = new Task();
        // Fields added by user
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        // Fields add by code
        task.setCreatedDate(LocalDateTime.now());
        task.setFinished(false);
        task.setTaskStatus(TaskStatus.ON_TIME);
        return task;

    }
}
