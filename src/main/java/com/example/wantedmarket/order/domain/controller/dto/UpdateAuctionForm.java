package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.common.AuctionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAuctionForm {
  private Long auctionNumber;
  private AuctionCategory auctionCategory;
  private Long auctionPrice;
  private String auctionTitle;
  private Long auctionQty;
  private String auctionDescription;
}
