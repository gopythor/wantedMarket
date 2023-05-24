package com.example.wantedmarket.order.domain.controller.dto;

import com.example.wantedmarket.order.domain.common.AuctionCategory;
import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.user.domain.controller.dto.SignInForm;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AuctionDto {
  private AuctionCategory auctionCategory;
  private Long auction_price;
  private String auction_title;
  private Long auction_qty;
  private String auction_description;

  public Auction toServiceDto(String userId){
    Auction auction = Auction.builder()
        .user_id(userId)
        .auctionCategory(auctionCategory)
        .auction_price(auction_price)
        .auction_title(auction_title)
        .auction_qty(auction_qty)
        .auction_description(auction_description)
        .build();
    return auction;
  }
}
