package de.marcweinberger.data.repository;

import de.marcweinberger.domain.model.Project;
import de.marcweinberger.domain.model.Technology;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


public class TechnologyRepositoryTest extends MongoRepositoryTestBase {

  @TestConfiguration
  @ComponentScan(basePackageClasses = TechnologyRepository.class)
  public static class TechnologyRepositoryTestConfiguration {
  }

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
    final Iterable<Technology> technologies = technologyRepository.all();

    // then
    assertThat(technologies, hasItems(new Technology("Java", 2), new Technology("Ruby", 1)));
  }

  private void createProject(List<String> technologies) {
    final Project project = new Project();
    project.setTechnologies(technologies);
    projectRepository.save(project);
  }
}
