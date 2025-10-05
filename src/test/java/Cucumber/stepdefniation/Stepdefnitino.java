package Cucumber.stepdefniation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

import Sowkath.PageObjectClasses.AddingProducts;
import Sowkath.PageObjectClasses.LoginPage;
import Sowkath.PageObjectClasses.PaymentOrderPage;
import Sowkath.PageObjectClasses.cartSelection;
import Sowkath.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefnitino extends BaseTest{
	public LoginPage login;
	public AddingProducts product;
	public cartSelection cart;
	public PaymentOrderPage pay;
	
	
	@Given ("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page () throws IOException {
		login = LaunchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		login.username(username);
		login.password(password);
		product = login.login();	
	}
	@When ("^I add the product to the cart (.+)$")
	public void I_add_the_product_to_the_cart (String productname) throws InterruptedException {
		List<WebElement> products = product.getProductList();
		WebElement prod = product.getProductByName(productname);
		cart = product.addProductToCart(productname);
		cart.cartclick();
		
	}
	@And ("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order (String productname) throws InterruptedException {
		   cart.getCartProduct();
		   boolean finalCart = cart.cartProductCheck(productname);
		   AssertJUnit.assertEquals(finalCart, true);
		   pay = cart.productCheckout();
		   pay.sendCountry();
		   pay.getCountry();
		   pay.selectCountry();
		   pay.placeOrder();
	}
	@Then ("{string} message is displayed on confirmation page")
	public void message_is_displayed_on_confirmation_page (String message) throws InterruptedException {
		 WebElement ordersuccessful = pay.checkOrderStatus();
	     System.out.println(ordersuccessful.getText());
	}

}
