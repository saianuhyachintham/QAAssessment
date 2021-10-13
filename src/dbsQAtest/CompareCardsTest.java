package dbsQAtest;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CompareCardsTest {
	
	WebDriver driver;
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "C:\\seldrivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://www.dbs.com.sg/personal/default.page#pb");
		driver.manage().window().maximize();
	}
	
	
  @Test
  public void comparingTheCardsDataOnWebSite() throws InterruptedException {
	  
	  	WebElement cards=driver.findElement(By.xpath("//div[contains(@class,'navbar-links-left hidden-xs')]//a[contains(text(),'Cards')]"));
		cards.click();
		
		
		driver.findElement(By.cssSelector("a[data-headinjectionajax='/personal/cards/cards-comparator/1488974818067.ajax']")).click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("window.scrollBy(0,550)"," ");
		WebElement card1=driver.findElement(By.xpath("//input[@id='cb0']"));
		
		executor.executeScript("arguments[0].scrollIntoView(true);",card1);
		Thread.sleep(3000);
		executor.executeScript("arguments[0].click()",card1);
		executor.executeScript("arguments[0].click()",driver.findElement(By.xpath("//label[@for='cb1']//div[1]")));
		
		WebElement compareButton=driver.findElement(By.xpath("//*[@id=\"cardCompareBtn\"]"));
		executor.executeScript("arguments[0].scrollIntoView(true);",compareButton);
		Thread.sleep(2000);
		compareButton.click();
		
		WebElement firstCard=driver.findElement(By.xpath("//*[@id=\"card0\"]/div[2]/div"));
		String firstCardName="DBS Altitude Visa Signature Card";
		
		WebElement secondCard=driver.findElement(By.xpath("//*[@id=\"card1\"]/div[2]/div"));
		String secondCardName="DBS Black Visa Card";
		
		Assert.assertEquals(firstCard.getText(), firstCardName);
		Assert.assertEquals(secondCard.getText(), secondCardName);
		
	  
  }
  @AfterClass
  public void teardown()
  {
	  driver.close();
  }
  
}
