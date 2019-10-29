package de.marcweinberger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TokenController {

  @Autowired
  private OAuth2AuthorizedClientService authorizedClientService;

  @GetMapping("/userinfo")
  @ResponseBody
  public String userinfo(OAuth2AuthenticationToken authentication, @AuthenticationPrincipal OAuth2User oauth2User) {
    OAuth2AuthorizedClient authorizedClient = getAuthorizedClient(authentication);
    if (authorizedClient == null) {
      return "no client authorized";
    }
    return String.format("client: %s%nuser: %s", authorizedClient.getClientRegistration().getClientName(), oauth2User.getName());
  }

  private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
    return this.authorizedClientService.loadAuthorizedClient(
      authentication.getAuthorizedClientRegistrationId(), authentication.getName());
  }

}
