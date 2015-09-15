package de.marcweinberger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * OAuth2 security configuration.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 07.09.15
 */
@Configuration
public class OAuth2SecurityConfig {

  @Value("${security.oauth2.resource.jwt.key-value}")
  private String jwtKey;

  @Bean
  @Primary
  public TokenStore tokenStore() {
    return new JwtTokenStore(tokenEnhancer());
  }

  @Bean
  public JwtAccessTokenConverter tokenEnhancer() {
    final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    converter.setSigningKey(jwtKey);

    return converter;
  }

  @Configuration
  @EnableResourceServer
  protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/api/projects").permitAll()
        .antMatchers(HttpMethod.GET, "/api/technologies").permitAll()
        .antMatchers("/auth/*").permitAll()
        .anyRequest().authenticated();
    }
  }

  @SuppressWarnings("SpringJavaAutowiringInspection")
  @Configuration
  @EnableAuthorizationServer
  protected static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OAuth2ClientProperties clientProperties;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
      clients
        .inMemory()
        .withClient(clientProperties.getClientId())
        .secret(clientProperties.getClientSecret())
        .authorizedGrantTypes("password", "refresh_token", "authorization_code")
        .scopes("read", "write")
        .authorities("ROLE_USER");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      endpoints
        .tokenStore(tokenStore)
        .tokenEnhancer(tokenConverter)
        .authenticationManager(authenticationManager);
    }
  }

}
