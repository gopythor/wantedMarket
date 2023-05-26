package com.example.wantedmarket.order.domain.repository;

import com.example.wantedmarket.order.domain.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

}
