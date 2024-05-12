package Tests;
import Pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Epic("E-commerce")
@Feature("Order Processing")
public class TestAutomation {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private Properties testData;

    @BeforeClass
    public void setup() {
        
        System.setProperty("webdriver.chrome.driver", "./src/Resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        testData = new Properties();
        try {
            testData.load(new FileInputStream("src/Tests/testdata.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Login Test")
    @Description("Test to login to SauceDemo")
    @Story("User logs in to the application")
    public void testLogin() {
        String username = testData.getProperty("username");
        String password = testData.getProperty("password");
        loginPage.login(username, password);
        Assert.assertTrue(productsPage.isPageOpened(), "Login failed");
    }

    @Test(description = "Add Product to Cart Test", dependsOnMethods = {"testLogin"})
    @Description("Test to add a product to the cart")
    @Story("User adds a product to the cart")
    public void testAddProductToCart() {
        String productName = testData.getProperty("productName");
        productsPage.addProductToCart(productName);
        productsPage.goToCart();
        Assert.assertTrue(cartPage.isProductInCart(), "Product not added to cart");
    }

    @Test(description = "Proceed to Checkout Test", dependsOnMethods = {"testAddProductToCart"})
    @Description("Test to proceed to checkout")
    @Story("User proceeds to checkout")
    public void testProceedToCheckout() {
        cartPage.checkout();
        Assert.assertTrue(checkoutPage.isCheckoutPageOpened(), "Checkout page not opened or element not found");

    }

    @Test(description = "Fill Checkout Details Test", dependsOnMethods = {"testProceedToCheckout"})
    @Description("Test to fill checkout details")
    @Story("User fills checkout details")
    public void testFillCheckoutDetails() {
        String firstName = testData.getProperty("firstName");
        String lastName = testData.getProperty("lastName");
        String zipCode = testData.getProperty("zipCode");
        checkoutPage.fillCheckoutDetails(firstName, lastName, zipCode);
        checkoutPage.finishOrder();
        Assert.assertTrue(checkoutPage.isOrderCompleteMessageDisplayed(), "Order completion message not displayed");
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test Passed: " + result.getMethod().getDescription());
        }
    }
    @AfterSuite
    public void generateAllureReport() {
        String command = "allure serve target/allure-results";
        try {
            Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            System.out.println("Failed to generate Allure report: " + e.getMessage());
        }
    }
}

