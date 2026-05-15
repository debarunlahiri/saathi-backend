package com.lambrk.saathi.task.service;

import com.lambrk.saathi.task.dto.request.AcceptTaskRequest;
import com.lambrk.saathi.task.dto.request.CreateTaskRequest;
import com.lambrk.saathi.task.dto.request.TaskStatusRequest;
import com.lambrk.saathi.task.entity.Task;
import com.lambrk.saathi.task.entity.TaskStatusHistory;
import com.lambrk.saathi.task.enums.TaskStatus;
import com.lambrk.saathi.task.client.ChatClient;
import com.lambrk.saathi.task.client.NotificationClient;
import com.lambrk.saathi.task.client.PartnerClient;
import com.lambrk.saathi.task.client.PaymentClient;
import com.lambrk.saathi.task.client.PricingClient;
import com.lambrk.saathi.task.client.WalletClient;
import com.lambrk.saathi.task.repository.TaskRepository;
import com.lambrk.saathi.task.repository.TaskStatusHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TaskApplicationService {
    private static final BigDecimal DEFAULT_PLATFORM_COMMISSION = BigDecimal.valueOf(5);

    private final TaskRepository taskRepository;
    private final TaskStatusHistoryRepository historyRepository;
    private final PricingClient pricingClient;
    private final PaymentClient paymentClient;
    private final PartnerClient partnerClient;
    private final ChatClient chatClient;
    private final NotificationClient notificationClient;
    private final WalletClient walletClient;

    public TaskApplicationService(TaskRepository taskRepository,
                                  TaskStatusHistoryRepository historyRepository,
                                  PricingClient pricingClient,
                                  PaymentClient paymentClient,
                                  PartnerClient partnerClient,
                                  ChatClient chatClient,
                                  NotificationClient notificationClient,
                                  WalletClient walletClient) {
        this.taskRepository = taskRepository;
        this.historyRepository = historyRepository;
        this.pricingClient = pricingClient;
        this.paymentClient = paymentClient;
        this.partnerClient = partnerClient;
        this.chatClient = chatClient;
        this.notificationClient = notificationClient;
        this.walletClient = walletClient;
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
        BigDecimal estimatedPrice = request.estimatedPrice() == null
                ? pricingClient.estimate(request.serviceCategoryId())
                : request.estimatedPrice();
        task.setEstimatedPrice(estimatedPrice);
        task.setTaskStatus(TaskStatus.SEARCHING_PARTNER);
        task = taskRepository.save(task);
        history(task.getId(), null, TaskStatus.SEARCHING_PARTNER, request.customerId(), "Task created");
        paymentClient.createOrder(task.getId(), task.getCustomerId(), task.getEstimatedPrice());
        notificationClient.notifyUser(task.getCustomerId(), "Task created", "Your task is searching for a partner", "TASK_CREATED", task.getId().toString());
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

    public long activeTaskCount() {
        return taskRepository.countByTaskStatusIn(List.of(
                TaskStatus.SEARCHING_PARTNER,
                TaskStatus.PARTNER_ASSIGNED,
                TaskStatus.PARTNER_ON_WAY,
                TaskStatus.PARTNER_REACHED,
                TaskStatus.TASK_STARTED,
                TaskStatus.TASK_IN_PROGRESS
        ));
    }

    @Transactional
    public Task accept(Long taskId, AcceptTaskRequest request) {
        partnerClient.ensurePartnerCanAccept(request.partnerId());
        Task task = get(taskId);
        if (task.getPartnerId() != null || task.getTaskStatus() != TaskStatus.SEARCHING_PARTNER) {
            throw new IllegalStateException("Task is no longer available");
        }
        TaskStatus oldStatus = task.getTaskStatus();
        task.setPartnerId(request.partnerId());
        task.setTaskStatus(TaskStatus.PARTNER_ASSIGNED);
        history(task.getId(), oldStatus, TaskStatus.PARTNER_ASSIGNED, request.partnerId(), "Accepted by partner");
        chatClient.createRoom(task.getId(), task.getCustomerId(), request.partnerId());
        notificationClient.notifyUser(task.getCustomerId(), "Partner assigned", "A partner accepted your task", "TASK_ACCEPTED", task.getId().toString());
        notificationClient.notifyUser(request.partnerId(), "Task accepted", "You accepted a task", "TASK_ACCEPTED", task.getId().toString());
        return task;
    }

    @Transactional
    public Task updateStatus(Long taskId, TaskStatusRequest request) {
        Task task = get(taskId);
        TaskStatus oldStatus = task.getTaskStatus();
        task.setTaskStatus(request.status());
        if (request.status() == TaskStatus.TASK_COMPLETED) {
            task.setFinalPrice(task.getEstimatedPrice());
            if (task.getPartnerId() != null) {
                walletClient.createEarning(task.getId(), task.getPartnerId(), task.getEstimatedPrice(), DEFAULT_PLATFORM_COMMISSION);
                notificationClient.notifyUser(task.getPartnerId(), "Task completed", "Your earnings were added to wallet pending balance", "TASK_COMPLETED", task.getId().toString());
            }
            notificationClient.notifyUser(task.getCustomerId(), "Task completed", "Your task has been marked completed", "TASK_COMPLETED", task.getId().toString());
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
