package Sowkath.PageObjectClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sowkath.Abstract.AbstractComponent;

public class PaymentOrderPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructors
	public PaymentOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countyrlist;
	
	@FindBy(css=".btnn")
	WebElement placeorder;
	
	@FindBy(css=".hero-primary")
	WebElement ordersuccess;
	
	public void sendCountry() {
		country.sendKeys("IND");
	
	}
	public List<WebElement> getCountry() throws InterruptedException {
		threadWait();
		List<WebElement> country = countyrlist;
		return country;
	}
	public void selectCountry() throws InterruptedException {
		getCountry().stream().filter(p->p.getText().equalsIgnoreCase("India")).findFirst().ifPresent(WebElement::click);
		
	}
	public void placeOrder() {
		placeorder.click();
		
	}
	public WebElement checkOrderStatus() {
		WebElement ordersuccessful = ordersuccess;
		return ordersuccessful;
	}

}
