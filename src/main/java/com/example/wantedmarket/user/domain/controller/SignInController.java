package com.example.wantedmarket.user.domain.controller;

import com.example.wantedmarket.user.domain.application.SignInApplication;
import com.example.wantedmarket.user.domain.controller.dto.SignInForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-in")
@RequiredArgsConstructor
public class SignInController {

  private final SignInApplication signInApplication;

    @PostMapping("/user")
  public ResponseEntity<String> signInUser(@RequestBody SignInForm form){
            return ResponseEntity.ok(signInApplication.userloginToken(form
                .toServiceDto()));
    }
}
