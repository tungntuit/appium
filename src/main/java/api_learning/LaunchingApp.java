package api_learning;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class LaunchingApp {
    public static void main(String[] args) {

        try{
            AppiumDriver<MobileElement> appiumDriver = null;

            // Specify capabilities

            DesiredCapabilities desireCaps = new DesiredCapabilities();
            desireCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
            desireCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desireCaps.setCapability(MobileCapabilityTypeEx.UDID, "3b1bc6aa");
            desireCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
            desireCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

            // Send the desiredCaps into appium server
            URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desireCaps);
            appiumDriver.quit();

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        // Init and create an appium session client <---> appium server <---> appium server
    }
}
