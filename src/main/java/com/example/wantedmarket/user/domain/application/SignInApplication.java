package com.example.wantedmarket.user.domain.application;

import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_AUCTION;
import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_USER;

import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.exception.ErrorCode;
import com.example.wantedmarket.user.domain.common.UserType;
import com.example.wantedmarket.user.domain.controller.dto.SignInForm;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInApplication {

  private final UserService userService;
  private final JwtAuthenticationProvider provider;


  public String userLoginToken(SignInForm form){
    // 1. 로그인 가능 여부
    User u = userService.findValidUser(form.getUserId(), form.getPassword())
        .orElseThrow(()-> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
    // 비활성화 중이면 로그인 불가
    if(u.getActive().equals(false)){
      throw new CustomException(NOT_FOUND_USER);
    }
    // 2. 토큰을 발행하고
    // 3. 토큰을 response한다.
    return provider.createToken(u.getUserId(), u.getId(), UserType.CUSTOMER);
  }

}
