package com.example.wantedmarket.user.domain.service;

import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import java.util.Locale;
import com.example.wantedmarket.user.domain.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
  private final UserRepository userRepository;

  public User signUp(SignUpForm form){
    return userRepository.save(User.from(form));
  }

  public boolean isEmailExist(String email){
    return userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
        .isPresent();
  }
  public boolean isUserIdExist(String userId){
    return userRepository.findByUserId(userId.toLowerCase(Locale.ROOT))
        .isPresent();
  }

}
