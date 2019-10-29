package de.marcweinberger.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/api/projects").permitAll()
      .antMatchers(HttpMethod.GET, "/api/technologies").permitAll()
      .anyRequest().authenticated()
      .and()
      .oauth2Login();
  }
}
