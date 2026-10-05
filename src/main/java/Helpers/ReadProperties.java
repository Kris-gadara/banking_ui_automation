package Helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties 
{
	static String url;
	static Properties props ;
	static InputStream fis = null;
	public static String getProperty(String key) 
	{
		
		try {
			props = readProperties("stage.properties");
			if(props.containsKey(key)) 
			{
				url = props.getProperty(key);
			}else {
				System.out.println("Keys Not Found");
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}
	private static Properties readProperties(String string) 
	{
		Properties prop = new Properties();
		String filepath = "/Configs/"+string;
		try {
			fis = ReadProperties.class.getResourceAsStream(filepath);
		if(fis!=null) 
		{
			prop.load(fis);
		}
		
		}catch(Exception e) 
		{
			System.out.println("Failed to Get File: "+ e.getLocalizedMessage());
			
		}
		return prop;
	}

}
