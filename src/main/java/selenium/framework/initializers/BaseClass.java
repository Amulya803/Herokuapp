package selenium.framework.initializers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import selenium.framework.reports.ExtentManager;

public class BaseClass extends ExtentManager{

    public static WebDriver driver;
    protected static ExtentReports extent;
    public static ExtentTest logger;
    private String browser="chrome";

    public static WebDriver getDriver() {
        return driver;
    }
    
    private  void initializeDriver(String browserType){
    	//Initialize the chrome driver
        if(browserType.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite()  {
    	//Initialized
        initializeDriver(browser);
        getDriver().manage().window().maximize();
		
    }
    
    @BeforeMethod
    public void beforeMethod() {
    	//Navigate to Challenging Dom
    	driver.navigate().to("https://the-internet.herokuapp.com/challenging_dom");
    	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);           
    	extent = ExtentManager.getInstance();                                      

   }
    
    @AfterSuite(alwaysRun = true) 
    public void afterTest() {
    	//Driver Closed
    	getDriver().quit();
        extent.flush(); 
     }
}
