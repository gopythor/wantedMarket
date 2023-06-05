package com.example.wantedmarket.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
  ALREADY_REGISTER_USERID(HttpStatus.BAD_REQUEST, "이미 사용되는 아이디입니다."),
  ALREADY_REGISTER_EMAIL(HttpStatus.BAD_REQUEST, "이미 사용되는 이메일입니다."),
  NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "사용자를 찾을 수 없습니다"),

  // login
  LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST,"아이디나 패스워드를 확인해주세요."),

  // money
  NOT_ENOUGH_BALANCE(HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),

  // auction
  NOT_FOUND_AUCTION(HttpStatus.BAD_REQUEST,"입찰 실패 : 해당 역경매 글이 존재하지 않습니다."),
  NOT_ENOUGH_QTY(HttpStatus.BAD_REQUEST, "등록 실패 : 수량은 1개 이상부터 등록이 가능합니다."),
  NOT_ENOUGH_PRICE(HttpStatus.BAD_REQUEST, "등록 실패 : 가격은 0원 이상부터 등록이 가능합니다.");

  private final HttpStatus httpStatus;
  private final String detail;
}
