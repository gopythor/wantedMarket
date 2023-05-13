package com.example.wantedmarket.user.domain.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.wantedmarket.user.application.SignUpApplication;
import com.example.wantedmarket.user.domain.SignUpForm;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
  private final SignUpApplication signUpApplication;

  @PostMapping
  public ResponseEntity<String> SignUp(@RequestBody SignUpForm form){
    return ResponseEntity.ok(signUpApplication.SignUp(form));
  }

}
