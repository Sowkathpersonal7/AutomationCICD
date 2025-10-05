package Sowkath.SeleniumFrameworkDesign;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Sowkath.PageObjectClasses.AddingProducts;
import Sowkath.PageObjectClasses.cartSelection;
import Sowkath.TestComponents.BaseTest;



public class ErrorHandling extends BaseTest{

    @Test (retryAnalyzer = Sowkath.TestComponents.Retry.class)
	public  void submitorder() throws IOException, InterruptedException {
		String gmail  = "sowkath@gmail.com";
		String Pswd = "Test@3";
		
		//Declarations are done in LoginPage 
		
		login.username(gmail);
		login.password(Pswd);
		login.login();
		System.out.println(login.errorMessage());
		AssertJUnit.assertEquals("Incorrect email or password.", login.errorMessage());
	  
	}
    
    @Test
    public void productErrorValidation() throws InterruptedException {
    	String gmail  = "sowkath_m@gmail.com";
		String Pswd = "Test@123";
		String purchase = "ZARA COAT 3";
		login.username(gmail);
		login.password(Pswd);
		AddingProducts product = login.login();
    	List<WebElement> products = product.getProductList();
		WebElement prod = product.getProductByName(purchase);
		cartSelection cart = product.addProductToCart(purchase);
		
	   //clicking of cart from cartselection page

	   cart.cartclick();
	   
	   //product check and checkout is done here 
	   cart.getCartProduct();
	   boolean finalCart = cart.cartProductCheck(purchase);
	   AssertJUnit.assertEquals(finalCart, true);
    	
    }

}
 