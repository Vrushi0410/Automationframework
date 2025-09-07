package base;


    import io.github.bonigarcia.wdm.WebDriverManager;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;

	import org.testng.annotations.AfterClass;
	import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	import com.aventstack.extentreports.ExtentTest;

	import java.time.Duration;

	public class BaseTest {

	    public static WebDriver driver;
	    public static ExtentReports extent;
	    public static ExtentTest test;

	  //@Parameters({"browser"})
	    //@BeforeClass
	    public void setup(String browser) {
	        // Setup ExtentReports
	        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
	        extent = new ExtentReports();
	        extent.attachReporter(spark);

	        // Initialize WebDriver by browser param
	        if(browser.equalsIgnoreCase("chrome")) {
	            WebDriverManager.chromedriver().setup();
	            ChromeOptions opt = new ChromeOptions();
	            opt.addArguments("--headless");
	            driver = new ChromeDriver(opt);
	        } else if(browser.equalsIgnoreCase("firefox")) {
	            WebDriverManager.firefoxdriver().setup();
	            driver = new FirefoxDriver();
	        } else if(browser.equalsIgnoreCase("edge")) {
	            WebDriverManager.edgedriver().setup();
	            driver = new EdgeDriver();
	        } else {
	            System.out.println("Invalid browser! Defaulting to Chrome.");
	            WebDriverManager.chromedriver().setup();
	            driver = new ChromeDriver();
	        }

	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        driver.get("https://demo.automationtesting.in/Register.html");
	    }

	    @AfterClass
	    public void tearDown() {
	        if(driver != null) {
	            driver.quit();
	        }
	        if(extent != null) {
	            extent.flush();
	        }
	    }
	}

