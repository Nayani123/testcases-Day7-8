package seleniumPractiseSessions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day7_HondaWheelers {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		//1.open url
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElementByXPath("//button[@class='close']").click();
		
		//2) Click on scooters and click dio
		Thread.sleep(2000);
		driver.findElementById("link_Scooter").click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']")));
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
		
		
		//3) Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(2000);
		WebElement engine = driver.findElementByXPath("//a[text()='ENGINE']");
		Thread.sleep(2000);
		Actions engine2=new Actions(driver);
		engine2.moveToElement(engine).perform();
		
		
		//4) Get Displacement value
		String Diodisvalue = driver.findElementByXPath("//span[text()='109.51cc']").getText();
		System.out.println(Diodisvalue);
		float diodisval = Float.parseFloat(Diodisvalue.replaceAll("c",""));
		System.out.println(diodisval);
		
		
		//5) Go to Scooters and click Activa 125
		Thread.sleep(3000);
		driver.findElementById("link_Scooter").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		Thread.sleep(2000);
		
		//6) Click on Specifications and mouseover on ENGINE
		driver.findElementByLinkText("Specifications").click();
		Thread.sleep(2000);
		WebElement Activa = driver.findElementByXPath("//a[text()='ENGINE']");
		Actions Activa1=new Actions(driver);
		Activa1.moveToElement(Activa).perform();
		
		//7) Get Displacement value
		Thread.sleep(2000);
		String Activadisvalue = driver.findElementByXPath("//span[text()='124 cc']").getText();
		System.out.println(Activadisvalue);
		float actdisval = Float.parseFloat(Activadisvalue.replaceAll("c",""));
		System.out.println(actdisval);
		
		
		//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(diodisval>actdisval) {
			System.out.println("Dio is better displacememnt");
			
		}else {
			System.out.println("Activa is better displacement");
		}
		
		//9) Click FAQ from Menu
		Thread.sleep(2000);
		driver.findElementByLinkText("FAQ").click();
		
		//10) Click Activa 125 BS-VI under Browse By Product
		Thread.sleep(2000);
		driver.findElementByLinkText("Activa 125 BS-VI").click();
		
		
		//11) Click  Vehicle Price 
		Thread.sleep(2000);
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
		
		
		//12) Make sure Activa 125 BS-VI selected and click submit
		Thread.sleep(2000);
		if(driver.findElementByXPath("//option[@selected='selected' and text()='Activa 125 BS-VI']").isSelected())
		{
			System.out.println("Activa 125 is selected");
		}
		else System.err.println("Activa 125 is not selected");
		Thread.sleep(500);
		driver.findElementByXPath("//button[@id='submit6']").click();
		
		
		//13) click the price link
		Thread.sleep(2000);
		driver.findElementByLinkText("Click here to know the price of Activa 125 BS-VI.").click();
		
		
		//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> Listwindowhandle=new ArrayList<String>(windowHandles);
		driver.switchTo().window( Listwindowhandle.get(1));
		
		WebElement state = driver.findElementById("StateID");
		Select state1=new Select(state);
		state1.selectByVisibleText("Tamil Nadu");
		
		WebElement city = driver.findElementById("CityID");
		Select city1=new Select(city);
		city1.selectByValue("1524");
		
		
		//15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		
		
		//16) Print all the 3 models and their prices
		Thread.sleep(2000);
		Map<String,String> map=new LinkedHashMap<String,String>();
		List<WebElement> List = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr/td[2]");
		List<WebElement> Model = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr/td[contains(text(),'ACTIVA')]");
		List<WebElement> Price = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr/td[contains(text(),'Rs')]");
		
		
		for(int i=0;i<List.size();i++)
		{
			String modelType= Model.get(i).getText();
			String modelPrice=Price.get(i).getText();
			map.put(modelType, modelPrice);
		}
		for (Entry<String,String> keyValue : map.entrySet()) 
		{
			System.out.println("Model with prices ");
			System.out.println(keyValue.getKey()+" - "+keyValue.getValue());
		}
		
		//17) Close the Browser
		driver.quit();
		
		
		
		

	}

}
