package com.example.wantedmarket.order.domain.repository;

import com.example.wantedmarket.order.domain.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
