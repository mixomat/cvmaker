package de.marcweinberger.client;

import org.junit.Test;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.Collections;

/**
 * TODO document me
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 09.09.15
 */
public class GithubRestTemplateTest {

  @Test(expected = UserRedirectRequiredException.class)
  public void gitHubClient() throws Exception {
    githubRestTemplate().getAccessToken();
  }

  private OAuth2RestTemplate githubRestTemplate() {
    return new OAuth2RestTemplate(github(), getOAuth2ClientContext());
  }

  private OAuth2ProtectedResourceDetails github() {
    AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
    details.setId("github");
    details.setClientId("8982f8b1340689811709");
    details.setClientSecret("acc3909c751af7c61d08b4ec30ae9863d1205955");
    details.setAccessTokenUri("https://github.com/login/oauth/access_token");
    details.setScope(Collections.singletonList("user:email"));
    details.setAuthenticationScheme(AuthenticationScheme.query);

    return details;
  }

  private OAuth2ClientContext getOAuth2ClientContext() {
    final DefaultAccessTokenRequest accessTokenRequest = new DefaultAccessTokenRequest();
    return new DefaultOAuth2ClientContext(accessTokenRequest);
  }
}
