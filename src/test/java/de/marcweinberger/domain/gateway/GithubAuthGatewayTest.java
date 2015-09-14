package de.marcweinberger.domain.gateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test cases for GithubAuthGateway.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 14.09.15
 */
@RunWith(MockitoJUnitRunner.class)
public class GithubAuthGatewayTest {

  @Mock
  private OAuth2RestTemplate githubRestTemplate;

  @InjectMocks
  private GithubAuthGateway githubAuthGateway;

  @Test
  public void getGithubEmail() throws Exception {
    // given
    final GithubAuthGateway.GithubEmail githubEmail = new GithubAuthGateway.GithubEmail("test@example.com", true, true);
    when(githubRestTemplate.getForObject("https://api.github.com/user/emails", GithubAuthGateway.GithubEmail[].class))
      .thenReturn(new GithubAuthGateway.GithubEmail[]{githubEmail});

    // when
    final String githubEmailAddress = githubAuthGateway.getGithubEmail();

    // then
    assertThat(githubEmailAddress, is("test@example.com"));
  }
}
