package de.marcweinberger.controller;

import de.marcweinberger.domain.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * TODO document me
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 09.09.15
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired

  private AuthService authService;

  @RequestMapping(method = RequestMethod.POST, value = "github")
  public OAuth2AccessToken authorizeWithGithub(@RequestBody Map<String, String> requestBody) {
    return authService.authorizeGithubUser(requestBody.get("code"), requestBody.get("redirectUri"));
  }

  @RequestMapping(method = RequestMethod.GET, value = "github")
  public OAuth2AccessToken getGithubAuthorization() {
    return authService.authorizeGithubUser(null, null);
  }

}
