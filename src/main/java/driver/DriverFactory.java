package driver;

import caps.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static AppiumDriverLocalService appiumServer;
    private static AndroidDriver<MobileElement> androidDriver;

    public static void startAppiumServer(){
        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
//        appiumServiceBuilder.withIPAddress("127.0.0.1").usingPort(4723);
        appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumServer.start();
    }

    public static void stopAppiumServer(){
        //appiumServer.stop();
        String killNodeWindowsCommand = "taskkill /F /IM node.exe";
        String killNodeLinuxCommand = "taskkill node";
        String killNodeCmd = System.getProperty("os.name").toLowerCase().startsWith("windows")
                ? killNodeWindowsCommand : killNodeLinuxCommand;
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(killNodeCmd);
//            String appiumPort = String.valueOf(appiumServer.getUrl().getPort());
//            runtime.exec("kill -9 $(lsof -ti:" + appiumPort+")");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static AndroidDriver<MobileElement> getAndroidDriver(){
        initAndroidDriver();
        return androidDriver;
    }

    private static void initAndroidDriver(){
        AppiumDriver<MobileElement> appiumDriver = null;

        // Specify capabilities

        DesiredCapabilities desireCaps = new DesiredCapabilities();
        desireCaps.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "android");
        desireCaps.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
        desireCaps.setCapability(MobileCapabilityTypeEx.UDID, "3b1bc6aa");
        desireCaps.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.wdiodemoapp");
        desireCaps.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

        // Send the desiredCaps into appium server
        androidDriver = new AndroidDriver<MobileElement>(appiumServer.getUrl(), desireCaps);
        androidDriver.manage().timeouts().implicitlyWait(3L, TimeUnit.SECONDS);
    }
}
