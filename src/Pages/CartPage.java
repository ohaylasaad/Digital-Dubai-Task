package Pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    private WebDriver driver;
    
    private By cartItem = By.id("shopping_cart_container");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    

    public boolean isProductInCart() {
    	
		return driver.findElement(cartItem).isDisplayed();
	}

    public void checkout() {
        
            driver.findElement(checkoutButton).click();
        
    }
}
