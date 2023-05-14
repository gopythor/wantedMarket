package com.example.wantedmarket.user.domain.model;

import com.example.wantedmarket.user.domain.controller.dto.SignUpForm;
import java.util.Locale;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditOverride;

@Entity
@Getter
@Builder
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
