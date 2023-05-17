package com.example.wantedmarket.config.filter;


import com.example.wantedmarket.config.JwtAuthenticationProvider;
import com.example.wantedmarket.user.domain.common.UserVo;
import com.example.wantedmarket.user.domain.service.UserService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@WebFilter(urlPatterns = "/user/*")
@RequiredArgsConstructor
public class UserFilter implements Filter {

  private final JwtAuthenticationProvider jwtAuthenticationProvider;
  private final UserService userService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader("X-AUTH-TOKEN");
    if(!jwtAuthenticationProvider.validateToken(token)){
      throw new ServletException("Invalid Access");
    }
    UserVo vo = jwtAuthenticationProvider.getUserVo(token);
    userService.findById(vo.getId()).orElseThrow(
        ()->new ServletException("Invalid access")
    );
    chain.doFilter(request,response);
  }
}
