package com.lambrk.saathi.identity.controller;

import com.lambrk.saathi.identity.dto.request.FirebaseAuthRequest;
import com.lambrk.saathi.identity.dto.request.LoginRequest;
import com.lambrk.saathi.identity.dto.request.RefreshTokenRequest;
import com.lambrk.saathi.identity.dto.request.RegisterRequest;
import com.lambrk.saathi.identity.dto.response.ApiResponse;
import com.lambrk.saathi.identity.dto.response.AuthResponse;
import com.lambrk.saathi.identity.enums.UserRole;
import com.lambrk.saathi.identity.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/customer/register")
  public ApiResponse<AuthResponse> registerCustomer(@Valid @RequestBody RegisterRequest request) {
    return ApiResponse.success(
        "Customer registered successfully", authService.register(request, UserRole.CUSTOMER));
  }

  @PostMapping("/partner/register")
  public ApiResponse<AuthResponse> registerPartner(@Valid @RequestBody RegisterRequest request) {
    return ApiResponse.success(
        "Partner registered successfully", authService.register(request, UserRole.PARTNER));
  }

  @PostMapping("/login")
  public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
    return ApiResponse.success("Login successful", authService.login(request));
  }

  @PostMapping("/refresh-token")
  public ApiResponse<AuthResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
    return ApiResponse.success(
        "Token refreshed successfully", authService.refresh(request.refreshToken()));
  }

  @PostMapping("/firebase/customer/login")
  public ApiResponse<AuthResponse> firebaseCustomerLogin(
      @Valid @RequestBody FirebaseAuthRequest request) {
    return ApiResponse.success(
        "Login successful", authService.firebaseLogin(request, UserRole.CUSTOMER));
  }

  @PostMapping("/firebase/partner/login")
  public ApiResponse<AuthResponse> firebasePartnerLogin(
      @Valid @RequestBody FirebaseAuthRequest request) {
    return ApiResponse.success(
        "Login successful", authService.firebaseLogin(request, UserRole.PARTNER));
  }
}
