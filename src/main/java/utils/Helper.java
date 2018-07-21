package utils;

import static utils.AppFactory.driver;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @author dhiraj
 *
 */
public class Helper {

	AppFactory appFactory = new AppFactory();

	public By loc(String locate, String element) {
		switch (locate.toLowerCase()) {
		case "class":
			return By.className(element);
		case "css":
			return By.cssSelector(element);
		case "name":
			return By.name(element);
		case "id":
			return By.id(element);
		case "linktext":
			return By.linkText(element);
		case "partiallinktext":
			return By.partialLinkText(element);
		case "xpath":
			return By.xpath(element);
		case "tagname":
			return By.tagName(element);
		default:
			return null;
		}
	}

	public List<MobileElement> findMobileListElements(String locate, String element) {
		waitForElement(locate, element);
		return driver.findElements(loc(locate, element));
	}

	public MobileElement findMobileElement(String locate, String element) {
		waitForElement(locate, element);
		return driver.findElement(loc(locate, element));
	}

	public void swipe(int startx, int starty, int endx, int endy, int duration) {
		driver.swipe(startx, starty, endx, endy, duration);
	}

	public void navigateBack(int navigateCount) {
		for (int i = 0; i < navigateCount; i++) {
			driver.navigate().back();
		}
	}

	public void waitForElement(String locate, String element) {
		String msg;
		long time = System.currentTimeMillis();
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(500, TimeUnit.MILLISECONDS);
			wait.until(ExpectedConditions.not(ExpectedConditions.invisibilityOfElementLocated(loc(locate, element))));
			msg = "Time taken to find out the element \"" + element + "\" is "
					+ ((System.currentTimeMillis() - time) / 1000) + " seconds.";
			System.out.println(msg);
		} catch (NoSuchElementException | TimeoutException | UnhandledAlertException e1) {
			System.out.println("Ohh! " + element + " couldn't be found. The Timeout happened in "
					+ ((System.currentTimeMillis() - time) / 1000) + " seconds.");
			acceptAlert();
		}
	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void phoneCallDismiss() {
		driver.switchTo().alert().dismiss();
	}

	public Boolean isElementPresent(String locator, String element) throws ElementNotFoundException {
		try {
			findMobileElement(locator, element).isDisplayed();
			System.out.println("Hurray! Element \"" + element + "\" is present");
			return true;
		} catch (Exception e) {
			System.out.println("Alaas! Element \"" + element + "\" was not there");
			return false;
		}
	}

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	public void tearDown() {
		driver.quit();
	}

}
