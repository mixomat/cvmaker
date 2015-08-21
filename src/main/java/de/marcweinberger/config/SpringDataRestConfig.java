package de.marcweinberger.config;

import de.marcweinberger.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import javax.annotation.PostConstruct;

/**
 * Custom spring-data-rest config, which exposes ids for entities.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 21.08.15
 */
@Configuration
@SuppressWarnings("SpringJavaAutowiringInspection")
public class SpringDataRestConfig {

  @Autowired
  private RepositoryRestConfiguration repositoryRestConfiguration;

  @PostConstruct
  public void init() {
    repositoryRestConfiguration.exposeIdsFor(Project.class);
  }
}
