package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class FormHandling {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try{
            // Click on Login label
            MobileElement loginElem = androidDriver.findElementByAccessibilityId("Login");
            loginElem.click();

            //Finding Element
            MobileElement usernameElem = androidDriver.findElementByAccessibilityId("input-email");
            MobileElement passwordElem = androidDriver.findElementByAccessibilityId("input-password");
            MobileElement loginBtnElem = androidDriver.findElementByAccessibilityId("button-LOGIN");

            // Input username
            usernameElem.sendKeys("abc");

            // Input password
            usernameElem.sendKeys("123456");

            // Clear
            usernameElem.clear();
            usernameElem.sendKeys("abc@gmail.com");

            // Click on Login button
            loginElem.click();

        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            androidDriver.quit();
        }

    }
}
