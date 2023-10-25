package com.example.streakapp.services;

import com.example.streakapp.entities.Task;
import com.example.streakapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService{

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        if(tasks==null || tasks.size()==0){
            return null;
        }
        return tasks;
    }

    public Task createTask(Task task) {
        task.setCreatedDate(LocalDate.now());
        task.setLastTimeTaskCompletedDate(LocalDate.now().minusDays(1));
        task.setTodayTaskCompleted(false);
        task.setDone(false);
        task.setStreakCount(0);
        task.setTodayTaskCompleted(false);
        Task savedTask = taskRepository.save(task);
        if(savedTask==null){
            return null;
        }
        return savedTask;
    }
    public Optional<Task> updateDone(Long id, Boolean status){
        Optional<Task> task = taskRepository.findById(id);

        if(task.isEmpty()){
            return Optional.empty();
        }
        task.get().setDone(status);
        Task savedTask = taskRepository.save(task.get());
        return Optional.of(savedTask);

    }

    public Optional<Task> updateTodayTaskCompleted(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if(!task.isEmpty()){
//            LocalDate lastTaskCompletedDate = task.get().getLastTimeTaskCompletedDate();;
//            int days = Period.between(lastTaskCompletedDate,LocalDate.now()).getDays();
//            if(days>0){
//                task.get().setStreakCount(task.get().getStreakCount() +1);
//            }
            if(!task.get().isTodayTaskCompleted()){
                task.get().setStreakCount(task.get().getStreakCount() +1);
            }
            task.get().setTodayTaskCompleted(true);
            task.get().setLastTimeTaskCompletedDate(LocalDate.now());
            taskRepository.save(task.get());
            return task;

        }

        return Optional.empty();

    }

    public Optional<Task> deleteTask(Long taskId) {
        Optional<Task> task =taskRepository.findById(taskId);
        if(task.isEmpty()){
            return Optional.empty();
        }
        taskRepository.delete(task.get());
        return task;
    }
}
