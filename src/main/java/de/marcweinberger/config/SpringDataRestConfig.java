package de.marcweinberger.config;

import de.marcweinberger.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

/**
 * Custom spring-data-rest config, which exposes ids for entities and configures cors filter.
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

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return new CorsFilter(source);
  }
}
