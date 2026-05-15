package com.lambrk.saathi.complaint.service;

import com.lambrk.saathi.complaint.dto.CreateComplaintRequest;
import com.lambrk.saathi.complaint.dto.UpdateComplaintStatusRequest;
import com.lambrk.saathi.complaint.entity.Complaint;
import com.lambrk.saathi.complaint.enums.ComplaintStatus;
import com.lambrk.saathi.complaint.repository.ComplaintRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintService {
    private final ComplaintRepository repository;
    public ComplaintService(ComplaintRepository repository) { this.repository = repository; }
    public Complaint create(CreateComplaintRequest request) {
        Complaint complaint = new Complaint();
        complaint.setTaskId(request.taskId());
        complaint.setRaisedBy(request.raisedBy());
        complaint.setComplaintType(request.complaintType());
        complaint.setDescription(request.description());
        return repository.save(complaint);
    }
    public List<Complaint> mine(Long raisedBy) { return repository.findByRaisedByOrderByIdDesc(raisedBy); }
    public List<Complaint> all() { return repository.findAll(); }
    public long openCount() { return repository.countByStatus(ComplaintStatus.OPEN); }
    @Transactional public Complaint updateStatus(Long id, UpdateComplaintStatusRequest request) {
        Complaint complaint = repository.findById(id).orElseThrow();
        complaint.setStatus(request.status());
        complaint.setAdminRemarks(request.adminRemarks());
        return complaint;
    }
}
