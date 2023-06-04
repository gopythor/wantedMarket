package com.example.wantedmarket.user.domain.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {
  private String password;
  private String name;
  private String nickName;
  private String phone;
  private String email;
  private String profile;
}
