package com.lambrk.saathi.identity.service;

import com.lambrk.saathi.identity.client.ProfileProvisioningClient;
import com.lambrk.saathi.identity.dto.request.FirebaseAuthRequest;
import com.lambrk.saathi.identity.dto.request.LoginRequest;
import com.lambrk.saathi.identity.dto.request.RegisterRequest;
import com.lambrk.saathi.identity.dto.response.AuthResponse;
import com.lambrk.saathi.identity.entity.User;
import com.lambrk.saathi.identity.enums.UserRole;
import com.lambrk.saathi.identity.repository.UserRepository;
import com.lambrk.saathi.identity.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final ProfileProvisioningClient profileProvisioningClient;
  private final FirebaseAuthService firebaseAuthService;

  public AuthService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      ProfileProvisioningClient profileProvisioningClient,
      FirebaseAuthService firebaseAuthService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.profileProvisioningClient = profileProvisioningClient;
    this.firebaseAuthService = firebaseAuthService;
  }

  public AuthResponse register(RegisterRequest request, UserRole role) {
    if (userRepository.existsByMobileNumber(request.mobileNumber())) {
      throw new IllegalArgumentException("Mobile number already registered");
    }
    User user = new User();
    user.setFullName(request.fullName());
    user.setMobileNumber(request.mobileNumber());
    user.setEmail(request.email());
    user.setPasswordHash(passwordEncoder.encode(request.password()));
    user.setRole(role);
    user = userRepository.save(user);
    if (role == UserRole.CUSTOMER) {
      profileProvisioningClient.createCustomerProfile(user);
    } else if (role == UserRole.PARTNER) {
      profileProvisioningClient.createPartnerProfile(user);
    }
    return response(user);
  }

  public AuthResponse login(LoginRequest request) {
    User user =
        userRepository
            .findByMobileNumber(request.mobileNumber())
            .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
    if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
      throw new IllegalArgumentException("Invalid credentials");
    }
    return response(user);
  }

  public AuthResponse refresh(String refreshToken) {
    User user =
        userRepository
            .findById(jwtService.subject(refreshToken))
            .orElseThrow(() -> new IllegalArgumentException("Invalid token"));
    return response(user);
  }

  public AuthResponse firebaseLogin(FirebaseAuthRequest request, UserRole role) {
    String phoneNumber = firebaseAuthService.verifyIdToken(request.idToken());
    return userRepository
        .findByMobileNumber(phoneNumber)
        .map(this::response)
        .orElseGet(() -> firebaseRegister(phoneNumber, request, role));
  }

  private AuthResponse firebaseRegister(
      String phoneNumber, FirebaseAuthRequest request, UserRole role) {
    User user = new User();
    user.setFullName(request.fullName() != null ? request.fullName() : "User");
    user.setMobileNumber(phoneNumber);
    user.setEmail(request.email());
    user.setRole(role);
    user = userRepository.save(user);
    if (role == UserRole.CUSTOMER) {
      profileProvisioningClient.createCustomerProfile(user);
    } else if (role == UserRole.PARTNER) {
      profileProvisioningClient.createPartnerProfile(user);
    }
    return response(user);
  }

  private AuthResponse response(User user) {
    return new AuthResponse(
        user.getId(), user.getRole(), jwtService.accessToken(user), jwtService.refreshToken(user));
  }
}
