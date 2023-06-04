package com.example.wantedmarket.order.domain.service;

import static com.example.wantedmarket.exception.ErrorCode.NOT_ENOUGH_PRICE;
import static com.example.wantedmarket.exception.ErrorCode.NOT_ENOUGH_QTY;
import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_AUCTION;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.order.domain.controller.dto.AuctionDto;
import com.example.wantedmarket.order.domain.controller.dto.DeleteAuctionForm;
import com.example.wantedmarket.order.domain.controller.dto.UpdateAuctionForm;
import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.repository.AuctionRepository;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuctionService {
  private final AuctionRepository auctionRepository;


  @Transactional
  public Auction postAuction(String userId, AuctionDto dto){
      // 수량은 1개 이상
      if(dto.getAuctionQty() <1){
        throw new CustomException(NOT_ENOUGH_QTY);
      }

      // 최저 등록 가능 금액은 0 원
      if(dto.getAuctionPrice() < 0){
        throw new CustomException(NOT_ENOUGH_PRICE);
      }
      return auctionRepository.save(Auction.from(userId,dto));
  }

  @Transactional
  public Auction modifyAuction(String userId, UpdateAuctionForm dto){
    // 수량은 1개 이상
    if(dto.getAuctionQty() <1){
      throw new CustomException(NOT_ENOUGH_QTY);
    }

    // 최저 등록 가능 금액은 0 원
    if(dto.getAuctionPrice() < 0){
      throw new CustomException(NOT_ENOUGH_PRICE);
    }

    Auction auction = auctionRepository.findByUserIdAndAuctionNumber(
        userId, dto.getAuctionNumber()).orElseThrow(
        () -> new CustomException(NOT_FOUND_AUCTION));

    // 역경매가 삭제되었거나 정상적인 상태가 아님.
    if(auction.getAuctionActive().equals(false)){
      throw new CustomException(NOT_FOUND_AUCTION);
    }

    auction.setAuctionCategory(dto.getAuctionCategory());
    auction.setAuctionTitle(dto.getAuctionTitle());
    auction.setAuctionDescription(dto.getAuctionDescription());
    auction.setAuctionPrice(dto.getAuctionPrice());
    auction.setAuctionQty(dto.getAuctionQty());
    return auction;
  }

  @Transactional
  public void deleteAuction(String userId, DeleteAuctionForm dto){
    Auction auction = auctionRepository.findByUserIdAndAuctionNumber(
        userId, dto.getAuctionNumber()).orElseThrow(
        () -> new CustomException(NOT_FOUND_AUCTION));

    // 역경매가 삭제되었거나 정상적인 상태가 아님.
    if(auction.getAuctionActive().equals(false)){
      throw new CustomException(NOT_FOUND_AUCTION);
    }

    auction.setAuctionActive(false);
  }

}
