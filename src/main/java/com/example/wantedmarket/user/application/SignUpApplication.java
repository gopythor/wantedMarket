package com.example.wantedmarket.user.application;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.exception.ErrorCode;
import com.example.wantedmarket.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.wantedmarket.user.domain.SignUpForm;
import com.example.wantedmarket.user.domain.service.SignUpService;

@Service
@RequiredArgsConstructor
public class SignUpApplication {
  private final SignUpService signUpService;

  public String SignUp(SignUpForm form){
    if(signUpService.isUserIdExist(form.getUserId())){
      throw new CustomException(ErrorCode.ALREADY_REGISTER_USERID);
    }
    if(signUpService.isEmailExist(form.getEmail())){
      throw new CustomException(ErrorCode.ALREADY_REGISTER_EMAIL);
    }
    User u = signUpService.signUp(form);
    return "회원 가입에 성공하였습니다.";
  }

}
