package com.example.wantedmarket.user.domain.service;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.exception.ErrorCode;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import java.util.Locale;
import com.example.wantedmarket.user.domain.controller.dto.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
  private final UserRepository userRepository;

  public boolean isEmailExist(String email){
    return userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
        .isPresent();
  }
  public boolean isUserIdExist(String userId){
    return userRepository.findByUserId(userId.toLowerCase(Locale.ROOT))
        .isPresent();
  }


  public User signUp(SignUpForm form){
    if(isUserIdExist(form.getUserId())){
      throw new CustomException(ErrorCode.ALREADY_REGISTER_USERID);
    }
    if(isEmailExist(form.getEmail())){
      throw new CustomException(ErrorCode.ALREADY_REGISTER_EMAIL);
    }
    return userRepository.save(User.from(form));
  }

}
