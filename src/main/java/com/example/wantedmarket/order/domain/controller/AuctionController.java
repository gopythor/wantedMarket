package com.example.wantedmarket.order.domain.controller;

import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.order.domain.controller.dto.AuctionDto;
import com.example.wantedmarket.order.domain.controller.dto.DeleteAuctionForm;
import com.example.wantedmarket.order.domain.controller.dto.UpdateAuctionForm;
import com.example.wantedmarket.order.domain.service.AuctionService;
import com.example.wantedmarket.user.domain.common.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @PutMapping
  public ResponseEntity<AuctionDto> put(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody UpdateAuctionForm dto){
    UserVo vo = provider.getUserVo(token);
    return ResponseEntity.ok(AuctionDto.from(auctionService.modifyAuction(vo.getUserId(), dto)));
  }

  @DeleteMapping
  public ResponseEntity<?> delete(@RequestHeader(name = "X-AUTH-TOKEN") String token,
      @RequestBody DeleteAuctionForm dto){
    UserVo vo = provider.getUserVo(token);
    auctionService.deleteAuction(vo.getUserId(), dto);
    return ResponseEntity.ok().build();
  }
}
