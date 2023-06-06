package com.example.wantedmarket.order.domain.repository;

import com.example.wantedmarket.order.domain.model.Auction;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
  Optional<Auction> findByUserIdAndAuctionNumber(String userId, Long auctionNumber);
}
