package com.example.wantedmarket.user.domain.user;

import com.example.wantedmarket.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String userId;

  public static UserDto from (User user){
    return new UserDto(user.getId(), user.getUserId());
  }
}
