package de.marcweinberger.data.repository;

import de.marcweinberger.CVMakerIntegrationTest;
import de.marcweinberger.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Test cases for UserRepository.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 14.09.15
 */
@Transactional
public class UserRepositoryTest extends CVMakerIntegrationTest {

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

    assertThat(user.isPresent(), is(true));
    assertThat(user.get(), is(savedUser));
  }

  @Test
  public void findByEmailNotFound() throws Exception {
    // given
    final User savedUser = userRepository.save(new User("foo@example.com"));

    // when
    final Optional<User> user = userRepository.findByEmail("bar@example.com");

    assertThat(user.isPresent(), is(true));
    assertThat(user.get(), is(savedUser));
  }
}
