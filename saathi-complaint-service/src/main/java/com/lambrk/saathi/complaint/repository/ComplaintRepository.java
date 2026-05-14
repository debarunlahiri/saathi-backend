package com.lambrk.saathi.complaint.repository;

import com.lambrk.saathi.complaint.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByRaisedByOrderByIdDesc(Long raisedBy);
}
