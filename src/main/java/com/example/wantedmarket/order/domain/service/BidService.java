package com.example.wantedmarket.order.domain.service;

import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_AUCTION;
import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_USER;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.order.domain.controller.dto.BidDto;
import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.model.Bid;
import com.example.wantedmarket.order.domain.repository.AuctionRepository;
import com.example.wantedmarket.order.domain.repository.BidRepository;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BidService {
  private final BidRepository bidRepository;
  private final UserRepository userRepository;
  private final AuctionRepository auctionRepository;

  @Transactional
  public Bid bidSave(String userId, Long auction_number, Long bidPrice){
    User user = userRepository.findByUserId(userId).orElseThrow(
        () -> new CustomException(NOT_FOUND_USER));
    Auction auction = auctionRepository.findById(auction_number).orElseThrow(
        () -> new CustomException(NOT_FOUND_AUCTION));

    // 역경매가 삭제되었거나 정상적인 상태가 아님.
    if(auction.getAuctionActive().equals(false)){
      throw new CustomException(NOT_FOUND_AUCTION);
    }

    BidDto dto = new BidDto();
    dto.setUser(user);
    dto.setAuction(auction);
    dto.setBid_record(bidPrice);

    return bidRepository.save(Bid.from(dto));
  }

}
