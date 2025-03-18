package co.kr.jwt.util;

import java.util.Map;

public interface JwtUtils {
  String getUsername(String token);
  String getRole(String token);
  Boolean isExpired(String token);
  Map<String, Object> parseClaims(String token);
  String createJwt(String username, String role, Long expiredMs, Map<String, String> claimMap);
}
