package de.marcweinberger.data.repository;

import de.marcweinberger.CVMakerIntegrationTest;
import de.marcweinberger.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
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

  final User user = new User("test@example.com");

  @Test
  public void save() throws Exception {
    // when
    final User savedUser = userRepository.save(user);

    // then
    assertThat(savedUser.getId(), is(notNullValue()));
  }

  @Test
  public void findByEmail() throws Exception {
    // given
    final User savedUser = userRepository.save(user);

    // when
    final List<User> users = userRepository.findByEmail("test@example.com");

    assertThat(users, hasItem(savedUser));
  }
}
