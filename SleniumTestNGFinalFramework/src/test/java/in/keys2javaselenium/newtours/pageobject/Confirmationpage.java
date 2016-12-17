package in.keys2javaselenium.newtours.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Confirmationpage {
	private WebDriver driver = null;
	public Confirmationpage( WebDriver driver){
		this.driver=driver;
	}
	public String getConfirmationMessage(){
		String str=driver.findElement(By.xpath("//table[@width='492']/tbody/tr[3]/td[1]/p/font/b")).getText();
		
		System.out.println(str);
		return str;
		
	}
}
