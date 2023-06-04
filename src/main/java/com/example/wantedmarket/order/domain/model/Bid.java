package com.example.wantedmarket.order.domain.model;

import com.example.wantedmarket.order.domain.common.Conditions;
import com.example.wantedmarket.order.domain.controller.dto.BidDto;
import com.example.wantedmarket.order.domain.controller.dto.BidForm;
import com.example.wantedmarket.user.domain.model.User;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bids")
@Entity
@Audited
@AuditOverride(forClass =  BaseEntity.class)
public class Bid extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "auction_number")
  private Auction auction;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  private Long bid_record;
  private String description;

  @Enumerated(value = EnumType.STRING)
  private Conditions conditions;

  public static Bid of(Auction auction, User user,  BidForm dto){
    return Bid.builder()
        .auction(auction)
        .user(user)
        .bid_record(dto.getBid_record())
        .description(dto.getDescription())
        .conditions(dto.getConditions())
        .build();
  }
}
