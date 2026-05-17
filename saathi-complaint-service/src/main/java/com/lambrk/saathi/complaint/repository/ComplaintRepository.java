package com.lambrk.saathi.complaint.repository;

import com.lambrk.saathi.complaint.entity.Complaint;
import com.lambrk.saathi.complaint.enums.ComplaintStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, UUID> {
  List<Complaint> findByRaisedByOrderByIdDesc(UUID raisedBy);

  long countByStatus(ComplaintStatus status);
}
