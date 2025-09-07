package Tsest;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
      import org.openqa.selenium.chrome.ChromeOptions;
     import org.testng.annotations.*;

    import Pageclass.RegistrationPage;
     import base.BaseTest;

import org.testng.Assert;

	import java.time.Duration;

	public class TestloginPage extends BaseTest  { 
	    RegistrationPage registrationPage;
	    
	    @BeforeClass
	    public void setup() {
	    setup("chrome");
	    }
	    @BeforeMethod
	    public void initPage() {
	        registrationPage = new RegistrationPage(driver);
	    }
    
	    
	    @Test
	    public void testUserRegistration() throws InterruptedException {
	        registrationPage.enterFirstName("John");
	        registrationPage.enterLastName("Doe");
	        registrationPage.enterAddress("123 Elm Street");
	        registrationPage.enterEmail("john.doe@example.com");
	        registrationPage.enterPhone("1234567890");
	        registrationPage.selectGender("Male");

	        registrationPage.selectHobbies("Cricket", "Movies");
             
	        registrationPage.selectLanguages("English", "Arabic");
	       

	        registrationPage.selectSkill("Java");

	        //registrationPage.selectCountry("India");

	        registrationPage.selectSelectCountry("Australia");

	        registrationPage.selectDOB("1985", "December", "10");

	        registrationPage.enterPassword("Password123");
	        registrationPage.enterConfirmPassword("Password123");

	        registrationPage.submitForm();

	        // Validation - demo site currently redirects to a blank page after submission.
	        // We can add a basic validation if URL changes or check page title etc.

	        String currentUrl = driver.getCurrentUrl();
	        // Assert that URL changed or page title changed after submitting form:
	        Assert.assertNotEquals(currentUrl, "https://demo.automationtesting.in/Register.html", "Form was not submitted successfully!");
	    }
     
	    @AfterMethod(alwaysRun = true)
	    public void tearDown() {
	        if(driver != null) {
	            driver.quit();
	        }
	    }
	    
	}