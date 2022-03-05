package api_learning;

import driver.DriverFactory;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SwipeToOpenNotification {
    public static void main(String[] args) throws InterruptedException {
        DriverFactory.startAppiumServer();
        AndroidDriver<MobileElement> androidDriver = DriverFactory.getAndroidDriver();
        MobileElement formsLabel = androidDriver.findElementByAccessibilityId("Forms");
        formsLabel.click();

        // Get mobile size
        Dimension windowsSize = androidDriver.manage().window().getSize();
        int screenHeight = windowsSize.getHeight();
        int screenWidth = windowsSize.getHeight();

        //Calculate touch points
        int xStarPoint = (50 * screenWidth) / 100;
        int xEndPoint = xStarPoint;

        int yStarPoint = 0;
        int yEndPoint = (50 * screenHeight) / 100;

        // Convert to PointOptions - Coordinates
        PointOption startPoint = new PointOption().withCoordinates(xStarPoint,yStarPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint,yEndPoint);

        // Avoid screen transtion
        // Check to see whether we are on Forms screen
        WebDriverWait wait = new WebDriverWait(androidDriver, 5L);
        wait.until(ExpectedConditions.visibilityOf(androidDriver.findElementByAccessibilityId("switch")));

        // Perform action
        // press -> move up -> release
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();

        Thread.sleep(3000);

        List<MobileElement> notificationElems = androidDriver.findElements(By.id("android:id/notification_main_column"));
        if (notificationElems.isEmpty()){
            throw new RuntimeException("Notification list is empty");
        }
        List<Notification> notifications = new ArrayList<>();

        // lambda expression | Functional Interface + lambda expression
        // Functional interface La interface Chi co 1 abtract method ben trong
        // Va minh cung cap parameter la lambda expression (giong nhu la Anonymous Function)
        // Anonymous Function | 1 ham binh thuong la nhu nay: returnType methodName () {}
        // Anonymous Function se nhu nay: () -> {}
        // truyen vao notificationElem so it, truong hop chi co 1 element thi remove ()
        notificationElems.forEach(notificationElem -> {
            String notificationTitle = notificationElem.findElement(By.id("android:id/title")).getText();
            By bigTextById = MobileBy.id("android:id/big_text");
            By textById = MobileBy.id("android:id/text");

            List<MobileElement> bigTextElems = androidDriver.findElements(bigTextById);
            List<MobileElement> textElems = androidDriver.findElements(textById);
            List<MobileElement> notificationBodyElems =
                    !bigTextElems.isEmpty()
                    ? bigTextElems
                    : textElems;

            String notificationBody = notificationBodyElems.isEmpty() ? null : notificationBodyElems.get(0).getText();
            notifications.add(new Notification(notificationTitle,notificationBody));
        });

        // Verification
        notifications.forEach(notification -> {
            System.out.println(notification);
        });

        // Swipe to close notification bar
        touchAction
                .press(endPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(startPoint)
                .release()
                .perform();

        Thread.sleep(3000);

    }
    public static class Notification{
        private final String title;
        private final String content;

        public Notification(String title,String content){
            this.title=title;
            this.content=content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "Notification{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
