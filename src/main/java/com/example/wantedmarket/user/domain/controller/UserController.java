package com.example.wantedmarket.user.domain.controller;


import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.exception.ErrorCode;
import com.example.wantedmarket.user.domain.common.UserVo;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.service.UserBalanceService;
import com.example.wantedmarket.user.domain.service.UserService;
import com.example.wantedmarket.user.domain.user.ChangeBalanceForm;
import com.example.wantedmarket.user.domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final JwtAuthenticationProvider provider;
  private final UserService userService;
  private final UserBalanceService userBalanceService;

  @GetMapping("/get-info")
  public ResponseEntity<UserDto> getInfo(@RequestHeader(name = "X-AUTH-TOKEN") String token){
    UserVo vo = provider.getUserVo(token);
    User u = userService.findByIdAndUserId(vo.getId(), vo.getUserId()).orElseThrow(
        ()->new CustomException(ErrorCode.NOT_FOUND_USER));

    return ResponseEntity.ok(UserDto.from(u));
  }

  @PostMapping("/balance")
  public ResponseEntity<Integer> changeBalance(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody ChangeBalanceForm form){
      UserVo vo = provider.getUserVo(token);

      return ResponseEntity.ok(userBalanceService.changeBalance(vo.getUserId(), form).getCurrentMoney());
  }
}
