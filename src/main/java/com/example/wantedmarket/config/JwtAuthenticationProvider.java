package com.example.wantedmarket.config;

import static com.example.wantedmarket.exception.ErrorCode.NOT_FOUND_USER;

import com.example.wantedmarket.exception.CustomException;
import com.example.wantedmarket.user.domain.common.UserVo;
import com.example.wantedmarket.user.domain.common.UserType;
import com.example.wantedmarket.user.domain.model.User;
import com.example.wantedmarket.user.domain.service.UserService;
import com.example.wantedmarket.util.Aes256Util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;

public class JwtAuthenticationProvider {

  private String secretKey = "secretKey";
  private long tokenValidTime = 1000L * 60 * 60 * 24;
  private UserService userService;

  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }


  public String createToken(String userPk, Long id, UserType userType){
    Claims claims = Jwts.claims().setSubject(Aes256Util.encrypt(userPk))
        .setId(Aes256Util.encrypt(id.toString()));
    claims.put("roles", userType);
    Date now = new Date();
    return Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(now.getTime()+tokenValidTime))
        .signWith(SignatureAlgorithm.HS256,secretKey)
        .compact();

  }
  public boolean validateToken(String jwtToken){
    try{
      Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey)
          .parseClaimsJws(jwtToken);
      return !claimsJws.getBody().getExpiration().before(new Date());
    }catch (Exception e){
      return false;
    }
  }
  public UserVo getUserVo(String token){
    Claims c = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
        .getBody();
    UserVo userVo = new UserVo(Long.valueOf(Objects.requireNonNull(Aes256Util
        .decrypt(c.getId()))), Aes256Util.decrypt(c.getSubject()));

    if(!userService.validUser(userVo.getUserId()).getActive()){
      throw new CustomException(NOT_FOUND_USER);
    }

    return userVo;
  }

}
