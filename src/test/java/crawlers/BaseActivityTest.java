package crawlers;

import org.testng.annotations.BeforeClass;

import pageConfigs.DataElements;
import pages.CommonFlows;
import pages.RandomClick;
import utils.AppFactory;
import utils.Helper;

/**
 * @author dhiraj
 *
 */
public abstract class BaseActivityTest extends AppFactory implements DataElements {

	Helper helper = new Helper();
	CommonFlows comflow = new CommonFlows();
	RandomClick randomClick = new RandomClick();

	@BeforeClass
	public void verifySignUpFlow() {
		comflow.signUp(email, password);
	}

	public void tearDown() {
		super.tearDown();
	}
}
