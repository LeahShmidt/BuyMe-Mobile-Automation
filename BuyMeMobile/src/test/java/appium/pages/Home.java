package appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Home extends BasePage{

    private By pageViewer = By.className("android.support.v4.view.ViewPager");

    private By cardNames = By.id("il.co.mintapp.buyme:id/businessName");

    private By price = By.className("android.widget.EditText");

    private By purchaseButton = By.id("il.co.mintapp.buyme:id/purchaseButton");

    public Home(AndroidDriver driver)  {
        super(driver);
    }

    public void selectGiftCategory(){
        WebElement viewer = child_Of_ParentElement(driver.findElement(pageViewer),"//*[@class='android.widget.FrameLayout']/child::android.widget.RelativeLayout[4]");
        viewer.getText();
        viewer.click();
    }

    private List<WebElement> getCardNames() {
        return driver.findElements(cardNames);
    }

    public WebElement getPrice() {
        return driver.findElement(price);
    }

    private WebElement getPurchaseButton() {
        return driver.findElement(purchaseButton);
    }

    public void clickOnPurchaseButton(){
        getPurchaseButton().click();
    }

//select one of the gift cards
    public void selectCard(){
        List<WebElement> cards = getCardNames();
        for(WebElement oneCard : cards){
            System.out.println(oneCard.getText());
            oneCard.click();
            break;
            }
    }

//find the child element inside a specific webelement
    private WebElement child_Of_ParentElement(WebElement parentElement, String child_xpath) {
        return parentElement.findElement(By.xpath(child_xpath));
    }


}
