package com.example.hw13tasktrackersystem.Controller;

import com.example.hw13tasktrackersystem.ApiResponse.ApiResponse;
import com.example.hw13tasktrackersystem.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/taskTracker/")
public class TaskTrackerController {

    ArrayList<TaskTracker> tasks = new ArrayList<>();

//    Create a new tasks
    @PostMapping("create")
    public ApiResponse createTask(@RequestBody TaskTracker taskTracker){
        tasks.add(taskTracker);
        return new ApiResponse("Task has been added", 200);
    }


//    Display all tasks .
    @GetMapping("display")
    public ArrayList<TaskTracker> displayAllTask(){
        return tasks;
    }


//    Update a task
    @PutMapping("update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody TaskTracker taskTracker){
        tasks.set(index, taskTracker);
        return new ApiResponse("Task ID:"+ taskTracker.getID() + " has been updated", 200);
    }


//    Delete a task
    @DeleteMapping("delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        tasks.remove(index);
        return new ApiResponse("Task Index: "+index  + " has been deleted", 200);
    }


//    Change the task status as done or not done
    @PutMapping("changeStatus")
    public ApiResponse changeTaskStatus(){
        for (TaskTracker tracker: tasks) {
            if (tracker.getStatus().equalsIgnoreCase("Done")){
                tracker.setStatus("Not Done");
            } else if (tracker.getStatus().equalsIgnoreCase("Not Done")) {
                tracker.setStatus("Done");
            } else tracker.setStatus("In progress");
        }
        return new ApiResponse("Task status has been updated", 200);
    }


//    Search for a task by given title
    @GetMapping("search/{title}")
    public ArrayList<TaskTracker> searchByTitle(@PathVariable String title) {
        ArrayList<TaskTracker> newTasks = new ArrayList<>();
        for (TaskTracker taskTracker : tasks) {
            if (taskTracker.getTitle().equalsIgnoreCase(title)){
                newTasks.add(taskTracker);
            }
        }
        return newTasks;
    }
}
