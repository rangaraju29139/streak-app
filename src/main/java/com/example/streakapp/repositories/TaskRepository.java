package com.example.streakapp.repositories;

import com.example.streakapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {



}
