package com.qainfotech.tap.training.resourceio;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
    
    public Object getOptionValue(String optionKey) throws IOException {
    	FileReader filereader=new FileReader("src/main/resources/options.properties");
    	
    	Properties properties=new Properties();
    	properties.load(filereader);
    	return properties.getProperty(optionKey);
    	
       
    }

    public void addOption(String optionKey, Object optionValue) throws IOException {
    	FileReader fileread=new FileReader("src/main/resources/options.properties");
    	 Properties properties=new Properties();
    	 properties.load(fileread);
    	 properties.setProperty(optionKey,optionValue.toString());
    	 FileOutputStream fileOut = new FileOutputStream("src/main/resources/options.properties");
    	properties.store(fileOut,null);
       
    }
}
