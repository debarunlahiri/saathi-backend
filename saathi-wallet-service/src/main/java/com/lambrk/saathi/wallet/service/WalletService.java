package com.lambrk.saathi.wallet.service;

import com.lambrk.saathi.wallet.dto.CreateEarningRequest;
import com.lambrk.saathi.wallet.dto.PayoutRequest;
import com.lambrk.saathi.wallet.entity.PartnerEarning;
import com.lambrk.saathi.wallet.entity.PartnerPayout;
import com.lambrk.saathi.wallet.entity.PartnerWallet;
import com.lambrk.saathi.wallet.repository.PartnerEarningRepository;
import com.lambrk.saathi.wallet.repository.PartnerPayoutRepository;
import com.lambrk.saathi.wallet.repository.PartnerWalletRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
  private final PartnerWalletRepository walletRepository;
  private final PartnerEarningRepository earningRepository;
  private final PartnerPayoutRepository payoutRepository;

  public WalletService(
      PartnerWalletRepository walletRepository,
      PartnerEarningRepository earningRepository,
      PartnerPayoutRepository payoutRepository) {
    this.walletRepository = walletRepository;
    this.earningRepository = earningRepository;
    this.payoutRepository = payoutRepository;
  }

  public PartnerWallet wallet(UUID partnerId) {
    return walletRepository
        .findByPartnerId(partnerId)
        .orElseGet(
            () -> {
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

  public List<PartnerEarning> earnings(UUID partnerId) {
    return earningRepository.findByPartnerIdOrderByIdDesc(partnerId);
  }

  @Transactional
  public PartnerPayout payout(PayoutRequest request) {
    PartnerWallet wallet = wallet(request.partnerId());
    BigDecimal available = wallet.getAvailableBalance();
    if (available.compareTo(request.amount()) < 0) {
      throw new IllegalArgumentException(
          "Insufficient available balance. Available: "
              + available
              + ", Requested: "
              + request.amount());
    }
    wallet.setAvailableBalance(available.subtract(request.amount()));
    wallet.setTotalWithdrawn(wallet.getTotalWithdrawn().add(request.amount()));
    PartnerPayout payout = new PartnerPayout();
    payout.setPartnerId(request.partnerId());
    payout.setAmount(request.amount());
    payout.setPayoutMethod(request.payoutMethod());
    payout.setStatus("INITIATED");
    return payoutRepository.save(payout);
  }

  public List<PartnerPayout> payouts(UUID partnerId) {
    return payoutRepository.findByPartnerIdOrderByIdDesc(partnerId);
  }

  @Transactional
  public PartnerWallet settleEarnings(UUID partnerId) {
    PartnerWallet wallet = wallet(partnerId);
    BigDecimal pending = wallet.getPendingBalance();
    if (pending.compareTo(BigDecimal.ZERO) > 0) {
      wallet.setAvailableBalance(wallet.getAvailableBalance().add(pending));
      wallet.setPendingBalance(BigDecimal.ZERO);
    }
    return wallet;
  }
}
