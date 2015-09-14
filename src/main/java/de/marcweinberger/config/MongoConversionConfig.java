package de.marcweinberger.config;

import de.marcweinberger.data.converter.DateToYearMonthConverter;
import de.marcweinberger.data.converter.YearMonthToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.Arrays;

/**
 * Custom configuration for mongo date conversion.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 18.08.15
 */
@Configuration
@EnableMongoAuditing
public class MongoConversionConfig {

  @Bean
  public CustomConversions customConversions() {
    return new CustomConversions(Arrays.asList(new YearMonthToDateConverter(), new DateToYearMonthConverter()));
  }
}
