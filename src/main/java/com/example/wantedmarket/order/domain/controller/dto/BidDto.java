package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.common.Conditions;
import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.model.Bid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BidDto {
  private Long auctionId;
  private Long bid_record;
  private Conditions conditions;
  private String description;

  public static BidDto from(Bid bid){
    return BidDto.builder()
        .auctionId(bid.getAuction().getAuctionNumber())
        .bid_record(bid.getBid_record())
        .conditions(bid.getConditions())
        .description(bid.getDescription())
        .build();
  }

}
