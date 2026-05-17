package com.lambrk.saathi.identity.controller;

import com.lambrk.saathi.identity.dto.response.ApiResponse;
import com.lambrk.saathi.identity.entity.User;
import com.lambrk.saathi.identity.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/{id}")
  public ApiResponse<UserDto> getUser(@PathVariable Long id) {
    User user =
        userRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
    return ApiResponse.success(
        "User fetched successfully",
        new UserDto(user.getId(), user.getFullName(), user.getMobileNumber(), user.getEmail()));
  }

  public record UserDto(Long id, String fullName, String mobileNumber, String email) {}
}
