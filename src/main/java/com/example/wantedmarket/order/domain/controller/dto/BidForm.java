package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.common.Conditions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BidForm {
  private Long bid_record;
  private Conditions conditions;
  private String description;

  public BidForm toService(){
    return new BidForm(bid_record, conditions, description);
  }
}
