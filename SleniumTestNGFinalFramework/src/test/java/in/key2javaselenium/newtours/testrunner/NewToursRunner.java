package in.key2javaselenium.newtours.testrunner;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import in.keys2javaselenium.newtours.pageobject.Confirmationpage;
import in.keys2javaselenium.newtours.pageobject.HomePage;
import in.keys2javaselenium.newtours.pageobject.RegisterPage;
import in.keys2javaselenium.newtours.utility.BrowserSelection;
import in.keys2javaselenium.newtours.utility.NewToursListeners;
import in.keys2javaselenium.newtours.utility.XLReader;
@Listeners(NewToursListeners.class)
public class NewToursRunner {
	WebDriver driver = null;
	HomePage homepg = null;
	RegisterPage regpg = null;

	@BeforeMethod
	@Parameters({ "browsername", "url" })
	public void init(@Optional("firefox") String browsername, @Optional("http://newtours.demoaut.com") String url) {

		driver = BrowserSelection.getBrowser(browsername);
		BrowserSelection.openUrl(url);
		homepg = PageFactory.initElements(driver, HomePage.class);


	}

	@Test(dataProvider = "newtours", dataProviderClass = XLReader.class)

	public void verifyHomePage(String pageTitle, String rownum) throws Throwable {
		String testResult="fail";
		String errorMsg="";
		try{
		String actualpageTitle = homepg.getTitle();
		System.out.println(pageTitle);
		System.out.println(actualpageTitle);
		Assert.assertEquals(pageTitle, actualpageTitle);
		testResult="pass";
		// Assert.assertTrue(pageTitle.equalsIgnoreCase(actualpageTitle));

		}
		catch(Throwable e){
			errorMsg=e.getMessage();
			throw e;
		}
		finally{
			XLReader.writexl(testResult,new Integer(rownum),16);
			XLReader.writexl(errorMsg,new Integer(rownum),17);
		}
	}

	@Test(dataProvider = "newtours", dataProviderClass = XLReader.class)
	public void verifyRegistrationProcess(String data1, String data2, String data3, String data4, String data5,
			String data6, String data7, String data8, String data9, String data10, String data11, String data12,
			String data13, String rownum) throws InterruptedException {
		homepg.clickRegisterLink();
		regpg=PageFactory.initElements(driver, RegisterPage.class);
		regpg.setFirstName(data1);
		regpg.setLastName(data2);
		regpg.setPhoneNumber(data3);
		regpg.setMailId(data4);
		System.out.println(rownum);
		regpg.clickOnRegLink();
		Confirmationpage cp=new Confirmationpage(driver);
/*		if(data13.contains(cp.getConfirmationMessage())){
			System.out.println("pass");
		}else{
			System.out.println("Fail");
		}*/
		Assert.assertTrue(data13.contains(cp.getConfirmationMessage()));
		
		
		
	}

}
