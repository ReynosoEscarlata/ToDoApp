package com.reynoso.ToDoApp.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime eta;
    private boolean finished;
    private TaskStatus taskStatus;
    

}
