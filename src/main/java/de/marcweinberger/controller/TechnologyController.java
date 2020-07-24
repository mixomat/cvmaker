package de.marcweinberger.controller;

import de.marcweinberger.data.repository.TechnologyRepository;
import de.marcweinberger.domain.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Simple controller for Technology resource.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 31.08.15
 */
@RestController
@CrossOrigin
@RequestMapping("/api/technologies")
public class TechnologyController {

  @Autowired
  private TechnologyRepository technologyRepository;

  @GetMapping
  public List<Technology> all() {
    return technologyRepository.all();
  }
}
