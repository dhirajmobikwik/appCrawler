package utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import pageConfigs.Config;

/**
 * @author dhiraj
 *
 */
public class AppFactory implements Config {

	protected static AppiumDriver<MobileElement> driver;
	DesiredCapabilities desiredCaps = new DesiredCapabilities();
	File apk = new File(apkPath);

	@BeforeClass
	public AppiumDriver<MobileElement> init() throws MalformedURLException {
		desiredCaps.setCapability(MobileCapabilityType.AUTOMATION_NAME, automation_Name);
		desiredCaps.setCapability(MobileCapabilityType.APPIUM_VERSION, appium_Version);
		desiredCaps.setCapability(MobileCapabilityType.PLATFORM_NAME, platform_Name);
		desiredCaps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, app_Activity);
		desiredCaps.setCapability(AndroidMobileCapabilityType.SUPPORTS_ALERTS, true);
		desiredCaps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, app_Package);
		desiredCaps.setCapability(MobileCapabilityType.APP, apk);
		desiredCaps.setCapability(MobileCapabilityType.DEVICE_NAME, device_Name);
		//desiredCaps.setCapability(MobileCapabilityType.NO_RESET, true);
		driver = new AndroidDriver<MobileElement>(new URL(ipAdd), desiredCaps);

		return driver;
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
