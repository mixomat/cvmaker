package de.marcweinberger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
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
	private ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testElasticTemplate() throws Exception {
		assertThat(elasticsearchTemplate, is(notNullValue()));
	}

}
