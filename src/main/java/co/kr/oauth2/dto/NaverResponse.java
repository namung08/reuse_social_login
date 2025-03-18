package co.kr.oauth2.dto;

import java.util.Map;

public class NaverResponse extends AbstractOAuth2Response{
  public NaverResponse(Map<String, Object> attribute) {
    super(attribute);
  }

  @Override
  public Map<String, Object> getAttribute() {
    return Map.of();
  }

  @Override
  public String getProvider() {
    return "naver";
  }

  @Override
  public String getProviderId() {
    return attribute.get("id").toString();
  }

  @Override
  public String getEmail() {
    return attribute.get("email").toString();
  }

  @Override
  public String getName() {
    return attribute.get("name").toString();
  }
}
