package appium.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HowToSend extends BasePage{

    private By sendNow = By.id("il.co.mintapp.buyme:id/nowRadioButton");

    private By checkboxes = By.className("android.widget.CheckBox");

    private By inputText = By.className("android.widget.EditText");

    private By nextButton = By.id("il.co.mintapp.buyme:id/goNextButton");

    public HowToSend(AndroidDriver driver) {
        super(driver);
    }


    private List<WebElement> getCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public void clickSendNow() {
        super.click(sendNow);
    }

    public void selectEmailCheckBox() {
        WebElement email = getCheckboxes().get(2);
        email.click();
    }

    private List<WebElement> getInputText() {
        return driver.findElements(inputText);
    }

    public void insertEmail(String email) {
        for (WebElement textField : getInputText()) {
            if (textField.getText().equals("מייל:")) {
                textField.sendKeys(email);
            }
        }
    }

    public void clickOnNextButton(){
        super.click(nextButton);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
