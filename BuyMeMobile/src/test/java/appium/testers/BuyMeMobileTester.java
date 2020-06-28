package appium.testers;

import appium.pages.*;
import appium.utils.Files;
import appium.utils.Screenshot;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import atu.testrecorder.ATUTestRecorder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class BuyMeMobileTester {
//Androidscreencast needs to be installed and opened in order to record the mobile screen.
// It enables you to share your android mobile device screen with your PC, then the test will be recorded.

    private static AndroidDriver driver;
    private Screenshot screenshot = new Screenshot();
    private static Files files = new Files();

    private String currentTime = String.valueOf(System.currentTimeMillis());
    private static ExtentReports extent ;
    private static ExtentTest test ;
    private static String PATH ;
    private static DateFormat df = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
    private static Date date = new Date();
    private static ATUTestRecorder recorder;
    private static String videoFolder = "C:\\Users\\lshmidt\\AppData\\Local\\Temp\\ScriptVideos\\";
    private static String videoFile = "TestVideo-" + df.format(date);

    public BuyMeMobileTester() {
    }

    @BeforeClass
        public static void setUp() throws IOException, ATUTestRecorderException, ParserConfigurationException, SAXException {
            String appPackage = files.readFile("appPackage");
            String   appActivity = files.readFile("appActivity");
            PATH = files.readFile("path");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120);
            capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\lshmidt\\Desktop\\extent.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            test = extent.createTest("BuyMe Mobile Automation Tests", "Sign up, choose present, and send");
            extent.setSystemInfo("Environment", "Production");
            extent.setSystemInfo("Tester", "Leah Shmidt");
            test.log(Status.INFO, "@Before class");

            //Created object of ATUTestRecorder
            //Provide path to store videos and file name format.
            recorder = new ATUTestRecorder(videoFolder,videoFile,false);
            recorder.start();

            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        }

@Test
public void A_login() throws IOException {
    Intro intro = new Intro(driver);
    intro.getSignIn().click();
    intro.clickGoogleButton();
    test.log(Status.PASS, "user is signing in");
    test.pass("log In: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
        }

@Test
public void B_chooseAccount(){
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
        driver.pressKey(new KeyEvent(AndroidKey.TAB));
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));

}

@Test
public void C_selectCategory(){
    Home home = new Home(driver);
    home.selectGiftCategory();
}
@Test
public void D_chooseCard() throws IOException {
    Home home = new Home(driver);
    home.selectCard();
    test.log(Status.PASS, "select gift card");
    test.pass("user selected gift card: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());

}

@Test
public void E_enterPrice() throws IOException {
    Home home = new Home(driver);
    home.getPrice().sendKeys("300");
    home.clickOnPurchaseButton();
    test.log(Status.PASS, "enter 300");
    test.pass("send amount of card: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
}
@Test
public void F_enterSendingInfo() throws IOException {
    SenderAndReceiver senderAndReceiver = new SenderAndReceiver(driver);
    senderAndReceiver.clickScrollArrow();
    senderAndReceiver.getToWhoField("for my sweet Libby!");
    senderAndReceiver.getFromWhoField("from Mommy");
    senderAndReceiver.getGreetingField();
    test.log(Status.PASS, "sending info");
    test.pass("sender and receiver info: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
    senderAndReceiver.clickOnNextButton();
}

@Test
public void G_enterMoreInfoOnHowToSend() throws IOException {
    HowToSend howToSend = new HowToSend(driver);
    howToSend.clickSendNow();
    howToSend.selectEmailCheckBox();
    howToSend.insertEmail("leah@gmail.com");
    test.log(Status.PASS, "email info");
    test.pass("email: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
    howToSend.clickOnNextButton();
}

@Test
public void H_selectCategoryWithSwipe()  {
    Extras extras = new Extras(driver);
    extras.clickGoBack();
    extras.clickGoBack();
    extras.clickGoBack();
    extras.clickGoBack();

    System.out.println("continue");

   extras.swipeLeft();
   extras.clickOnSearch();

}


@Test
public void I_enterSettingsMenu() throws IOException {
            Extras extras = new Extras(driver);
            extras.clickSettings();
    test.log(Status.PASS, "user enters the settings menu");
    test.pass("settings: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
}

@Test
public void J_printText() throws IOException {
Extras extras = new Extras(driver);
extras.selectTitleAndClick();
extras.assertTextIsBuyMe();
    test.log(Status.PASS, "user enters buyme selection");
    test.pass("buyme: ", MediaEntityBuilder.createScreenCaptureFromPath(screenshot.takeScreenShot(driver,
            PATH + currentTime)).build());
extras.printBuyMeText();

}

    @Test
    public void createAndAttachScreencast()  {
        test.log(Status.INFO,"<a href='" + videoFolder + videoFile + ".mov" +
                "'><span class='label info'>Link to video</span></a>");
    }
        @AfterClass
        public static void tearDown() throws ATUTestRecorderException {
            recorder.stop();
            driver.quit();
            test.log(Status.INFO, "completed tests");
            extent.flush();
        }
}
