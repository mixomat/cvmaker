package de.marcweinberger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Collections;

/**
 * OAuth2 clients.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 09.09.15
 */
@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {

  @Bean
  public OAuth2ProtectedResourceDetails github() {
    AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
    details.setId("github");
    details.setClientId("8982f8b1340689811709");
    details.setClientSecret("acc3909c751af7c61d08b4ec30ae9863d1205955");
    details.setAccessTokenUri("https://github.com/login/oauth/access_token");
    details.setUserAuthorizationUri("https://github.com/login/oauth/authorize");
    details.setTokenName("token");
    details.setScope(Collections.singletonList("user:email"));
    details.setClientAuthenticationScheme(AuthenticationScheme.query);

    return details;
  }

  @Bean
  public OAuth2RestTemplate githubRestTemplate(OAuth2ClientContext clientContext) {
    final AuthorizationCodeAccessTokenProvider accessTokenProvider = new AuthorizationCodeAccessTokenProvider();
    accessTokenProvider.setStateMandatory(false);

    final OAuth2RestTemplate githubRestTemplate = new OAuth2RestTemplate(github(), clientContext);
    githubRestTemplate.setAccessTokenProvider(accessTokenProvider);

    return githubRestTemplate;
  }

}
