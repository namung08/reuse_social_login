package co.kr.oauth2.dto;

import java.util.Map;

public class KakaoResponse extends AbstractOAuth2Response {

  public KakaoResponse(Map<String, Object> attribute) {
    super(attribute);
  }

  @Override
  public String getProvider() {
    return "kakao";
  }

  @Override
  public String getProviderId() {
    return attribute.get("id").toString();
  }

  @Override
  public String getEmail() {
    return (String) ((Map) attribute.get("kakao_account")).get("email");
  }

  @Override
  public String getName() {
    return (String) ((Map) attribute.get("properties")).get("nickname");
  }
}
