package de.marcweinberger.config;

import de.marcweinberger.model.Project;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

/**
 * REST repository customization.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 12.08.15
 */
@Configuration
public class RESTRepositoryConfig extends RepositoryRestMvcConfiguration {

  @Override
  protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Project.class);
  }
}
