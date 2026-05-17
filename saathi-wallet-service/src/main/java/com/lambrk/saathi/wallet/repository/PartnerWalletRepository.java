package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerWallet;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerWalletRepository extends JpaRepository<PartnerWallet, UUID> {
  Optional<PartnerWallet> findByPartnerId(UUID partnerId);
}
