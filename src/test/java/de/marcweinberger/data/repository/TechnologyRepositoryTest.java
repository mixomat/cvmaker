package de.marcweinberger.data.repository;

import de.marcweinberger.CVMakerIntegrationTest;
import de.marcweinberger.model.Project;
import de.marcweinberger.model.Technology;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Integration test for {@code TechnologyRepository}.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 31.08.15
 */
@Transactional
public class TechnologyRepositoryTest extends CVMakerIntegrationTest {

  @Autowired
  private TechnologyRepository technologyRepository;

  @Autowired
  private ProjectRepository projectRepository;

  @Test
  public void aggregateTechnologies() throws Exception {
    // given
    createProject(Collections.singletonList("Java"));
    createProject(Arrays.asList("Java", "Ruby"));

    // when
    final List<Technology> technologies = technologyRepository.all();

    // then
    assertThat(technologies, hasItems(new Technology("Java", 2), new Technology("Ruby", 1)));
  }

  private void createProject(List<String> technologies) {
    final Project project = new Project();
    project.setTechnologies(technologies);
    projectRepository.save(project);
  }
}
