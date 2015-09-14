package de.marcweinberger.resource;

import de.marcweinberger.CVMakerIntegrationTest;
import de.marcweinberger.data.repository.ProjectRepository;
import de.marcweinberger.model.Project;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration Test for Project resource.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 17.08.15
 */
public class ProjectResourceTest extends CVMakerIntegrationTest {

  private RestTemplate template = new TestRestTemplate();

  @Autowired
  private ProjectRepository projectRepository;

  @Value("${local.server.port}")
  private int port;

  @Test
  public void getProjectList() throws Exception {
    // given
    projectRepository.save(new Project());

    // when
    final ResponseEntity<Resources> response = template.getForEntity(getProjectsURL(), Resources.class);

    // then
    assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
  }

  private String getProjectsURL() {
    return "http://localhost:" + port + "/api/projects";
  }


}
