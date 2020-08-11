package com.vytrack.step_definitions;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.logging.Logger;

public class Hooks {

      //private static Logger logger = Logger.getLogger(Hooks.class);

    @Before(order = 2)
    public void setup() {
       // logger.info("=============");
        //logger.info("Test setup!");
        System.out.println("Test setup");
        String browser = ConfigurationReader.getProperty("browser");
        if(!browser.contains("remote") && !browser.contains("mobile")) {
            Driver.getDriver().manage().window().maximize();
        }
    }

    @Before(value = "@driver", order = 1)
    public void specialSetup() {
        System.out.println("Setup for driver only");
    }

    @After("@driver")
    public void specialTearDown() {
        System.out.println("Tear down for driver only");
    }
//    @After
//    public void teardown(Scenario scenario){
//        if (scenario.isFailed()){
//            logger.error("Test setup!");
//            byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot,"image/png" );
//        }else{
//            logger.info("Cleanup");
//            logger.info("Test completed");
//        }
//        logger.info("=================");
//        Driver.close();
//    }

    @After
    public void tearDown(Scenario scenario) {
        //how to check if scenario failed
        if (scenario.isFailed()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.getDriver();
            byte[] image = takesScreenshot.getScreenshotAs(OutputType.BYTES);
            //attach screenshot to the report
            scenario.embed(image, "image/png", scenario.getName());
        }
        System.out.println("Test clean up");
        Driver.closeDriver();
    }
}
