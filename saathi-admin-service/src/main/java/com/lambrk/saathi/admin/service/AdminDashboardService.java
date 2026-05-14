package com.lambrk.saathi.admin.service;

import com.lambrk.saathi.admin.client.*;
import com.lambrk.saathi.admin.dto.DashboardResponse;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardService {
    private final TaskClient taskClient;
    private final PartnerClient partnerClient;
    private final ComplaintClient complaintClient;
    private final PaymentClient paymentClient;

    public AdminDashboardService(TaskClient taskClient, PartnerClient partnerClient, ComplaintClient complaintClient, PaymentClient paymentClient) {
        this.taskClient = taskClient;
        this.partnerClient = partnerClient;
        this.complaintClient = complaintClient;
        this.paymentClient = paymentClient;
    }

    public DashboardResponse dashboard() {
        return new DashboardResponse(taskClient.activeTasks(), partnerClient.pendingPartners(), complaintClient.openComplaints(), paymentClient.paymentIssues());
    }
}
