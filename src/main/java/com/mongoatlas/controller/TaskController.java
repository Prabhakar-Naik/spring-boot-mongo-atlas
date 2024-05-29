package com.mongoatlas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongoatlas.model.Task;
import com.mongoatlas.service.TaskServicesImpl;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskServicesImpl services;

    @PostMapping("/createTask")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
       return services.addTask(task);
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return services.getAllTasks();
    }

    @GetMapping("/getTask/{taskId}")
    public Task getByTaskId(@PathVariable String taskId){
        return services.getByTaskId(taskId);
    }
    
    @DeleteMapping("/deleteTaskBy/{taskId}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("taskId") String taskId) {
    	try {
    		services.deleteTaskById(taskId);
    	}catch(Exception e) {
    		return new ResponseEntity<String>("Not Deleted..!",HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	return new ResponseEntity<String>("Successfully deleted @",HttpStatus.OK);
    }
    
    @GetMapping("/getTasksBySeverity/{severity}")
    public List<Task> getTasksBySeverity(@PathVariable int severity){
        return services.getBySeverity(severity);
    }
    
    @GetMapping("/getByAssignee/{assignee}")
    public List<Task> getTasksByAssignee(@PathVariable String assignee){
        return services.getByAssignee(assignee);
    }


}

