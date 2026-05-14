package com.lambrk.saathi.wallet.repository;

import com.lambrk.saathi.wallet.entity.PartnerWallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerWalletRepository extends JpaRepository<PartnerWallet, Long> {
    Optional<PartnerWallet> findByPartnerId(Long partnerId);
}
