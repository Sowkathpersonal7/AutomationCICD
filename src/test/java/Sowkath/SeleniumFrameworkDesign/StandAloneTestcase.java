package Sowkath.SeleniumFrameworkDesign;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import Sowkath.PageObjectClasses.AddingProducts;
import Sowkath.PageObjectClasses.PaymentOrderPage;
import Sowkath.PageObjectClasses.cartSelection;
import Sowkath.TestComponents.BaseTest;



public class StandAloneTestcase extends BaseTest{

    @Test
	public  void submitorder() throws IOException, InterruptedException {
		String gmail  = "sowkath_m@gmail.com";
		String Pswd = "Test@123";
		String purchase = "ZARA COAT 3";
		

		
		LaunchApplication();
		login.username(gmail);
		login.password(Pswd);
		AddingProducts product = login.login();
		

		List<WebElement> products = product.getProductList();
		WebElement prod = product.getProductByName(purchase);
		cartSelection cart = product.addProductToCart(purchase);
		

	   cart.cartclick();
	   

	   cart.getCartProduct();
	   boolean finalCart = cart.cartProductCheck(purchase);
	   AssertJUnit.assertEquals(finalCart, true);
	   PaymentOrderPage pay = cart.productCheckout();
	   

	  pay.sendCountry();
	  pay.getCountry();
	  pay.selectCountry();
	  pay.placeOrder();


	  WebElement ordersuccessful = pay.checkOrderStatus();
     System.out.println(ordersuccessful.getText());

 
	  
	}

}
 