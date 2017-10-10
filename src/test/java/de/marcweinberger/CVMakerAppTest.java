package de.marcweinberger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CVMakerAppTest {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private CustomConversions customerConversion;

  @Autowired
  private OAuth2RestTemplate gitHubRestTemplate;

  @Value("${security.user.name}")
  private String username;
  @Value("${security.user.password}")
  private String password;


  @Test
  public void username() {
    assertThat(username, is("admin"));
  }

  @Test
  public void password() {
    assertThat(password, is("secret"));
  }

  @Test
  public void mongoTemplate() throws Exception {
    assertThat(mongoTemplate, is(notNullValue()));
  }

  @Test
  public void customConversions() throws Exception {
    assertThat(customerConversion, is(notNullValue()));
  }

  @Test
  public void gitHubRestTemplate() throws Exception {
    assertThat(gitHubRestTemplate, is(notNullValue()));
  }
}
