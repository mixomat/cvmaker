package de.marcweinberger.controller;

import de.marcweinberger.data.repository.ProjectRepository;
import de.marcweinberger.domain.model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/projects")
public class ProjectController {

  private ProjectRepository projectRepository;

  public ProjectController(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @PostMapping
  public Project create(@RequestBody Project project) {
    return projectRepository.save(project);
  }


  @GetMapping
  public List<Project> list() {
    return projectRepository.findAll();
  }
}
