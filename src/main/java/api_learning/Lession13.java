package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Lession13 {
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

            MobileElement loginDialogTitleElem = androidDriver.findElement(By.id("android:id/alertTitle"));
            System.out.println("Login Title: "+loginDialogTitleElem.getText());

        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            androidDriver.quit();
            DriverFactory.stopAppiumServer();
        }

    }
}
