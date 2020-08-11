package com.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CalculatorTest {
    AppiumDriver<MobileElement> driver;

    @Test
    public void test() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //Since we use android. could be IOS
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //version f your device
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        // Name of the device
        //If it's a real device, you need to use udid parameter
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        /*
        either you specify app --> //path/to/the/app.apk
        or, if app is already installed, you need to specify appActivity and appPackage
        this info you can find in the internet, at work - from developers
        otherwise, you can use apk info app to find this information
         */
        //To find this use the apk-info or google appActivity for calculator
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
       // Set your application's package name.
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
       // Set your application's MainActivity i.e. the LAUNCHER activity name.
        //put this after you run it and you see error
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        //Now we use driver and surround with try catch block or throws a declaration because of URL exception
          //AppiumDriver driver on top so that we can use it anywhere, it will be complaining
            // because is generic so put diamond braces and specify which element you would like to up there
            //"http://localhost:4723/wd/hub" --> address of the appium server. If you have appium server on the same computer
            // -just use localhost. 4723  --> default appium port
            //We need to provide parameters: URl of appium servers and desired capabilities
            //in desired capabilities we specify device and application to test information
            //We use AppiumDriver as a parent driver instead of new AndroidDriver or new IOSDriver
            driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);

           Thread.sleep(3000);

          // driver.closeApp();

           //Make sure you emulator is on or working
          //Android Studio is open
         //To check if emulator is on
        //Go to Terminal and enter : adb devices  ---> to see if is on

        // HOW TO FIND ELEMENTS
        MobileElement digit8 = driver.findElement(By.id("com.android.calculator2:id/digit_8"));
        MobileElement digit_9 = driver.findElement(By.id("com.android.calculator2:id/digit_9"));
        MobileElement digit_2 = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
        MobileElement digit_7 = driver.findElement(By.id("com.android.calculator2:id/digit_7"));

        MobileElement plus = driver.findElement(By.id("com.android.calculator2:id/op_add"));
        MobileElement equals = driver.findElement(By.id("com.android.calculator2:id/eq"));
        MobileElement results = driver.findElement(By.id("com.android.calculator2:id/result"));
        MobileElement multiply = driver.findElement(By.id("com.android.calculator2:id/op_mul"));
        MobileElement divide = driver.findElement(By.id("com.android.calculator2:id/op_div"));
        MobileElement minus = driver.findElement(By.id("com.android.calculator2:id/op_sub"));
        //MobileElement plus = driver.findElement(MobileBy.AccessibilityId("plus"));
        //MobileBy --> is a child class of By
         //Clicking on digits
        digit8.click();
        plus.click();
        digit_9.click();
        equals.click();



        //Then we have to do Assertion
        String resultsText = results.getText();
        Assert.assertEquals("17", resultsText);
         // 2 * 7 = 14
        getDigit(2).click();
        multiply.click();
        getDigit(7).click();
        equals.click();
        resultsText = results.getText();
        Assert.assertEquals("14", resultsText);

        getDigit(8).click();
        divide.click();
        getDigit(4).click();
        equals.click();

        resultsText = results.getText();
        Assert.assertEquals("2", resultsText);

       //50 - 20 = 30
       getDigit(5).click();
       getDigit(0).click();
       minus.click();
       getDigit(2).click();
       getDigit(0).click();
       equals.click();


        resultsText = results.getText();
        Assert.assertEquals("30", resultsText);





        driver.closeApp();

    }
    //Crete a method
    public MobileElement getDigit(int digit){
        return driver.findElement(By.id("com.android.calculator2:id/digit_" + digit));
    }





    /*After Running program
    DEPRECATION WARNING: For this warning create another line of desired capabilities and specify this
    The 'automationName' capability was not provided in the desired
    capabilities for this Android session

    Setting 'automationName=UiAutomator2' by default and using the
    UiAutomator2 Driver
     */
}
