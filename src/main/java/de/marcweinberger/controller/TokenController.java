package de.marcweinberger.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

  @GetMapping("/api/userinfo")
  public String userinfo(@AuthenticationPrincipal JwtAuthenticationToken authenticationToken) {
    return String.format("user: %s", authenticationToken.getName());
  }

}
