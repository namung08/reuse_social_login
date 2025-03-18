package co.kr.oauth2.service;

import co.kr.oauth2.dto.OAuth2Response;
import co.kr.oauth2.factory.OAuth2ResponseFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class LibOAuth2UserService extends DefaultOAuth2UserService {
  protected final OAuth2ResponseFactory auth2ResponseFactory;

  protected LibOAuth2UserService(OAuth2ResponseFactory auth2ResponseFactory) {
    this.auth2ResponseFactory = auth2ResponseFactory;
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);

    Map<String, Object> attribute = oAuth2User.getAttributes();

    OAuth2Response response = auth2ResponseFactory.createResponse(userRequest, attribute);

    return createCustomOAuth2User(response, attribute);
  }

  protected abstract OAuth2User createCustomOAuth2User(OAuth2Response response, Map<String, Object> attribute);
}
