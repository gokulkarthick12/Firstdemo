package com.automation.appium.AppiumStarter;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class CreateContact {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("appium:deviceName", "vivo V2126");
		caps.setCapability("appium:udid", "1378553624000I7");   // Get via adb devices
		caps.setCapability("appium:automationName", "UiAutomator2");
		//caps.setCapability("appium:appPackage", "com.google.android.dialer");
		//caps.setCapability("appium:appActivity", "com.android.dialer.main.impl.MainActivity");
		caps.setCapability("appium:noReset", true);
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
		 //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//driver.findElement(AppiumBy.accessibilityId("Phone")).click();
		//Activity startactivitiy = new Activity("\"com.android.launcher\"","com.android.launcher2.Launcher");
		//driver.startActivity(startactivitiy);
		 //driver.startActivity(new io.appium.java_client.android.Activity("com.android.launcher", "com.android.launcher2.Launcher"));
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		CreateContact.swipeUp(driver);
		WebElement searchbar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.launcher3:id/search_edit_text\"]"));
		searchbar.click();
		searchbar.sendKeys("Phone");
		driver.findElement(AppiumBy.accessibilityId("Phone")).click();
		driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@resource-id=\"com.google.android.dialer:id/photo\"]")).click();
		WebElement firstname = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"First name\"]"));
		firstname.click();
		firstname.sendKeys("Dummy1");
		driver.hideKeyboard();
		WebElement phone = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text=\"Phone\"]"));
		phone.click();
		phone.sendKeys("601");
		driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.google.android.contacts:id/toolbar_button\"]")).click();
        WebElement toastElement = driver.findElement(By.linkText("Dummy1 saved"));
        String toastMessage = toastElement.getText();

        // Verify the toast message
        if (toastMessage.equals("Dummy1 saved")) {
            System.out.println("Toast message verified successfully.");
        } else {
            System.out.println("Toast message verification failed.");
        }
		System.out.print("Contact Created");
		driver.quit();

	}
	public static void swipeUp(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();

        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);  // Near bottom
        int endY = (int) (size.height * 0.2);    // Near top

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Start swipe action
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

}
