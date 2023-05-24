package com.example.wantedmarket.order.domain.service;

import com.example.wantedmarket.order.domain.controller.dto.AuctionDto;
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
  private final UserRepository userRepository;

  @Transactional
  public Auction postAuction(String userId, AuctionDto dto){

    Auction auction = dto.toServiceDto(userId);

    return auctionRepository.save(auction);
  }
}