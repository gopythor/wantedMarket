package com.example.wantedmarket.user.domain.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
  @ApiModelProperty(value = "사용자 ID", required = true, example = "user")
  private String userId;
  @ApiModelProperty(value = "사용자 PW", required = true, example = "1")
  private String password;
  @ApiModelProperty(value = "사용자 이름", required = true, example = "name")
  private String name;
  @ApiModelProperty(value = "사용자 별명", required = true, example = "nickname")
  private String nickName;
  @ApiModelProperty(value = "사용자 전화번호", required = true, example = "010-8888-8888")
  private String phone;
  @ApiModelProperty(value = "사용자 E-Mail", required = true, example = "naver@naver.com")
  private String email;

  public SignUpForm toServiceDto(){
    return new SignUpForm(userId, password, name, nickName, phone,email);
  }
}
