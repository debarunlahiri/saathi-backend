package com.lambrk.saathi.partner.repository;

import com.lambrk.saathi.partner.entity.PartnerProfile;
import com.lambrk.saathi.partner.enums.AvailabilityStatus;
import com.lambrk.saathi.partner.enums.KycStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerProfileRepository extends JpaRepository<PartnerProfile, Long> {
  Optional<PartnerProfile> findByIdentityUserId(Long identityUserId);

  List<PartnerProfile> findByAvailabilityStatusAndKycStatus(
      AvailabilityStatus availabilityStatus, KycStatus kycStatus);

  long countByKycStatus(KycStatus kycStatus);
}
