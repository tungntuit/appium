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

public class SwipeUntil {
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
        int xStarPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStarPoint;

        int yStarPoint = (50 * screenHeight) / 100;
        int yEndPoint = (20 * screenHeight) / 100;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStarPoint,yStarPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);


        // Perform action
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        final int MAX_SWIPE_TIME = 10;
        int swipeTime = 0;

        while(swipeTime < MAX_SWIPE_TIME){
            List<MobileElement> matchedElems = androidDriver.findElementsByXPath("//*[@text='You found me!!!']");
            if(!matchedElems.isEmpty()) break;

            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                    .moveTo(endPoint)
                    .release()
                    .perform();

            swipeTime++;
        }

        if (swipeTime == 10){
            throw new RuntimeException("Icon not found");
        }

    }
}
