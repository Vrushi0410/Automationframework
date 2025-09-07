package Pageclass;

	import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import java.time.Duration;
	import java.util.List;
import java.util.NoSuchElementException;

	public class RegistrationPage {
	    private WebDriver driver;
	    private WebDriverWait wait;

	    // Locators
	    private By firstName = By.cssSelector("input[placeholder='First Name']");
	    private By lastName = By.cssSelector("input[placeholder='Last Name']");
	    private By address = By.cssSelector("textarea[ng-model='Adress']");
	    private By email = By.cssSelector("input[ng-model='EmailAdress']");
	    private By phone = By.cssSelector("input[ng-model='Phone']");
	    private By genderMale = By.xpath("//input[@value='Male']");
	    private By genderFemale = By.xpath("//input[@value='FeMale']");
	    private By hobbiesCheckboxes = By.cssSelector("input[type='checkbox']");
	    private By languagesDropdown = By.id("msdd");
	    private By languagesOptions = By.xpath("//ul[contains(@class,'ui-corner-all')]//a");
	    private By skillsDropdown = By.id("Skills");
	    private By countryDropdown = By.id("countries");
	    private By selectCountryDropdown = By.xpath("//span[@role='combobox']");
	    private By selectCountryListItems = By.xpath("//ul[@id='select2-country-results']/li");
	    private By yearDropdown = By.id("yearbox");
	    private By monthDropdown = By.cssSelector("select[ng-model='monthbox']");
	    private By dayDropdown = By.id("daybox");
	    private By password = By.id("firstpassword");
	    private By confirmPassword = By.id("secondpassword");
	    private By submitButton = By.xpath("//button[@class='btn btn-primary'][1]");

	    public RegistrationPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
	    }

	    public void enterFirstName(String fname) {
	      //
	    	driver.findElement(firstName).sendKeys(fname);
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(fname);
	    }

	    public void enterLastName(String lname) {
	        driver.findElement(lastName).sendKeys(lname);
	    }

	    public void enterAddress(String addr) {
	        driver.findElement(address).sendKeys(addr);
	    }

	    public void enterEmail(String mail) {
	        driver.findElement(email).sendKeys(mail);
	    }

	    public void enterPhone(String phoneNumber) {
	        driver.findElement(phone).sendKeys(phoneNumber);
	    }

	    public void selectGender(String gender) {
	        gender = gender.toLowerCase();
	        if (gender.equals("male")) {
	            driver.findElement(genderMale).click();
	        } else if (gender.equals("female")) {
	            driver.findElement(genderFemale).click();
	        }
	    }

	    public void selectHobbies(String... hobbies) {
	        List<WebElement> allHobbies = driver.findElements(hobbiesCheckboxes);
	        for (String hobby : hobbies) {
	            hobby = hobby.toLowerCase();
	            for (WebElement checkbox : allHobbies) {
	                String value = checkbox.getAttribute("value").toLowerCase();
	                if (value.equals(hobby)) {
	                    if (!checkbox.isSelected()) {
	                        checkbox.click();
	                    }
	                }
	            }
	        }
	    }

	    // Languages dropdown is a bit special: multi-select opens a dropdown list.
	    public void selectLanguages(String... languages) {
	       // driver.findElement(languagesDropdown).click();
	        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(languagesDropdown));
	        dropdown.click();
	        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(languagesOptions));
	        for (String language : languages) {
	            boolean languageFound = false;
	            for (WebElement option : options) {
	                if (option.getText().equalsIgnoreCase(language)) {
	                    try {
	                        // Scroll to the element in case it's out of view or blocked
	                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);

	                        // Wait for the option to be clickable
	                        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	                        languageFound = true;
	                        break;
	                    } catch (ElementClickInterceptedException e) {
	                        // Fallback: try clicking with JavaScript
	                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
	                        languageFound = true;
	                        break;
	                    }
	                }
	            }

	            if (!languageFound) {
	                throw new NoSuchElementException("Language option not found: " + language);

	                }
	            }
	        // Click outside to close dropdown
	        driver.findElement(firstName).click();
	    }

	    public void selectSkill(String skill) {
	        Select skills = new Select(driver.findElement(skillsDropdown));
	        skills.selectByVisibleText(skill);
	    }

	    public void selectCountry(String country) {
	        Select countries = new Select(driver.findElement(countryDropdown));
	        countries.selectByVisibleText(country);
	    }

	    public void selectSelectCountry(String country) {
	        driver.findElement(selectCountryDropdown).click();
	        List<WebElement> countries = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(selectCountryListItems));
	        for (WebElement c : countries) {
	            if (c.getText().equalsIgnoreCase(country)) {
	                c.click();
	                break;
	            }
	        }
	    }

	    public void selectDOB(String year, String month, String day) {
	        Select yearDropdownSelect = new Select(driver.findElement(yearDropdown));
	        yearDropdownSelect.selectByVisibleText(year);

	        Select monthDropdownSelect = new Select(driver.findElement(monthDropdown));
	        monthDropdownSelect.selectByVisibleText(month);

	        Select dayDropdownSelect = new Select(driver.findElement(dayDropdown));
	        dayDropdownSelect.selectByVisibleText(day);
	    }

	    public void enterPassword(String pwd) {
	        driver.findElement(password).sendKeys(pwd);
	    }

	    public void enterConfirmPassword(String pwd) {
	        driver.findElement(confirmPassword).sendKeys(pwd);
	    }

	    public void submitForm() {
	        driver.findElement(submitButton).click();
	    }
	}
	

	

