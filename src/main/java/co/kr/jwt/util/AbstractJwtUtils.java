package co.kr.jwt.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractJwtUtils implements JwtUtils {
  protected final SecretKey secretKey;

  protected AbstractJwtUtils(SecretKey secretKey) {
    if(Objects.isNull(secretKey)) {
      throw new IllegalArgumentException("SecretKey must not be null.");
    }
    this.secretKey = secretKey;
  }

  @Override
  public String getUsername(String token) {
    return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("username", String.class);
  }

  @Override
  public String getRole(String token) {
    return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("role", String.class);
  }

  @Override
  public Boolean isExpired(String token) {
    return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getExpiration()
            .before(new Date());
  }

  @Override
  public Map<String, Object> parseClaims(String token) {
    return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

  @Override
  public String createJwt(String username, String role, Long expiredMs, Map<String, String> claimMap) {
    return Jwts.builder()
            .claim("username", username)
            .claim("role", role)
            .claims(claimMap)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + expiredMs))
            .signWith(secretKey)
            .compact();
  }
}
