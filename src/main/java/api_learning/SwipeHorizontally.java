package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SwipeHorizontally {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        MobileElement formsLabel = androidDriver.findElementByAccessibilityId("Swipe");
        formsLabel.click();

        WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
        wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByXPath("//*[@text='Swipe horizontal']")));

        // Get mobile size
        Dimension windowsSize = androidDriver.manage().window().getSize();
        int screenHeight = windowsSize.getHeight();
        int screenWidth = windowsSize.getWidth();

        //Calculate touch points
        int xStarPoint = (90 * screenWidth) / 100;
        int xEndPoint = (10 * screenWidth) / 100;

        int yStarPoint = (50 * screenHeight) / 100;
        int yEndPoint = yStarPoint;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStarPoint,yStarPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);


        // Perform action
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        final int MAX_SWIPE_TIME = 5;
        int swipeTime = 0;

        while(swipeTime < MAX_SWIPE_TIME){
            List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='EXTENDABLE']");
            if(!matchedElems.isEmpty()) break;

            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(3)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            swipeTime++;
        }

        if (swipeTime == MAX_SWIPE_TIME){
            throw new RuntimeException("Card not found");
        }

    }
}
