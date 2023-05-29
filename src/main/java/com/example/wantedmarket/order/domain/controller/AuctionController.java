package com.example.wantedmarket.order.domain.controller;

import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.order.domain.controller.dto.AuctionDto;
import com.example.wantedmarket.order.domain.model.Auction;
import com.example.wantedmarket.order.domain.service.AuctionService;
import com.example.wantedmarket.user.domain.common.UserVo;
import com.example.wantedmarket.user.domain.user.ChangeBalanceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auction")
@RestController
@RequiredArgsConstructor
public class AuctionController {

  private final JwtAuthenticationProvider provider;
  private final AuctionService auctionService;

  @PostMapping
  public ResponseEntity<AuctionDto> post(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody AuctionDto dto){
    UserVo vo = provider.getUserVo(token);
    return ResponseEntity.ok(AuctionDto.from(auctionService.postAuction(vo.getUserId(),dto)));
  }
}
