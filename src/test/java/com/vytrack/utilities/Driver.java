package com.vytrack.utilities;

import com.vytrack.step_definitions.Hooks;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class Driver {
    //private static Logger logger = Logger.getLogger(Driver.class);
    //same for everyone
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final String USERNAME = "samuelosei1";
    private static final String AUTOMATE_KEY = "3P7xGoNoXuCzDUSoBjnp";
    private static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    //so no one can create object of Driver class
    //everyone should call static getter method instead
    private Driver() {

    }

    /**
     * synchronized makes method thread safe. It ensures that only 1 thread can use it at the time.
     * <p>
     * Thread safety reduces performance but it makes everything safe.
     *
     * @return
     */
    public synchronized static WebDriver getDriver() {
        String GRID_URL = "http://35.175.126.183:4444/wd/hub";
        //if webdriver object doesn't exist
        //create it
        if (driverPool.get() == null) {
            //specify browser type in configuration.properties file
            String browser = ConfigurationReader.getProperty("browser").toLowerCase();
            // -Dbrowser=firefox
            if (System.getProperty("browser") != null) {
                browser = System.getProperty("browser");
            }

            if (System.getProperty("grid_url") != null) {
                GRID_URL = System.getProperty("grid_url");
            }

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "chrome-remote":
                    try {
                        //we create object of URL and specify
                        //selenium grid hub as a parameter
                        //make sure it ends with /wd/hub
                        URL url = new URL(GRID_URL);
                        //desiredCapabilities used to specify what kind of node
                        //is required for testing
                        //such as: OS type, browser, version, etc...
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.CHROME);
                        desiredCapabilities.setPlatform(Platform.ANY);

                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox-remote":
                    try {
                        //we create object of URL and specify
                        //selenium grid hub as a parameter
                        //make sure it ends with /wd/hub
                        URL url = new URL(GRID_URL);
                        //desiredCapabilities used to specify what kind of node
                        //is required for testing
                        //such as: OS type, browser, version, etc...
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName(BrowserType.FIREFOX);
                        desiredCapabilities.setPlatform(Platform.ANY);
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;

                case "mobile_remote":
                    try {
                        ChromeOptions chromeOptions1 = new ChromeOptions();
                        chromeOptions1.addArguments("--incognito");
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "7.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_2");
                    desiredCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);

                    desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions1);

                        driverPool.set(new RemoteWebDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "mobile_safari_remote":
                    try {
                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setCapability("browserName", "safari");
                    caps.setCapability("device", "iPhone 11 Pro Max");
                    caps.setCapability("realMobile", "true");
                    caps.setCapability("os_version", "13");
                    caps.setCapability("name", "VyTrack tests");
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                 break;
                case "mobile_chrome_remote":
                    try {
                        DesiredCapabilities caps = new DesiredCapabilities();
                        caps.setCapability("browserName", "android");
                        caps.setCapability("device", "Samsung Galaxy S10");
                        caps.setCapability("realMobile", "true");
                        caps.setCapability("os_version", "9.0");
                        caps.setCapability("name", "VyTrack tests");
                        driverPool.set(new RemoteWebDriver(new URL(URL), caps));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }

    /**
     * synchronized makes method thread safe. It ensures that only 1 thread can use it at the time.
     * <p>
     * Thread safety reduces performance but it makes everything safe.
     *
     * @return
     */
    public synchronized static WebDriver getDriver(String browser) {
        //if webdriver object doesn't exist
        //create it
        if (driverPool.get() == null) {
            //specify browser type in configuration.properties file
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().browserVersion("84").setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;
                case "chromeheadless":
                    //to run chrome without interface (headless mode)
                    WebDriverManager.chromedriver().browserVersion("84").setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setHeadless(true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                default:
                    throw new RuntimeException("Wrong browser name!");
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
