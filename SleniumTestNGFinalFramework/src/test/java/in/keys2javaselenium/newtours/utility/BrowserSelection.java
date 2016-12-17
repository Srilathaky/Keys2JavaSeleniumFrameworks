package in.keys2javaselenium.newtours.utility;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserSelection {
	private static WebDriver driver = null;

	public static WebDriver getBrowser(String browsername) {
		if (browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new InternetExplorerDriver();
		}

		return driver;
	}

	public static WebDriver getGitBrowser(String browsername) throws MalformedURLException {
		if (browsername.equalsIgnoreCase("firefox")) {
			DesiredCapabilities dc = DesiredCapabilities.firefox();
			dc.setBrowserName("firefox");
			dc.setPlatform(Platform.WINDOWS);
			driver=new RemoteWebDriver(new URL("http://localhost:5566/wd/hub"),dc);//client url
			//driver=new FirefoxDriver(dc)//if client and server are in same machine
		} else if (browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else {
			driver = new InternetExplorerDriver();
		}

		return driver;
	}

	public static void openUrl(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss");
		Date d = new Date();
		return df.format(d);

	}

	public static void getScreenShot() throws IOException {
		String destination = "c:\\Temp\\" + getCurrentDate() + " "
				+ driver.getTitle().replace(":", "_").replace("\\", "_") + ".png";
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination));
	}
}
