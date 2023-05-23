package com.example.wantedmarket.user.domain.user;

import lombok.Getter;

@Getter
public class ChangeBalanceForm {
  private String from;
  private String message;
  private Integer money;
}
