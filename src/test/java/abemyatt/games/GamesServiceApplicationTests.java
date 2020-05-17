package abemyatt.games;

import static org.assertj.core.api.Assertions.assertThat;
import abemyatt.games.controller.GameController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GamesServiceApplicationTests {

	@Autowired
	private GameController controller;

	/**
	 * Sanity test that the controller is injected
	 */
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
