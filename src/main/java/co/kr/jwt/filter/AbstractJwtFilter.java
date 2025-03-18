package co.kr.jwt.filter;

import co.kr.jwt.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public abstract class AbstractJwtFilter extends OncePerRequestFilter {
  protected final JwtUtils jwtUtils;
  protected final String header;

  protected AbstractJwtFilter(JwtUtils jwtUtils, String header) {
    if(Objects.isNull(jwtUtils) && Objects.isNull(header)) {
      throw new IllegalArgumentException("JwtUtils and Header must not be null.");
    }
    this.jwtUtils = jwtUtils;
    this.header = header;
  }

  protected abstract void validateJwt(HttpServletRequest request);

  protected abstract String getToken(HttpServletRequest request);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String token = getToken(request);

    // 토큰이 없거나 만료되었을 경우 401 Unauthorized를 응답하고, 필터 체인 진행을 멈춤
    if (token == null || jwtUtils.isExpired(token)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().write("Unauthorized: Invalid or expired token");
      return; // 필터 체인 진행 중단
    }

    validateJwt(request);

    filterChain.doFilter(request, response);
  }

  private String extractJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");

    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
