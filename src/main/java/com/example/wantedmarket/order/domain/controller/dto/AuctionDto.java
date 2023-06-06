package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.common.AuctionCategory;
import com.example.wantedmarket.order.domain.model.Auction;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AuctionDto {
  @ApiModelProperty(hidden = true)
  private String userId;
  private AuctionCategory auctionCategory;
  private Long auctionPrice;
  private String auctionTitle;
  private Long auctionQty;
  private String auctionDescription;


  public static AuctionDto from(Auction auction){
    return new AuctionDto(auction.getUserId(), auction.getAuctionCategory(),
        auction.getAuctionPrice(), auction.getAuctionTitle(),
        auction.getAuctionQty(), auction.getAuctionDescription());
  }
}
