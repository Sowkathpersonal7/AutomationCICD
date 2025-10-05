package Sowkath.SeleniumFrameworkDesign;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Sowkath.PageObjectClasses.AddingProducts;
import Sowkath.PageObjectClasses.PaymentOrderPage;
import Sowkath.PageObjectClasses.cartSelection;
import Sowkath.TestComponents.BaseTest;



public class SubmitOrderTest extends BaseTest{

    @Test (dataProvider = "getData")
	public  void submitorder(HashMap<String,String> input) throws IOException, InterruptedException {
		
    	//using data provider and object 
//    	String gmail  = email;
//		String Pswd = psw;
//		String purchase = productss;
    	
    	//using data provider and hashmap 
    	String gmail = input.get("email");
    	String Pswd = input.get("psw");
    	String purchase = input.get("pro");
		
		//Declarations are done in LoginPage 
		
		LaunchApplication();
		login.username(gmail);
		login.password(Pswd);
		AddingProducts product = login.login();
		
		//waiting declraction are done in abstract class since its reusable 
		
		
		//Adding products from List 
		//object for each class will be created on the previous page last method for more reliable 
		List<WebElement> products = product.getProductList();
		WebElement prod = product.getProductByName(purchase);
		cartSelection cart = product.addProductToCart(purchase);
		
	   //clicking of cart from cartselection page

	   cart.cartclick();
	   
	   //product check and checkout is done here 
	   cart.getCartProduct();
	   boolean finalCart = cart.cartProductCheck(purchase);
	   AssertJUnit.assertEquals(finalCart, true);
	   PaymentOrderPage pay = cart.productCheckout();
	   
	  //payment is done here  
	  pay.sendCountry();
	  pay.getCountry();
	  pay.selectCountry();
	  pay.placeOrder();

	  //check the order status
	  WebElement ordersuccessful = pay.checkOrderStatus();
     System.out.println(ordersuccessful.getText());
     
  
	}
    @Test (dependsOnMethods = {"submitorder"})
    public void oderHistory() {
    	String gmail  = "sowkath_m@gmail.com";
		String Pswd = "Test@123";
    	login.username(gmail);
		login.password(Pswd);
		AddingProducts product = login.login();

    }
    
    //Method 1: sending data using data provider and object - which will take any type of data eg int, string, float etc.
    
//    @DataProvider
//    public Object[][] getData()
//    {
//    	return new Object[][] {{"sowkath_m@gmail.com","Test@123","ZARA COAT 3"},{"sowkath_m@gmail.com","Test@123","ADIDAS ORIGINAL"}};
//    }
    

    //Method 2: sending data using data provider and hashmap 
    //the HashMap will be decleared with 2 strings mean both key and values are string  
    
//    @DataProvider
//    public Object[][] getData()
//    {
//    	HashMap<String, String> map= new HashMap<String, String>();
//    	map.put("email", "sowkath_m@gmail.com");
//    	map.put("psw", "Test@123");
//    	map.put("pro", "ZARA COAT 3");
//    	
//    	HashMap<String, String> map1= new HashMap<String, String>();
//    	map1.put("email", "sowkath_m@gmail.com");
//    	map1.put("psw", "Test@123");
//    	map1.put("pro", "ADIDAS ORIGINAL");
//    	
//       return new Object [][] {{map},{map1}};
//    }
    
    //Method 3: sending data from Json using jackson and HashMap
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	List<HashMap<String, String>> data = getJsonToHashmap(System.getProperty("user.dir") + "/src/test/java/Sowkath/data/PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    }
}
 