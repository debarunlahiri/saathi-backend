package com.lambrk.saathi.identity.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthService {

  private final FirebaseAuth firebaseAuth;

  public FirebaseAuthService(FirebaseAuth firebaseAuth) {
    this.firebaseAuth = firebaseAuth;
  }

  public String verifyIdToken(String idToken) {
    try {
      FirebaseToken decoded = firebaseAuth.verifyIdToken(idToken);
      String phone = (String) decoded.getClaims().get("phone_number");
      if (phone == null) {
        throw new IllegalArgumentException("Firebase token does not contain phone_number claim");
      }
      return phone;
    } catch (FirebaseAuthException e) {
      throw new IllegalArgumentException("Invalid Firebase ID token: " + e.getMessage());
    }
  }
}
