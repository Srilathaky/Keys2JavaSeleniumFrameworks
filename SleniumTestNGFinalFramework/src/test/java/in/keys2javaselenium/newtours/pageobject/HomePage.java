package in.keys2javaselenium.newtours.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver = null;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	
	public void clickRegisterLink(){
		WebDriverWait wd=new WebDriverWait(driver,50);
		wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'REGISTER')]"))).click();
		
	}
	public void clickRegisterLink1(){
	FluentWait<WebDriver> fw=new FluentWait<WebDriver>(driver);
		fw.withTimeout(120,TimeUnit.SECONDS);
		fw.pollingEvery(5, TimeUnit.SECONDS);
		WebElement regLink=fw.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'REGISTER')]")));
		
	}
	@FindBy(xpath="//a[contains(text(),'REGISTER')]")
	private WebElement regl;
	public void clickRegisterLink4(){
		regl.click();
	}
	@FindBy(how=How.XPATH,using="//a[contains(text(),'REGISTER')]")
	private WebElement regl2;
	public void clickRegisterLink5(){
		regl2.click();
	}
}
