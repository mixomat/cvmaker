package de.marcweinberger.repository;

import de.marcweinberger.model.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

/**
 * Mongo Repository for @{Technology} value objects.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 31.08.15
 */
@Repository
public class TechnologyRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Technology> all() {
    final Aggregation aggregation = newAggregation(
      project("technologies"),
      unwind("technologies"),
      group("technologies").count().as("weight"),
      project("weight").and("name").previousOperation()
    );

    final AggregationResults<Technology> aggregationResults = mongoTemplate.aggregate(aggregation, "project", Technology.class);
    return aggregationResults.getMappedResults();
  }
}
