package de.marcweinberger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CVMakerAppTest extends CVMakerIntegrationTest {

	@Autowired
	private MongoTemplate mongoTemplate;

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

}
