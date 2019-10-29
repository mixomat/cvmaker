package de.marcweinberger.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.marcweinberger.CVMakerApp;
import de.marcweinberger.data.repository.ProjectRepository;
import de.marcweinberger.domain.model.Project;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = CVMakerApp.class)
@WithMockUser
@Ignore("not working spring security oauth")
public class ProjectResourceTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private ProjectRepository projectRepository;

  @Test
  public void getProjectsReturnsTheListOfProjects() throws Exception {
    projectRepository.save(new Project());
    projectRepository.save(new Project());

    mvc.perform(get("/projects"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$._embedded.projects", hasSize(2)));
  }

  @Test
  public void createProjectIsSound() throws Exception {
    Project project = Project.builder().withTitle("some title").build();

    mvc.perform(post("/projects").content(objectMapper.writeValueAsBytes(project)))
      .andDo(print())
      .andExpect(status().isCreated());
  }
}
