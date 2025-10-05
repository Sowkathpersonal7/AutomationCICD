package Sowkath.PageObjectClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Sowkath.Abstract.AbstractComponent;

public class LoginPage extends AbstractComponent {
	
	WebDriver driver;
	
	//constructors
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//elements by findby element 
	@FindBy (id="userEmail")
	WebElement usernamefield;
	
	
	@FindBy (id="userPassword")
	WebElement passwordfield;
	
	@FindBy (id="login")
	WebElement loginBtn;
	
	@FindBy (css="[class*='flyInOut']")
	WebElement errorMessage;
	
	//action
	
	public void gotoo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	public void username (String username) {
		
		usernamefield.sendKeys(username);
	}
	
    public void password (String password) {
		
		passwordfield.sendKeys(password);
	}
    public String errorMessage() throws InterruptedException {
    	WaitForElementToAppear(errorMessage);
    	return 	errorMessage.getText();
    }
    
    public AddingProducts login () {
		
		loginBtn.click();
		AddingProducts product = new AddingProducts(driver);
		return product;
	}
	

}
