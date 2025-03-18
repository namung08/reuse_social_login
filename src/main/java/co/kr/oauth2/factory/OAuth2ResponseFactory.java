package co.kr.oauth2.factory;

import co.kr.oauth2.dto.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;

import java.util.Map;

public interface OAuth2ResponseFactory {
  OAuth2Response createResponse(OAuth2UserRequest userRequest, Map<String, Object> attributes);
}
