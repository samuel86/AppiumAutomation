package com.automation.tests;

public class Notes {
    /**
     * Can we use POM with appium?
     * yes, same like with Selenium
     *
     * How about page factory and @FindBy annotations?
     * Also it's the same, plus there are @AndroidBy @IOSBy/@iOSXCUITBy annotations
     *
     * How to integrate appium with Jenkins?
     * Since it's a Java/maven project, it's the same process. The only thing is, what kind of device to use for testing
     *
     * How do I get app for testing at work?
     * To update web version, developers have to deploy it, lets say to test environment.
     * For android/ios app, developers also have to deploy app. Building the of mobile/web
     * application is the same. It's also done by some ci/cd tool like Jenkins. The only difference
     * is, after building mobile app. It doesn't go to the application server. You can just take that new
     * .apk file (for android) and install it. With appium, you don't need to install apps manually.
     * In desired capabilities, just put:
     *        "app", "http://s3.bucket.com/myproject/application.apk".
     * Then app will download from the cloud and installed on the device before testing.
     *
     * How about remote execution?
     * There are couple famous web sites that are not free, but very useful for mobile testing.
     * http://www.browserstack.com/
     * http://saucelabs.com/
     * aws device farm
     *
     * For Emulators:
     * Docker container:
     * https://hub.docker.com/r/appium/appium/
     * container has appium server, emulator, vnc viewer. You can, very easily scale number of emulators.
     * But, for this solution you need server not VM. In terms of aws, you can use metal.
     *
     *      WE CAN RUN MOBILE CHROME
     *         --As Native application (Not recommended at all)
     *         --As a regular browser
     */
}
