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

  NOT_ENOUGH_QTY(HttpStatus.BAD_REQUEST, "등록 실패 : 수량은 1개 이상부터 등록이 가능합니다."),
  NOT_ENOUGH_PRICE(HttpStatus.BAD_REQUEST, "등록 실패 : 가격은 0원 이상부터 등록이 가능합니다."),

  // bid
  NOT_FOUND_AUCTION(HttpStatus.BAD_REQUEST,"입찰 실패 : 해당 역경매 글이 존재하지 않습니다."),
  HIGH_BID_PRICE(HttpStatus.BAD_REQUEST,"입찰 실패 : 요청 입찰 가격이 현재 최저 입찰가보다 높습니다."),
  NOT_FOUND_BID(HttpStatus.BAD_REQUEST,"삭제 실패 : 입찰 가격 삭제가 실패하였습니다.");

  private final HttpStatus httpStatus;
  private final String detail;
}
