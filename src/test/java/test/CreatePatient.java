package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreatePatient {
	@Test
	public void Createpatient() throws InterruptedException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//LogIn:
		driver.get("https://demo.openemr.io/a/openemr");
		driver.findElement(By.id("authUser")).clear();               //good practice to clear() then    goes with sendkeys()
		driver.findElement(By.id("authUser")).sendKeys("admin");
		driver.findElement(By.id("clearPass")).sendKeys("pass");
	    driver.findElement(By.xpath("//*[@id=\"login_form\"]/div[1]/div[1]/div[4]/button")).click();
		
        //Mouse hover:
	    Actions act= new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/div"))).perform();
		driver.findElement(By.xpath("//*[@id=\"mainMenu\"]/div/div[5]/div/ul/li[2]/div")).click();  //UI level click
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"framesDisplay\"]/div[3]/iframe")));
		Select title= new Select(driver.findElement(By.id("form_title")));
		title.selectByValue("Ms.");
		//Name:
	    driver.findElement(By.id("form_fname")).sendKeys("First");
	    driver.findElement(By.id("form_lname")).sendKeys("Last");
	    //DOB
	    driver.findElement(By.id("form_DOB")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.className("xdsoft_today")).click();
	    
	    //Gender:
	    Select gender = new Select(driver.findElement(By.id("form_sex")));
	    gender.selectByValue("Female");
	    
	    driver.findElement(By.id("create")).click();
	    driver.switchTo().defaultContent();
	    
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"modalframe\"]")));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"searchResultsHeader\"]/center/input")).click();
	    driver.switchTo().defaultContent();
	    
	    Thread.sleep(3000);
	    
	    System.out.println(driver.switchTo().alert().getText());
	    driver.switchTo().alert().accept();
	    
	   
	    
	    
	   
		Thread.sleep(5000);
		//driver.quit();	

	}
}