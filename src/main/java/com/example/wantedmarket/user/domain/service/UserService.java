package com.example.wantedmarket.user.domain.service;

import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_USER;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.user.domain.controller.dto.UpdateUserForm;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public void deactivateId(String userId){
    User user = validUser(userId);
    user.setActive(false);
  }

  @Transactional
  public User modifyUser(String userId, UpdateUserForm dto){
    User user = validUser(userId);
    user.setPassword(dto.getPassword());
    user.setName(dto.getName());
    user.setNickName(dto.getNickName());
    user.setPhone(dto.getPhone());
    user.setEmail(dto.getEmail());
    user.setProfile(dto.getProfile());
    return user;
  }

}
