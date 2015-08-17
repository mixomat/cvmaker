package de.marcweinberger.resource;

import de.marcweinberger.CVMakerIntegrationTest;
import de.marcweinberger.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.YearMonth;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration Test for Project controller.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 17.08.15
 */
public class ProjectControllerTest extends CVMakerIntegrationTest {

  private RestTemplate template = new TestRestTemplate("admin", "secret");

  @Value("${local.server.port}")
  private int port;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testProjectMapping() throws Exception {
    // given
    final Project project = new Project();
    project.setStart(YearMonth.of(2012, 2));
    project.setEnd(YearMonth.of(2012, 11));

    // when
    final ResponseEntity<Project> response = template.postForEntity(getProjectsURL(), project, Project.class);

    // then
    assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
  }

  private String getProjectsURL() {
    return "http://localhost:" + port + "/projects";
  }


}
