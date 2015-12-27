package de.marcweinberger.data.converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import de.marcweinberger.domain.model.Project;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for YearMonth conversion in @{Project} model.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 18.08.15
 */
@RunWith(MockitoJUnitRunner.class)
public class YearMonthConversionTest {

  @Mock
  private ApplicationContext context;
  @Mock
  private DbRefResolver resolver;

  private MappingMongoConverter converter;

  @Before
  public void setUp() throws Exception {
    MongoMappingContext mappingContext = new MongoMappingContext();
    mappingContext.setApplicationContext(context);
    mappingContext.afterPropertiesSet();

    converter = new MappingMongoConverter(resolver, mappingContext);
    converter.setCustomConversions(new CustomConversions(Arrays.asList(new YearMonthToDateConverter(), new DateToYearMonthConverter())));
    converter.afterPropertiesSet();
  }

  @Test
  public void testProjectWriteConversion() throws Exception {
    // given
    DBObject dbo = new BasicDBObject();
    final LocalDateTime now = LocalDateTime.now();

    Project project = getProject(now);


    // when
    converter.write(project, dbo);

    // then
    assertThat(dbo.get("title"), is("test title"));
    assertThat(dbo.get("start").toString(), containsString("Mon Feb 01"));
    assertThat(dbo.get("createdAt"), is(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())));
  }

  @Test
  public void testProjectReadConversion() throws Exception {
    // given
    DBObject dbo = new BasicDBObject();
    final LocalDateTime now = LocalDateTime.now();

    converter.write(getProject(now), dbo);


    // when
    final Project project = converter.read(Project.class, dbo);

    // then
    assertThat(project.getTitle(), is("test title"));
    assertThat(project.getStart(), is(YearMonth.of(2010, 2)));
    assertThat(project.getCreatedAt(), is(now));
  }

  private Project getProject(LocalDateTime now) {
    Project project = new Project();
    project.setTitle("test title");
    project.setStart(YearMonth.of(2010, 2));
    project.setCreatedAt(now);
    return project;
  }
}
