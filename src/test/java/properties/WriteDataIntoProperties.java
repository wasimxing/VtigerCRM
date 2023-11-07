package properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WriteDataIntoProperties {

	public static void main(String[] args) throws IOException  {
		
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");

		Properties p = new Properties();
		p.load(fis);

		System.out.println(p.getProperty("url"));

		p.put("OS", "Windows");
		FileOutputStream fos = new FileOutputStream("./src/test/resources/commonData.properties");
		p.store(fos, "Updated successfully");

	}

}
