package appium.pages;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import java.util.List;

public class Extras extends BasePage{

    private Duration fast = Duration.ofMillis(100);

    private By goBackArrow = By.xpath("//android.widget.ImageButton[@content-desc='נווט למעלה']");

    private By settings = By.id("il.co.mintapp.buyme:id/profileTab");

    private By search = By.id("il.co.mintapp.buyme:id/search");

    private By title = By.id("il.co.mintapp.buyme:id/title");

    private By titleText = By.id("il.co.mintapp.buyme:id/titleText");

    private By contentText = By.id("il.co.mintapp.buyme:id/contentText");


//swipe left, move right
    public void swipeLeft(){

        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        LongPressOptions longPressOptions = new LongPressOptions();
        PointOption fromPointOption = new PointOption();
        int startX = 1000;
        int startY = 500;
        fromPointOption.withCoordinates(startX, startY);
        PointOption toPointOption = new PointOption();
        int endX = 100;
        toPointOption.withCoordinates(endX, startY);
        longPressOptions.withDuration(fast).withPosition(fromPointOption).build();
        action.longPress(longPressOptions).moveTo(toPointOption).perform();
    }

    public Extras(AndroidDriver driver)  {
        super(driver);
    }

    public void clickGoBack(){
        WebElement goBack = driver.findElement(goBackArrow);
        goBack.click();

    }

    public void clickSettings(){
        super.click(settings);
    }

    public void clickOnSearch(){
        super.click(search);
    }

    private List<WebElement> getTitles() {
        return driver.findElements(title);
    }

//select the setting to click on
    public void selectTitleAndClick() {
        List<WebElement> titles = getTitles();
        String buyMe = titles.get(2).getText();
        if(buyMe.equals("על BUYME")){
            titles.get(2).click();
        }
    }

    public void assertTextIsBuyMe(){
        super.assertEquals(titleText,"מתנות זה דבר נפלא; יש בכוחן לעודד, לחבר, לגרום לסליחה ולהסב אושר, ולכן אנחנו ב-BUYME מאמינים שמתנות צריכות להיות משהו שכולו כיף, כיף לתת וכיף לקבל.");
    }

    public void printBuyMeText(){
        WebElement titleOfBuyMe = driver.findElement(titleText);
        WebElement contentOfBuyMe = driver.findElement(contentText);
        System.out.println(titleOfBuyMe.getText() + contentOfBuyMe.getText());
    }
}
