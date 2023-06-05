package com.example.wantedmarket.user.domain.repository;


import com.example.wantedmarket.user.domain.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserId(String userId);
  Optional<User> findByEmail(String email);
  Optional<User> findByUserIdAndActive(String userId, Boolean active);
}
