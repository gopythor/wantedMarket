package com.example.wantedmarket.user.domain.repository;

import com.example.wantedmarket.user.domain.model.UserBalanceHistory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface UserBalanceHistoryRepository extends JpaRepository<UserBalanceHistory, Long> {

  Optional<UserBalanceHistory> findFirstByUser_UserIdOrderByIdDesc(@RequestParam("user_id") String userId);
}
