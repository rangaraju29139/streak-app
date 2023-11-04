package com.example.streakapp.controller;


import com.example.streakapp.entities.Task;
import com.example.streakapp.exceptions.TaskNotCreateException;
import com.example.streakapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://${REACT_HOST_NAME:localhost}:3000")
@RestController
public class TasksController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/")
    public String getHome(){
        return "try for /api/v1/tasks to get all tasks";
    }


    @GetMapping("/api/v1/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> tasks = taskService.getAllTasks();
        if(tasks==null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tasks);
    }
    @PatchMapping("/api/v1/tasks/{id}/updateDone/{status}")
    public ResponseEntity updateDone(@PathVariable Long id,@PathVariable boolean status){
        Optional<Task> task= taskService.updateDone(id,status);
        if(!task.isEmpty()){
           return  ResponseEntity.ok(task.get());
        }
        return ResponseEntity.internalServerError().build();

    }
    @PatchMapping("/api/v1/tasks/{id}/updateTaskName")
    public ResponseEntity updateDone(@PathVariable Long id,@RequestBody Task inputTask){
        Optional<Task> task= taskService.updateTaskName(id,inputTask.getTaskName());
        if(!task.isEmpty()){
            return  ResponseEntity.ok(task.get());
        }
        return ResponseEntity.internalServerError().build();

    }

    @PatchMapping("/api/v1/tasks/{id}/updateTodayTaskCompleted")
    public ResponseEntity updateTodayTaskCompleted(@PathVariable Long id){
        Optional<Task> task= taskService.updateTodayTaskCompleted(id);
        if(!task.isEmpty()){
          return    ResponseEntity.ok(task.get());
        }
        return ResponseEntity.internalServerError().build();

    }

    @PostMapping("/api/v1/tasks")
    public ResponseEntity createTask(@RequestBody Task task){
        Task savedTask = taskService.createTask(task);
        if(savedTask ==null ){
            throw new TaskNotCreateException("task not created");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/api/v1/tasks/{id}/delete")
    public ResponseEntity deleteTask(@PathVariable Long id){
        Optional<Task> task = taskService.deleteTask(id);
        if(task.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(task.get());
    }

}
