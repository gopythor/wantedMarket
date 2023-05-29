package com.example.wantedmarket.order.domain.controller;

import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.order.domain.controller.dto.BidDto;
import com.example.wantedmarket.order.domain.model.Bid;
import com.example.wantedmarket.order.domain.service.BidService;
import com.example.wantedmarket.user.domain.common.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/bid")
@RestController
public class BidContorller {

  private final JwtAuthenticationProvider provider;
  private final BidService bidService;

  @PostMapping("/auction/{number}")
  public ResponseEntity<BidDto> bidding(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @PathVariable Long number,
      @RequestParam(value = "bidding price")Long bid) {
    UserVo vo = provider.getUserVo(token);
    return ResponseEntity.ok(BidDto.from(bidService.bidSave(vo.getUserId(), number, bid)));
  }


}
