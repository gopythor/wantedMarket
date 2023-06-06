package com.example.wantedmarket.order.domain.model;

import com.example.wantedmarket.order.domain.common.AuctionCategory;
import com.example.wantedmarket.order.domain.controller.dto.AuctionDto;
import com.example.wantedmarket.user.domain.controller.dto.SignUpForm;
import com.example.wantedmarket.user.domain.model.User;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@AuditOverride(forClass =  BaseEntity.class)
public class Auction extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long auctionNumber;
  private String userId;
  @Enumerated(value = EnumType.STRING)
  private AuctionCategory auctionCategory;
  private Long auctionPrice;
  private String auctionTitle;
  private Long auctionQty;
  private String auctionDescription;

  @OneToMany(mappedBy = "auction", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @OrderBy("bid_record asc") // 가격 정렬
  private List<Bid> bid;


  public static Auction from(String userId, AuctionDto dto) {
  return Auction.builder()
      .userId(userId)
      .auctionCategory(dto.getAuctionCategory())
      .auctionPrice(dto.getAuctionPrice())
      .auctionTitle(dto.getAuctionTitle())
      .auctionQty(dto.getAuctionQty())
      .auctionDescription(dto.getAuctionDescription())
      .build();
  }
}
