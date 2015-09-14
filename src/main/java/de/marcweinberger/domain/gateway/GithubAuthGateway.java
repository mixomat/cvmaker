package de.marcweinberger.domain.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Infrastructure Service for github authentication.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 12.09.15
 */
@Service
public class GithubAuthGateway {

  @Autowired
  @Qualifier("githubRestTemplate")
  private OAuth2RestTemplate githubRestTemplate;


  public String getGithubEmail() {
    final GithubEmail[] githubEmails = githubRestTemplate.getForObject("https://api.github.com/user/emails", GithubEmail[].class);
    final GithubEmail primaryGithubEmail = Arrays.stream(githubEmails).filter(githubEmail -> githubEmail.isPrimary()).findFirst().get();

    return primaryGithubEmail.getEmail();
  }

  @SuppressWarnings("unused")
  public static class GithubEmail {
    private String email;
    private boolean primary;
    private boolean verified;

    public GithubEmail() {
      // required for json mapping
    }

    public GithubEmail(String email, boolean primary, boolean verified) {
      this.email = email;
      this.primary = primary;
      this.verified = verified;
    }

    public String getEmail() {
      return email;
    }

    public boolean isPrimary() {
      return primary;
    }

  }
}
