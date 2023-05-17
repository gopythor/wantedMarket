package com.example.wantedmarket.user.domain.controller;

import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.wantedmarket.user.domain.controller.dto.SignUpForm;

@RestController
@RequestMapping("/signUp")
@RequiredArgsConstructor
public class SignUpController {
  private final SignUpService signUpService;

  @PostMapping("/user")
  public ResponseEntity<User> SignUp(@RequestBody SignUpForm form){
    return ResponseEntity.ok(signUpService.signUp(form.toServiceDto()));
  }

}
