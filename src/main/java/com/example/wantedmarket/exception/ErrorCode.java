package com.example.wantedmarket.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  ALREADY_REGISTER_USERID(HttpStatus.BAD_REQUEST, "이미 사용되는 아이디입니다."),
  ALREADY_REGISTER_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용되는 이메일입니다.");
  private final HttpStatus httpStatus;
  private final String detail;
}
