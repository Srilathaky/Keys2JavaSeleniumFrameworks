package in.keys2javaselenium.newtours.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	private WebDriver driver = null;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}
	public void setFirstName(String fn){
		WebDriverWait wd=new WebDriverWait(driver,50);
		wd.until(ExpectedConditions.presenceOfElementLocated(By.name("firstName")));
		driver.findElement(By.name("firstName")).sendKeys(fn);
	}
	public void setLastName(String ln){
		driver.findElement(By.name("lastName")).sendKeys(ln);
	}
	public void setMailId(String mail){
		driver.findElement(By.name("userName")).sendKeys(mail);
	}
	public void setPhoneNumber(String phn){
		driver.findElement(By.name("phone")).sendKeys(phn);
	}
	public void clickOnRegLink(){
		driver.findElement(By.name("register")).click();
	}
}
