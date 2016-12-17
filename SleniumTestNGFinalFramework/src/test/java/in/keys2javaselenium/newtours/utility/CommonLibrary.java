package in.keys2javaselenium.newtours.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CommonLibrary {
	public static String getPropertyValue(String key, String filename) throws IOException {
		FileInputStream fis = new FileInputStream("./TestData/" + filename + ".properties");
		Properties p = new Properties();
		p.load(fis);
		return p.getProperty(key);
	}
}
