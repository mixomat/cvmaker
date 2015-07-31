package de.marcweinberger.repository;

import de.marcweinberger.CVMakerApp;
import de.marcweinberger.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

/**
 * Test cases for ProjectRepository.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 10.07.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CVMakerApp.class)
@Transactional
public class ProjectRepositoryTest {

  @Autowired
  private ProjectRepository projectRepository;

  private Project project;

  @Before
  public void setUp() throws Exception {
    project = new Project();
  }

  @After
  public void tearDown() throws Exception {
    projectRepository.deleteAll();
  }

  @Test
  public void save() throws Exception {
    final Project savedProject = projectRepository.save(project);

    assertThat(savedProject.getId(), is(notNullValue()));
  }

  @Test
  public void findAll() throws Exception {
    // given
    projectRepository.save(project);

    // when
    final List<Project> projects = projectRepository.findAll();

    // then
    assertThat(projects, hasSize(1));
    assertThat(projects, everyItem(hasProperty("id")));
  }
}