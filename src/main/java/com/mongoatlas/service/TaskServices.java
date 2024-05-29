package com.mongoatlas.service;

import java.util.List;

import com.mongoatlas.model.Task;

public interface TaskServices {

	 public Task addTask(Task task);

	    public void deleteTaskById(String taskId);

	    public List<Task> getAllTasks();

	    public Task updateTaskById(Task taskRequest);

	    public Task getByTaskId(String taskId);

	    public List<Task> getBySeverity(int severity);

	    public  List<Task> getByAssignee(String assignee);
}
