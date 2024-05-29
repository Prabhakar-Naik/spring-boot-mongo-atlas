package com.mongoatlas.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongoatlas.model.Task;
import com.mongoatlas.repository.TaskRepository;

@Service
public class TaskServicesImpl implements TaskServices {

	@Autowired
	private TaskRepository repository;

	 // CRUD CREATE READ UPDATE DELETE

    @Override
    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    @Override
    public void deleteTaskById(String taskId) {
        repository.deleteById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    @Override
    public Task updateTaskById(Task taskRequest) {

        //get the existing document from db then perform update operation on it

        Task existingTask=repository.findById(taskRequest.getTaskId()).get();
        if(existingTask !=null){
            existingTask.setAssignee(taskRequest.getAssignee());
            existingTask.setDescription(taskRequest.getDescription());
            existingTask.setSeverity(taskRequest.getSeverity());
            existingTask.setStoryPoint(taskRequest.getStoryPoint());

            return repository.save(existingTask);
        }
        return null;
    }

    @Override
    public Task getByTaskId(String taskId) {
        return repository.findById(taskId).get();
    }

    @Override
    public List<Task> getBySeverity(int severity) {
        return repository.findBySeverity(severity);
    }

    @Override
    public List<Task> getByAssignee(String assignee) {
        return repository.findByAssignee(assignee);
    }


}
