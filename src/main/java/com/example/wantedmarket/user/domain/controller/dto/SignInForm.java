package com.example.wantedmarket.user.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignInForm {
  private String userId;
  private String password;

  public SignInForm toServiceDto(){
    return new SignInForm(userId, password);
  }
}
