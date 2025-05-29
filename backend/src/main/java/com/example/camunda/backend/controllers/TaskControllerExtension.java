package com.example.camunda.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.camunda.backend.services.TaskServiceExtension;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.exception.TaskListException;

/**
 * Extension of TaskController that adds database-backed round-robin assignment functionality
 * without modifying the original TaskController class.
 */
@RestController
@RequestMapping("/tasks-db")
public class TaskControllerExtension {

    private final TaskServiceExtension taskServiceExtension;

    public TaskControllerExtension(TaskServiceExtension taskServiceExtension) {
        this.taskServiceExtension = taskServiceExtension;
    }

    @PostMapping("/{taskId}/claim-role/{role}")
    public ResponseEntity<Task> claimTaskForRole(
            @PathVariable String taskId,
            @PathVariable String role) throws TaskListException {
        Task task = taskServiceExtension.claimTaskForRole(taskId, role);
        return ResponseEntity.ok(task);
    }
}