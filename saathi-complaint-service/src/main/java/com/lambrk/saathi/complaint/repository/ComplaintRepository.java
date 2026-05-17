package com.lambrk.saathi.complaint.repository;

import com.lambrk.saathi.complaint.entity.Complaint;
import com.lambrk.saathi.complaint.enums.ComplaintStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
  List<Complaint> findByRaisedByOrderByIdDesc(Long raisedBy);

  long countByStatus(ComplaintStatus status);
}
