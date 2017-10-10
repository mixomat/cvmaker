package de.marcweinberger.data.converter;

import de.marcweinberger.domain.model.Project;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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
    Document doc = new Document();
    final LocalDateTime now = LocalDateTime.now();

    Project project = getProject(now);


    // when
    converter.write(project, doc);

    // then
    assertThat(doc.get("title"), is("test title"));
    assertThat(doc.get("start").toString(), containsString("Mon Feb 01"));
    assertThat(doc.get("createdAt"), is(Date.from(now.atZone(ZoneId.systemDefault()).toInstant())));
  }

  @Test
  public void testProjectReadConversion() throws Exception {
    // given
    Document doc = new Document();
    final LocalDateTime now = LocalDateTime.now();

    converter.write(getProject(now), doc);


    // when
    final Project project = converter.read(Project.class, doc);

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
