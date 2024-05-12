package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
    private WebDriver driver;
    private By productList = By.id("inventory_container");
    private By cartButton = By.className("shopping_cart_badge");
    

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPageOpened() {
        return driver.findElement(productList).isDisplayed();
    }
    
    
	public void addProductToCart(String productName) {
		
		 WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item_label']/following-sibling::div//button"
				 ));
         addToCartButton.click();

   }


	public void goToCart() {
        driver.findElement(cartButton).click();
    }



	
	
}
