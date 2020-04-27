package seleniumPractiseSessions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day8_pepperfry {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		 //2) Mouseover on Furniture and click Office Chairs under Chairs
		WebElement furniture =  driver.findElementByXPath("(//a[text()='Furniture'])[1]");
		Actions  furn=new Actions(driver);
		furn.moveToElement(furniture).perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
		Thread.sleep(2000);

				
	    //3) click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		Thread.sleep(2000);
		
		
		//4) Change the minimum Height as 50 in under Dimensions
		wait1.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]")));
	    WebElement value = driver.findElementByXPath("(//input[@class='clipFilterDimensionHeightValue'])[1]");
	    value.clear();
	    value.sendKeys("50");
	
		//5) Add "Poise Executive Chair in Black Colour" chair to Wishlist
	    Thread.sleep(3000);
	    wait1.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[contains(@data-productname,'Poise Executive Chair in Black Colour')]")));
	    driver.findElementByXPath("//a[contains(@data-productname,'Poise Executive Chair in Black Colour')]").click();
	    
		//6) Mouseover on Homeware and Click Pressure Cookers under Cookware
	    Thread.sleep(2000);
	    WebElement cookware = driver.findElementByXPath("(//a[text()='Homeware'])[1]");
	    Actions cookware1=new Actions(driver);
	    cookware1.moveToElement(cookware).perform();
	    driver.findElementByXPath("//a[text()='Pressure Cookers']").click();
		
		//7) Select Prestige as Brand
	    driver.findElementByXPath("//label[@for='brandsnamePrestige']").click();
		
		//8) Select Capacity as 1-3 Ltr
	    Thread.sleep(2000);
	    driver.findElementByXPath("//label[@for='capacity_db1_Ltr_-_3_Ltr']").click();
		
		//9) Add "Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr" to Wishlist
	    Thread.sleep(2000);
	    driver.findElementByXPath("//a[contains(@data-productname,'Nakshatra Cute Metallic Red Aluminium Cooker 2 Ltr')]").click();
		
        //10) Verify the number of items in Wishlist
	    String numberitems = driver.findElementByXPath("//a[@data-tooltip='Wishlist']/following::span[@class='count_alert'][1]").getText();
	    if(numberitems.equalsIgnoreCase("2")) {
			System.out.println(" number of items added in the wishlist is 2");
		}else {
			System.out.println(" number of items added in the wishlist is not equal to 2");
		}
		
		//11) Navigate to Wishlist
	    Thread.sleep(2000);
	    driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		
		
		//12) Move Pressure Cooker only to Cart from Wishlist
	    driver.findElementByXPath("//a[@unbxdattr='AddToCart']").click();
		
		//13) Check for the availability for Pincode 600128
	    Thread.sleep(2000);
		driver.findElementByXPath("//input[@class='srvc_pin_text']").sendKeys("600128",Keys.ENTER);
		
		//14) Click Proceed to Pay Securely
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		
		//15 Click Proceed to Pay
		driver.findElementByXPath("(//a[text()='PLACE ORDER'])[1]").click();
		
		
		//16) Capture the screenshot of the item under Order Item
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@id='ordersummary_accordian_header']").click();
		Thread.sleep(2000);
		File src = driver.findElementByXPath("//div[@class='slick-list draggable']//li").getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/snap1.png");
		FileUtils.copyFile(src, dst);
		
		//17) Close the browser
		driver.close();

	}

}
