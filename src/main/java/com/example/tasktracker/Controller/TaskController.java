package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResponse.ApiResponse;
import com.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateTask(@PathVariable String id, @RequestBody Task task){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId().equalsIgnoreCase(id)){
                tasks.set(i, task);
                return new ApiResponse("Task updated successfully");
            }
        }
        return new ApiResponse("Task not found");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTask(@PathVariable String id){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId().equalsIgnoreCase(id)){
                tasks.remove(i);
                return new ApiResponse("Task deleted successfully");
            }
        }
        return new ApiResponse("Task not found");
    }

    @PutMapping("/update-status/{id}")
    public ApiResponse updateStatus(@PathVariable String id){
        for (Task task : tasks) {
            if (task.getId().equalsIgnoreCase(id)) {
                task.setDone(!task.isDone());
                return new ApiResponse("Status updated successfully");
            }
        }
        return new ApiResponse("Task not found");
    }

    @GetMapping("/get-by-title/{title}")
    public Task searchTaskByTitle(@PathVariable String title){
        for(Task task: tasks){
            if(task.getTitle().equalsIgnoreCase(title))
                return task;
        }
        return null;
    }


}
