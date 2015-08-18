package de.marcweinberger;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base class for cvmaker integration test.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 17.08.15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CVMakerApp.class)
@WebIntegrationTest(randomPort = true)
public abstract class CVMakerIntegrationTest {
}
