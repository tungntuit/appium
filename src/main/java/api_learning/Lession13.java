package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import javafx.scene.effect.MotionBlur;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.List;

public class Lession13 {
    public static void main(String[] args) {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();

        try{
            // Click on Login label
            MobileElement loginElem = androidDriver.findElementByAccessibilityId("Login");
            loginElem.click();

            final int EMAIL_FIELD_INDEX = 0;
            final int PASSWORD_FIELD_INDEX = 1;
            List<MobileElement> loginFormInputElem = androidDriver.findElements(By.xpath("//android.widget.EditText"));
            if (!loginFormInputElem.isEmpty()){
                throw new RuntimeException("Login form element can't be found!");
            }else{
                loginFormInputElem.get(EMAIL_FIELD_INDEX).sendKeys("new@abc.com"); // Magic number
                loginFormInputElem.get(PASSWORD_FIELD_INDEX).sendKeys("12345678");
            }

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

            WebDriverWait wait = new WebDriverWait(androidDriver, 10L);
            wait.until(ExpectedConditions.visibilityOf(androidDriver.findElement(By.id ("android:id/alertTitle"))));
            MobileElement loginDialogTitleElem = androidDriver.findElement(By.id("android:id/alertTitle"));
            System.out.println("Login Title: "+ loginDialogTitleElem.getText());

            MobileElement loginDialogXpathElem = (androidDriver.findElementByXPath(
                    "//*[@resource-id='android:id/topPanel'1//*[@resource-id='android:id/alertTitle']"));
            System.out.println(loginDialogXpathElem.getText());

            List<MobileElement> badSelectorElems =androidDriver.findElementsByXPath("wrong");

            if(!badSelectorElems.isEmpty()) {
                throw new RuntimeException("Wrong element still displayed!!");
            }

//            System.out.println(badSelectorElems.get(0).getText()); // index out of bound

        }catch(Exception e)
        {
            e.printStackTrace();
        }finally {
            androidDriver.quit();
            DriverFactory.stopAppiumServer();
        }

    }
}
