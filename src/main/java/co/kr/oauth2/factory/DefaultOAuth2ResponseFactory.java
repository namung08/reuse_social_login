package co.kr.oauth2.factory;

import co.kr.oauth2.dto.GoogleResponse;
import co.kr.oauth2.dto.KakaoResponse;
import co.kr.oauth2.dto.NaverResponse;
import co.kr.oauth2.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import java.util.Map;

public class DefaultOAuth2ResponseFactory implements OAuth2ResponseFactory {
  @Override
  public OAuth2Response createResponse(OAuth2UserRequest userRequest, Map<String, Object> attributes) {
    return switch (userRequest.getClientRegistration().getRegistrationId()) {
      case "google" -> new GoogleResponse(attributes);
      case "kakao" -> new KakaoResponse(attributes);
      case "naver" -> new NaverResponse(attributes);
      default ->
              throw new UnsupportedOperationException("Unsupported OAuth2 provider: " + userRequest.getClientRegistration().getRegistrationId());
    };
  }
}
