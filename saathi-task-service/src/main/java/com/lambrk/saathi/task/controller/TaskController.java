package com.lambrk.saathi.task.controller;

import com.lambrk.saathi.task.dto.request.AcceptTaskRequest;
import com.lambrk.saathi.task.dto.request.CreateTaskRequest;
import com.lambrk.saathi.task.dto.request.TaskStatusRequest;
import com.lambrk.saathi.task.dto.response.ApiResponse;
import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.service.TaskApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskApplicationService taskService;

    public TaskController(TaskApplicationService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ApiResponse<Task> create(@Valid @RequestBody CreateTaskRequest request) {
        return ApiResponse.success("Task created successfully", taskService.create(request));
    }

    @GetMapping("/{taskId}")
    public ApiResponse<Task> get(@PathVariable Long taskId) {
        return ApiResponse.success("Task fetched successfully", taskService.get(taskId));
    }

    @GetMapping("/customer/{customerId}")
    public ApiResponse<List<Task>> customerTasks(@PathVariable Long customerId) {
        return ApiResponse.success("Customer tasks fetched successfully", taskService.customerTasks(customerId));
    }

    @GetMapping("/partner/{partnerId}")
    public ApiResponse<List<Task>> partnerTasks(@PathVariable Long partnerId) {
        return ApiResponse.success("Partner tasks fetched successfully", taskService.partnerTasks(partnerId));
    }

    @GetMapping("/nearby")
    public ApiResponse<List<Task>> nearbyTasks() {
        return ApiResponse.success("Nearby tasks fetched successfully", taskService.nearbyTasks());
    }

    @PostMapping("/{taskId}/accept")
    public ApiResponse<Task> accept(@PathVariable Long taskId, @Valid @RequestBody AcceptTaskRequest request) {
        return ApiResponse.success("Task accepted successfully", taskService.accept(taskId, request));
    }

    @PutMapping("/{taskId}/status")
    public ApiResponse<Task> updateStatus(@PathVariable Long taskId, @Valid @RequestBody TaskStatusRequest request) {
        return ApiResponse.success("Task status updated successfully", taskService.updateStatus(taskId, request));
    }

    @GetMapping("/admin/counts/active")
    public ApiResponse<Long> activeTaskCount() {
        return ApiResponse.success("Active task count fetched successfully", taskService.activeTaskCount());
    }
}
