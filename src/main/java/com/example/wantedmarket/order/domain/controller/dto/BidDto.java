package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.model.Bid;
import com.example.wantedmarket.user.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BidDto {
  private Long bid_record;
  private User user;
  private Auction auction;

  public static BidDto from(Bid bid){
    return new BidDto(bid.getBid_record(), bid.getUser(), bid.getAuction());
  }


}
