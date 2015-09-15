package de.marcweinberger.data.repository;

import de.marcweinberger.domain.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Repository for User models.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 14.09.15
 */
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
