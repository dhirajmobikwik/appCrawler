package crawlers;

import java.io.IOException;

import org.testng.annotations.Test;

/**
 * @author dhiraj
 *
 */
public class RandomTest extends BaseActivityTest {

	@Test
	public void ramdomUITest() throws IOException, InterruptedException {
		randomClick.getUIDump();
		randomClick.readUIAndClick();
	}
}
