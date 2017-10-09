package de.marcweinberger.data.repository;

import de.marcweinberger.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest extends MongoRepositoryTestBase {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void save() throws Exception {
    // when
    final User savedUser = userRepository.save(new User("foo@example.com"));

    // then
    assertThat(savedUser.getId(), is(notNullValue()));
  }

  @Test
  public void findByEmail() throws Exception {
    // given
    final String email = "test@example.com";
    final User savedUser = userRepository.save(new User(email));

    // when
    final Optional<User> user = userRepository.findByEmail(email);

    // then
    assertThat(user.isPresent(), is(true));
    assertThat(user.get(), is(savedUser));
  }

  @Test
  public void findByEmailNotFound() throws Exception {
    // given
    userRepository.save(new User("foo@example.com"));

    // when
    final Optional<User> user = userRepository.findByEmail("bar@example.com");

    // then
    assertThat(user.isPresent(), is(false));
  }
}
