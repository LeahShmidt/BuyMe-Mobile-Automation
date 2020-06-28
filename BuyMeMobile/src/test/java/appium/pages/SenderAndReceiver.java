package appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class SenderAndReceiver extends BasePage{

    private By scrollArrow = By.id("il.co.mintapp.buyme:id/scrollArrows") ;

    private By toField = By.id("il.co.mintapp.buyme:id/toEditText");

    private By greetingType = By.xpath("//*[@class='android.widget.Spinner']/child::android.widget.TextView");

    private By fromField = By.id("il.co.mintapp.buyme:id/userFrom");

    private By nextButton = By.id("il.co.mintapp.buyme:id/goNextButton");

    private By greetingTypes = By.className("android.widget.CheckedTextView");


    public SenderAndReceiver(AndroidDriver driver)  {
        super(driver);
    }

    public void clickScrollArrow() {
    super.click(scrollArrow);
    }

    private WebElement getGreetingType() {
        return driver.findElement(greetingType);
    }

    private List<WebElement> getGreetingTypes (){
        return driver.findElements(greetingTypes);
    }

    public void getToWhoField(String to) {
    super.writeText(toField,to);
    }

//select a greeting from the drop down
    public void getGreetingField(){
        getGreetingType().click();
        WebElement greeting = getGreetingTypes().get(4);
        greeting.click();
    }

    public void getFromWhoField(String from){
        driver.findElement(fromField).clear();
        super.writeText(fromField, from);
    }

    public void clickOnNextButton(){
        super.click(nextButton);
    }
}

