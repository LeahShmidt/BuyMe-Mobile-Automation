package appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Intro extends BasePage {

    private By signIn = (By.id("il.co.mintapp.buyme:id/skipButton"));

    private By googleButton = By.xpath("//[text()='עם Google']");

    private By signInButton = By.className("android.widget.Button");

    public Intro(AndroidDriver driver)  {
        super(driver);
    }

    private List<WebElement> getSignInButtons (){
        return driver.findElements(signInButton);
    }

    public WebElement getSignIn() {
        WebElement signIn1 = driver.findElement(signIn);
        return signIn1;
    }


    public By getGoogleButton() {
        return googleButton;
    }

// click on sign in through google
    public void clickGoogleButton()  {

        List<WebElement> selectA = getSignInButtons();
        String selectedReason = selectA.get(1).getText();
        System.out.println(selectedReason);
        selectA.get(1).click();
        try {
            Thread.sleep(300);}
        catch (Exception e){
            System.out.println("wrong selection");
        }
    }
}
