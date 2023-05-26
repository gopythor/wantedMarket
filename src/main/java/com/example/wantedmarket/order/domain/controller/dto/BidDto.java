package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.model.Bid;
import com.example.wantedmarket.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BidDto {
  private Long bid_record;
  private User user;
  private Auction auction;

  /* Dto -> Entity */
  public Bid toEntity(){
    Bid bid = Bid.builder()
        .bid_record(bid_record)
        .user(user)
        .auction(auction)
        .build();

    return bid;
  }

}
