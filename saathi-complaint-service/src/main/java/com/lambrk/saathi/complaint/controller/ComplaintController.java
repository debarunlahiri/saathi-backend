package com.lambrk.saathi.complaint.controller;

import com.lambrk.saathi.complaint.dto.ApiResponse;
import com.lambrk.saathi.complaint.dto.CreateComplaintRequest;
import com.lambrk.saathi.complaint.dto.UpdateComplaintStatusRequest;
import com.lambrk.saathi.complaint.entity.Complaint;
import com.lambrk.saathi.complaint.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintService service;
    public ComplaintController(ComplaintService service) { this.service = service; }
    @PostMapping public ApiResponse<Complaint> create(@Valid @RequestBody CreateComplaintRequest request) { return ApiResponse.success("Complaint created successfully", service.create(request)); }
    @GetMapping("/users/{raisedBy}") public ApiResponse<List<Complaint>> mine(@PathVariable Long raisedBy) { return ApiResponse.success("Complaints fetched successfully", service.mine(raisedBy)); }
    @GetMapping public ApiResponse<List<Complaint>> all() { return ApiResponse.success("Complaints fetched successfully", service.all()); }
    @GetMapping("/admin/counts/open") public ApiResponse<Long> openCount() { return ApiResponse.success("Open complaint count fetched successfully", service.openCount()); }
    @PutMapping("/{id}/status") public ApiResponse<Complaint> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateComplaintStatusRequest request) { return ApiResponse.success("Complaint updated successfully", service.updateStatus(id, request)); }
}
