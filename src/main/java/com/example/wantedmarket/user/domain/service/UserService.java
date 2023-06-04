package com.example.wantedmarket.user.domain.service;

import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_USER;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public Optional<User> findByIdAndUserId(Long id, String userId){
    return userRepository.findById(id)
        .stream().filter(user->user.getUserId().equals(userId))
        .findFirst();
  }
  public Optional<User> findValidUser(String userId, String password){
    return userRepository.findByUserId(userId).stream().filter(
        user -> user.getPassword().equals(password))
        .findFirst();
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }


  public User validUser(String userId){
    User user = userRepository.findByUserIdAndActive(userId, true)
        .orElseThrow(() -> new CustomException(NOT_FOUND_USER));
    return user;
  }
  public void deactivateId(String userId){
    User user = validUser(userId);
    user.setActive(false);
  }
}
