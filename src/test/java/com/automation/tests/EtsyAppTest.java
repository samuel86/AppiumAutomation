package com.automation.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class EtsyAppTest {
    AppiumDriver<MobileElement> driver;

    @Test
    public void test() throws Exception {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //Cloud App of Etsy
        desiredCapabilities.setCapability("app", "http://cybertek-appium.s3.amazonaws.com/etsy.apk");
        driver = new AppiumDriver<>(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(5000);

        MobileElement search = driver.findElement(By.id("com.etsy.android:id/search_src_text"));

        search.sendKeys("Selenium");
        Thread.sleep(3000);



//        try{
//            search.sendKeys("Selenium");
//        }catch (WebDriverException e){
//            search = driver.findElement(By.id("com.etsy.android:id/search_src_text"));
//            search.sendKeys("Selenium");
//        }
        driver.getKeyboard().pressKey(Keys.ENTER);
        driver.hideKeyboard();
        Thread.sleep(3000);

        driver.closeApp();


    }
}
