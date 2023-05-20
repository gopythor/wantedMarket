package com.example.wantedmarket.user.domain.service;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.exception.ErrorCode;
import com.example.wantedmarket.user.domain.model.UserBalanceHistory;
import com.example.wantedmarket.user.domain.repository.UserBalanceHistoryRepository;
import com.example.wantedmarket.user.domain.repository.UserRepository;
import com.example.wantedmarket.user.domain.user.ChangeBalanceForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBalanceService {

  private final UserBalanceHistoryRepository userBalanceHistoryRepository;
  private final UserRepository userRepository;

  @Transactional(noRollbackFor = {CustomException.class})
  public UserBalanceHistory changeBalance(String userId, ChangeBalanceForm form) throws CustomException{
    UserBalanceHistory userBalanceHistory =
        userBalanceHistoryRepository.findFirstByUser_UserIdOrderByIdDesc(userId)
            .orElse(UserBalanceHistory.builder()
                .changeMoney(0)
                .currentMoney(0)
                .user(userRepository.findByUserId(userId)
                    .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_USER)))
                .build());
    if(userBalanceHistory.getCurrentMoney() + form.getMoney() < 0){
      throw new CustomException(ErrorCode.NOT_ENOUGH_BALANCE);
    }

    userBalanceHistory = UserBalanceHistory.builder()
        .changeMoney(form.getMoney())
        .currentMoney(userBalanceHistory.getCurrentMoney()+form.getMoney())
        .description(form.getMessage())
        .fromMessage(form.getFrom())
        .user(userBalanceHistory.getUser())
        .build();
    userBalanceHistory.getUser().setBalance(userBalanceHistory.getCurrentMoney());

    return userBalanceHistoryRepository.save(userBalanceHistory);
  }

}
