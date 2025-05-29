package com.example.camunda.backend.services;

import org.springframework.stereotype.Service;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;

/**
 * Extension of TaskService that adds database-backed round-robin assignment functionality
 * without modifying the original TaskService class.
 */
@Service
public class TaskServiceExtension {

    private final CamundaTaskListClient taskListClient;
    private final DbTaskAssignmentService dbTaskAssignmentService;

    
    public TaskServiceExtension(
            CamundaTaskListClient taskListClient,
            DbTaskAssignmentService dbTaskAssignmentService) {
        this.taskListClient = taskListClient;
        this.dbTaskAssignmentService = dbTaskAssignmentService;
    }
    public Task claimTaskForRole(String taskId, String role) throws TaskListException {
        dbTaskAssignmentService.assignTaskToRole(taskId, role);
        return taskListClient.getTask(taskId);
    }
}