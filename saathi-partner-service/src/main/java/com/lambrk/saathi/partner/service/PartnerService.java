package com.lambrk.saathi.partner.service;

import com.lambrk.saathi.partner.dto.request.*;
import com.lambrk.saathi.partner.entity.PartnerKyc;
import com.lambrk.saathi.partner.entity.PartnerProfile;
import com.lambrk.saathi.partner.enums.AvailabilityStatus;
import com.lambrk.saathi.partner.enums.KycStatus;
import com.lambrk.saathi.partner.repository.PartnerKycRepository;
import com.lambrk.saathi.partner.repository.PartnerProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PartnerService {
    private final PartnerProfileRepository partnerRepository;
    private final PartnerKycRepository kycRepository;

    public PartnerService(PartnerProfileRepository partnerRepository, PartnerKycRepository kycRepository) {
        this.partnerRepository = partnerRepository;
        this.kycRepository = kycRepository;
    }

    public PartnerProfile createOrUpdate(PartnerProfileRequest request) {
        PartnerProfile partner = partnerRepository.findByIdentityUserId(request.identityUserId()).orElseGet(PartnerProfile::new);
        partner.setIdentityUserId(request.identityUserId());
        partner.setFullName(request.fullName());
        partner.setMobileNumber(request.mobileNumber());
        partner.setEmail(request.email());
        partner.setAddress(request.address());
        if (request.serviceRadiusKm() != null) {
            partner.setServiceRadiusKm(request.serviceRadiusKm());
        }
        return partnerRepository.save(partner);
    }

    public PartnerProfile get(Long partnerId) {
        return partnerRepository.findById(partnerId).orElseThrow();
    }

    @Transactional
    public PartnerKyc submitKyc(Long partnerId, KycRequest request) {
        PartnerProfile partner = get(partnerId);
        PartnerKyc kyc = kycRepository.findByPartnerId(partnerId).orElseGet(PartnerKyc::new);
        kyc.setPartner(partner);
        kyc.setAadhaarUrl(request.aadhaarUrl());
        kyc.setPanUrl(request.panUrl());
        kyc.setAddressProofUrl(request.addressProofUrl());
        kyc.setProfilePhotoUrl(request.profilePhotoUrl());
        kyc.setBankAccountOrUpi(request.bankAccountOrUpi());
        kyc.setStatus(KycStatus.PENDING);
        partner.setKycStatus(KycStatus.PENDING);
        return kycRepository.save(kyc);
    }

    @Transactional
    public PartnerProfile availability(Long partnerId, AvailabilityRequest request) {
        PartnerProfile partner = get(partnerId);
        partner.setAvailabilityStatus(request.availabilityStatus());
        return partner;
    }

    @Transactional
    public PartnerProfile location(Long partnerId, LocationRequest request) {
        PartnerProfile partner = get(partnerId);
        partner.setCurrentLatitude(request.latitude());
        partner.setCurrentLongitude(request.longitude());
        return partner;
    }

    public List<PartnerProfile> availablePartners() {
        return partnerRepository.findByAvailabilityStatusAndKycStatus(AvailabilityStatus.ONLINE, KycStatus.APPROVED);
    }
}
