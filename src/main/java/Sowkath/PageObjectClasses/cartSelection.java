package Sowkath.PageObjectClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sowkath.Abstract.AbstractComponent;

public class cartSelection extends AbstractComponent {
	
	WebDriver driver;
	
	//constructors
	public cartSelection(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy (css="[routerlink*='/cart']")
	WebElement cartsel;
	
	@FindBy (css=".cartSection h3")
	List<WebElement> cartPurch;
	
	@FindBy (css=".subtotal button")
	WebElement checkout;

	public void cartclick() throws InterruptedException {
		Thread.sleep(2000);
		WebElement cartBtn = cartsel;
		cartBtn.click();
	}
	
	public List<WebElement> getCartProduct() {
		List<WebElement> cartPurchs = cartPurch;
		return cartPurchs;
		
	}
	
	
	public boolean cartProductCheck(String purchase) {
		boolean finalCart = getCartProduct().stream().anyMatch(p->p.getText().equalsIgnoreCase(purchase));
	    return finalCart;
	    
	}
	
    public PaymentOrderPage productCheckout() {
    	checkout.click();
    	PaymentOrderPage pay = new PaymentOrderPage(driver);
    	return pay;
	}

}
