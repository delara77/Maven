package test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Magento {
	@Test
	public void Register(){
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://magento.com/");
		driver.findElement(By.className("account-icon")).click();
		driver.findElement(By.id("register")).click();
		driver.findElement(By.id("firstname")).sendKeys("Delara");
		driver.findElement(By.id("lastname")).sendKeys("Khan");
		driver.findElement(By.id("email_address")).sendKeys("delarakhan@outlook.com");
		driver.findElement(By.id("self_defined_company")).sendKeys("Yes M Systems");
		
		Select ct= new Select(driver.findElement(By.id("company_type")));
		ct.selectByVisibleText("Is a merchant who sells online");           
		//ct.selectByValue("selling");
		//ct.selectByIndex(1);
		
		Select role= new Select(driver.findElement(By.id("individual_role")));
		role.selectByValue("technical/developer");
		
		driver.findElement(By.id("password")).sendKeys("welcome123");
		driver.findElement(By.name("password_confirmation")).sendKeys("welcome123");
		
		driver.findElement(By.id("agree_terms")).click();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"recaptcha-f979c2ff515d921c34af9bd2aee8ef076b719d03\"]/div/div/iframe")));
		driver.findElement(By.className("recaptcha-checkbox-border")).click();
		driver.switchTo().defaultContent();
		
		Select country= new Select(driver.findElement(By.id("country")));
		country.selectByValue("US");
		
		if(!driver.findElement(By.id("agree_terms")).isSelected())
			driver.findElement(By.id("agree_terms")).click();

		driver.quit();
	}

	@Test(dependsOnMethods= {"Register"})                  //Dependency code: (dependsOnMethods= {"..."}) //Priority:(priority=0)
	public void login() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://magento.com/");
		driver.findElement(By.xpath("//*[@id=\"block-header\"]/ul/li[9]/a/span[1]/div")).click();
		driver.findElement(By.id("email")).sendKeys("delarakhan@outlook.com");
		driver.findElement(By.id("pass")).sendKeys("Password");
		driver.findElement(By.id("send2")).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText());
		
		driver.quit();

	}

	
	
}
