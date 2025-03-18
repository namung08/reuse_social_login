package co.kr.oauth2.dto;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class AbstractOAuth2Response implements OAuth2Response {
  protected final Map<String, Object> attribute;
  @Override
  public Map<String, Object> getAttribute() {
    return attribute;
  }

  @Override
  public abstract String getProvider();

  @Override
  public abstract String getProviderId();

  @Override
  public abstract String getEmail();

  @Override
  public abstract String getName();
}
