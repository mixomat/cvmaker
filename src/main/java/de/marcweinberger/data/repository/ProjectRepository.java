package de.marcweinberger.data.repository;

import de.marcweinberger.domain.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for project documents.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 10.07.15
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {
}
