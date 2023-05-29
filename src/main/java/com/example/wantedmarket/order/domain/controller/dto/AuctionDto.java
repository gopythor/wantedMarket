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
  private String user_id;
  private AuctionCategory auctionCategory;
  private Long auction_price;
  private String auction_title;
  private Long auction_qty;
  private String auction_description;


  public static AuctionDto from(Auction auction){
    return new AuctionDto(auction.getUser_id(), auction.getAuctionCategory(),
        auction.getAuction_price(), auction.getAuction_title(),
        auction.getAuction_qty(), auction.getAuction_description());
  }




}
