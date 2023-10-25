package com.example.streakapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.IdGeneratorType;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {

    @Id
    private long id;
    private String taskName;
    private int streakCount;
    @CreationTimestamp
    private LocalDate createdDate;

    private LocalDate lastTimeTaskCompletedDate;

    private boolean isDone;

}
