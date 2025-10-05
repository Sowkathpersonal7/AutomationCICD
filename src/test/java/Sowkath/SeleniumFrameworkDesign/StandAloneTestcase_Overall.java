package Sowkath.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Sowkath.PageObjectClasses.AddingProducts;
import Sowkath.PageObjectClasses.LoginPage;
import Sowkath.PageObjectClasses.PaymentOrderPage;
import Sowkath.PageObjectClasses.cartSelection;



public class StandAloneTestcase_Overall {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String gmail  = "sowkath_m@gmail.com";
		String Pswd = "Test@123";
		String purchase = "ZARA COAT 3";
		
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		//Declarations are done in LoginPage 
		LoginPage login = new LoginPage(driver);
		login.username(gmail);
		login.password(Pswd);
		login.login();
		
		//waiting declraction are done in abstract class since its reusable 
		
		
		//Adding products from List 
		AddingProducts product = new AddingProducts(driver);
		List<WebElement> products = product.getProductList();
		WebElement prod = product.getProductByName(purchase);
		product.addProductToCart(purchase);
		
		
	   //WebElement w = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
	   //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ngx-spinner-overlay"))));
	   //JavascriptExecutor js = (JavascriptExecutor) driver;
	   //js.executeScript("window.scroll(0,0)");
	   
//	   WebElement cartBtn = driver.findElement(By.cssSelector("[routerlink*='/cart']"));
//       cartBtn.click();
		
	   //clicking of cart from cartselection page
	   cartSelection cart = new cartSelection(driver);
	   cart.cartclick();
	   
	   //product check and checkout is done here 
	   cart.getCartProduct();
	   boolean finalCart = cart.cartProductCheck(purchase);
	   Assert.assertEquals(finalCart, true);
	   cart.productCheckout();
	   //List<WebElement> cartPurch = driver.findElements(By.cssSelector(".cartSection h3"));
	   //WebElement finalCart = cartPurch.stream().filter(p->p.findElement(By.cssSelector(".cartSection h3"))
			 //  .getText().equals(purchase)).findFirst().orElse(null);
	   //boolean finalCart = cartPurch.stream().anyMatch(p->p.getText().equalsIgnoreCase(purchase));	
	   //System.out.println(finalCart);
	   
//	  driver.findElement(By.cssSelector(".subtotal button")).click();
	  //method 1 for selection
	  //driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("IND");
	  
    //  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	   
	  PaymentOrderPage pay = new PaymentOrderPage(driver);
	  pay.sendCountry();
	  pay.getCountry();
	  pay.selectCountry();
	  pay.placeOrder();
//	  List<WebElement> country = driver.findElements(By.cssSelector(".ta-results button"));

//	  for (WebElement con : country)
//	  {
//		  if (con.getText().equalsIgnoreCase("India"))
//		  {
//			  con.click();
//			  break;
//		  }
//	  }
	  //method 2 with streams
//     country.stream().filter(p->p.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
//     driver.findElement(By.cssSelector(".btnn")).click();
//     WebElement ordersuccessful = driver.findElement(By.cssSelector(".hero-primary"));
	  WebElement ordersuccessful = pay.checkOrderStatus();
     System.out.println(ordersuccessful.getText());
     driver.quit();
 
	  
	}

}
 