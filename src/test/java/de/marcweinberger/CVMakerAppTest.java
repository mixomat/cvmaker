package de.marcweinberger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CVMakerApp.class)
@WebAppConfiguration
public class CVMakerAppTest {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void mongoTemplate() throws Exception {
		assertThat(mongoTemplate, is(notNullValue()));
	}

}
