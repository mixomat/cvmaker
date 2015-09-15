package de.marcweinberger.controller;

import de.marcweinberger.data.repository.TechnologyRepository;
import de.marcweinberger.domain.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Simple controller for Technology resource.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 31.08.15
 */
@BasePathAwareController
public class TechnologyController {

  @Autowired
  private TechnologyRepository technologyRepository;

  @RequestMapping(method = RequestMethod.GET, value = "technologies")
  @ResponseBody
  public List<Technology> all() {
    return technologyRepository.all();
  }
}
