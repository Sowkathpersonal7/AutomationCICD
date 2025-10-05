package Sowkath.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Sowkath.PageObjectClasses.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage login;
	ChromeOptions options;

	public void intializeDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Sowkath//resources//GlobalData.properties");
		prop.load(stream);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");
		
		
//		if (browserName.equalsIgnoreCase("chrome"))
//		{
//			if (browserName.equalsIgnoreCase("chromeheadless")){
//				options = new ChromeOptions();
//				options.addArguments("headless");
//			}
//			
//		    driver = new ChromeDriver(options);
//		}
//		else if (browserName.equalsIgnoreCase("edge")){
//
//			 driver = new EdgeDriver();
//		}
//		else if (browserName.equalsIgnoreCase("firefox"))
//		{
//			driver = new FirefoxDriver();
//		}
		
		
		if (browserName.equalsIgnoreCase("chrome")) {
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions options = new ChromeOptions();   // always initialize
		    driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("chromeheadless")) {
		    WebDriverManager.chromedriver().setup();
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--headless=new");        // modern headless flag
		    driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("edge")) {
		    WebDriverManager.edgedriver().setup();
		    driver = new EdgeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
		    WebDriverManager.firefoxdriver().setup();
		    driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@BeforeMethod
	public  LoginPage LaunchApplication() throws IOException {
		intializeDriver();
		login = new LoginPage(driver);
		login.gotoo();
		return login;
	}
	@AfterMethod
	public void terminate() {
		driver.close();
	}
	public List<HashMap<String, String>> getJsonToHashmap(String filepath) throws IOException {
		//read Json to String
		String jsonContent = FileUtils.readFileToString(
			    new File(filepath),
			    StandardCharsets.UTF_8);	
		//String to HashMap - using Jackson Datbind
		ObjectMapper mapper = new ObjectMapper ();
		List<HashMap<String, String>> data = mapper.readValue(
			    jsonContent,
			    new TypeReference<List<HashMap<String, String>>>() {}
			);
		return data;
		
	}
	
	public String getScreenShots(String testcasename, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+ testcasename + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+ testcasename + ".png";
	}
	
}
