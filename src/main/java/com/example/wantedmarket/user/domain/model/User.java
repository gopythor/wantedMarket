package com.example.wantedmarket.user.domain.model;

import com.example.wantedmarket.order.domain.model.Bid;
import com.example.wantedmarket.user.domain.controller.dto.SignUpForm;
import java.util.List;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Getter
@Setter
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class User extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String userId;
  private String password;
  private String name;
  private String nickName;
  private String phone;
  @Column(unique = true)
  private String email;
  private String profile;

  @Column(columnDefinition = "int default 0")
  private Integer balance;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @OrderBy("bid_record asc") // 댓글 정렬
  private List<Bid> bids;

  public static User from(SignUpForm form){
    return User.builder()
        .userId(form.getUserId().toLowerCase(Locale.ROOT))
        .password(form.getPassword())
        .name(form.getName())
        .nickName(form.getNickName())
        .phone(form.getPhone())
        .email(form.getEmail().toLowerCase(Locale.ROOT))
        .profile("default.png")
        .build();
  }


}
