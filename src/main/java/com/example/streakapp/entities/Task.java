package com.example.streakapp.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue
    private long id;

    @NotNull
    private String taskName;
    private int streakCount;
    @CreationTimestamp
    private LocalDate createdDate;

    private LocalDate lastTimeTaskCompletedDate;
    private boolean todayTaskCompleted;

    private boolean isDone;

}
