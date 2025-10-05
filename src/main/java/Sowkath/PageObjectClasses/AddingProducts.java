package Sowkath.PageObjectClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sowkath.Abstract.AbstractComponent;

public class AddingProducts extends AbstractComponent {
	
	WebDriver driver;
	
	
	//constructors
	public AddingProducts(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By waitBy = By.cssSelector(".mb-3");
	
	@FindBy (css=".mb-3")
	List <WebElement> products;
	
	By ProductSelection = By.cssSelector(".btn:last-of-type");
	
	
	
	public List<WebElement> getProductList() {
		
		WaitForElements(waitBy);
		return products;
	}
	
	public WebElement getProductByName(String purchase) {
		WebElement prod = products.stream().filter(p -> p.findElement(By.cssSelector("b"))
				.getText().equals(purchase)).findFirst().orElse(null);
		return prod;
	}
	
	public cartSelection addProductToCart(String purchase) {
		
		WebElement prod = getProductByName(purchase);
		prod.findElement(ProductSelection).click();
		cartSelection cart = new cartSelection(driver);
		return cart;
		
	}

}
