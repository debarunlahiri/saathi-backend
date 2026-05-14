package com.lambrk.saathi.task.service;

import com.lambrk.saathi.task.dto.request.AcceptTaskRequest;
import com.lambrk.saathi.task.dto.request.CreateTaskRequest;
import com.lambrk.saathi.task.dto.request.TaskStatusRequest;
import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.entity.TaskStatusHistory;
import com.lambrk.saathi.task.enums.TaskStatus;
import com.lambrk.saathi.task.repository.TaskRepository;
import com.lambrk.saathi.task.repository.TaskStatusHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TaskApplicationService {
    private final TaskRepository taskRepository;
    private final TaskStatusHistoryRepository historyRepository;

    public TaskApplicationService(TaskRepository taskRepository, TaskStatusHistoryRepository historyRepository) {
        this.taskRepository = taskRepository;
        this.historyRepository = historyRepository;
    }

    @Transactional
    public Task create(CreateTaskRequest request) {
        Task task = new Task();
        task.setCustomerId(request.customerId());
        task.setServiceCategoryId(request.serviceCategoryId());
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPickupAddress(request.pickupAddress());
        task.setPickupLatitude(request.pickupLatitude());
        task.setPickupLongitude(request.pickupLongitude());
        task.setDropAddress(request.dropAddress());
        task.setTaskDateTime(request.taskDateTime());
        task.setEstimatedPrice(request.estimatedPrice() == null ? BigDecimal.ZERO : request.estimatedPrice());
        task.setTaskStatus(TaskStatus.SEARCHING_PARTNER);
        task = taskRepository.save(task);
        history(task.getId(), null, TaskStatus.SEARCHING_PARTNER, request.customerId(), "Task created");
        return task;
    }

    public Task get(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow();
    }

    public List<Task> customerTasks(Long customerId) {
        return taskRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    public List<Task> partnerTasks(Long partnerId) {
        return taskRepository.findByPartnerIdOrderByCreatedAtDesc(partnerId);
    }

    public List<Task> nearbyTasks() {
        return taskRepository.findByTaskStatusOrderByCreatedAtDesc(TaskStatus.SEARCHING_PARTNER);
    }

    @Transactional
    public Task accept(Long taskId, AcceptTaskRequest request) {
        Task task = get(taskId);
        if (task.getPartnerId() != null || task.getTaskStatus() != TaskStatus.SEARCHING_PARTNER) {
            throw new IllegalStateException("Task is no longer available");
        }
        TaskStatus oldStatus = task.getTaskStatus();
        task.setPartnerId(request.partnerId());
        task.setTaskStatus(TaskStatus.PARTNER_ASSIGNED);
        history(task.getId(), oldStatus, TaskStatus.PARTNER_ASSIGNED, request.partnerId(), "Accepted by partner");
        return task;
    }

    @Transactional
    public Task updateStatus(Long taskId, TaskStatusRequest request) {
        Task task = get(taskId);
        TaskStatus oldStatus = task.getTaskStatus();
        task.setTaskStatus(request.status());
        if (request.status() == TaskStatus.TASK_COMPLETED) {
            task.setFinalPrice(task.getEstimatedPrice());
        }
        history(task.getId(), oldStatus, request.status(), request.changedBy(), request.remarks());
        return task;
    }

    private void history(Long taskId, TaskStatus oldStatus, TaskStatus newStatus, Long changedBy, String remarks) {
        TaskStatusHistory history = new TaskStatusHistory();
        history.setTaskId(taskId);
        history.setOldStatus(oldStatus);
        history.setNewStatus(newStatus);
        history.setChangedBy(changedBy);
        history.setRemarks(remarks);
        historyRepository.save(history);
    }
}
