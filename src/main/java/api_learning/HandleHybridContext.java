package api_learning;

import com.sun.org.apache.xpath.internal.operations.Bool;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandleHybridContext {
    private static Object AppiumDriver;
    private static Object MobileElement;

    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try{
            // Click on Login label
            MobileElement loginElem = androidDriver.findElementByAccessibilityId("Login");
            loginElem.click();

            WebDriverWait wait = new WebDriverWait(androidDriver,10L);
            wait.until(moreThanOneContext(androidDriver));

            androidDriver.getContextHandles().forEach(context ->{
                System.out.println(context);
            });

        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            androidDriver.quit();
            DriverFactory.stopAppiumServer();
        }

        private static ExpectedCondition<Boolean> moreThanOneContext (AppiumDriver<MobileElement appiumDriver>){
            return new ExpectedCondition<Boolean>(){
                @Override
                public Boolean apply (WebDriver driver) {
                    return appiumDriver.getContextHandles().size() > 1;
                }
            };
        }

    }
}
