package frameworkUtility;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FuncUtilLibrary{

Properties prop = new Properties();
InputStream input = null;

static String filepath = "C:/Users/sarve/eclipse-workspace/Projectibibo/src/test/resources/Config.properties";

public FuncUtilLibrary()  {
	
	loadProperties();
}

public void loadProperties() {
	
	try {
		 input = new FileInputStream(filepath);
		prop.load(input);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public String readProperty(String keyname) {
	
	return prop.getProperty(keyname);
}
}


