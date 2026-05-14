package com.lambrk.saathi.wallet.service;

import com.lambrk.saathi.wallet.dto.CreateEarningRequest;
import com.lambrk.saathi.wallet.entity.PartnerEarning;
import com.lambrk.saathi.wallet.entity.PartnerWallet;
import com.lambrk.saathi.wallet.repository.PartnerEarningRepository;
import com.lambrk.saathi.wallet.repository.PartnerWalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {
    private final PartnerWalletRepository walletRepository;
    private final PartnerEarningRepository earningRepository;
    public WalletService(PartnerWalletRepository walletRepository, PartnerEarningRepository earningRepository) {
        this.walletRepository = walletRepository;
        this.earningRepository = earningRepository;
    }
    public PartnerWallet wallet(Long partnerId) {
        return walletRepository.findByPartnerId(partnerId).orElseGet(() -> {
            PartnerWallet wallet = new PartnerWallet();
            wallet.setPartnerId(partnerId);
            return walletRepository.save(wallet);
        });
    }
    @Transactional
    public PartnerEarning addEarning(CreateEarningRequest request) {
        BigDecimal net = request.grossAmount().subtract(request.platformCommission());
        PartnerEarning earning = new PartnerEarning();
        earning.setPartnerId(request.partnerId());
        earning.setTaskId(request.taskId());
        earning.setGrossAmount(request.grossAmount());
        earning.setPlatformCommission(request.platformCommission());
        earning.setNetAmount(net);
        PartnerWallet wallet = wallet(request.partnerId());
        wallet.setPendingBalance(wallet.getPendingBalance().add(net));
        wallet.setTotalEarnings(wallet.getTotalEarnings().add(net));
        return earningRepository.save(earning);
    }
    public List<PartnerEarning> earnings(Long partnerId) { return earningRepository.findByPartnerIdOrderByIdDesc(partnerId); }
}
