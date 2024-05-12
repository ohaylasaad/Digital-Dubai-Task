package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private WebDriver driver;
    private By checkoutTitle = By.xpath("//span[contains(text(),'Checkout: Your Information')]");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By zipCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By orderCompleteMessage = By.xpath("//h2[text()='Thank you for your order!']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

   

	public void fillCheckoutDetails(String firstName, String lastName,  String zipCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(zipCodeField).sendKeys(zipCode);
        driver.findElement(continueButton).click();
    }

    public void finishOrder() {
        driver.findElement(finishButton).click();
    }
    
	public boolean isCheckoutPageOpened() {
		
		 WebElement titleElement = driver.findElement(checkoutTitle);
	        return titleElement.isDisplayed();
	}


    public boolean isOrderCompleteMessageDisplayed() {
        return driver.findElement(orderCompleteMessage).isDisplayed();
    }



}
