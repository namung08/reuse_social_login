package co.kr.oauth2.dto;

import java.util.Map;

public interface OAuth2Response {
  Map<String, Object> getAttribute();
  String getProvider();
  String getProviderId();
  String getEmail();
  String getName();
}
