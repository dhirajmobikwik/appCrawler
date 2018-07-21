package pageConfigs; 

/**
 * @author dhiraj
 *
 */
public interface Config {

	String automation_Name = "Appium";
	String platform_Version_local = "7.1.1";
	String appium_Version = "1.8.1";
	String platform_Name = "Android";
	String device_Name = "emulator-5554";

	String app_Activity = ".MobikwikMain";
	String app_Package = "com.mobikwik_new";
	String apkPath = "MK_Android_App-prod-debug-27.apk";

	String ipAdd = "http://0.0.0.0:4723/wd/hub";
}
