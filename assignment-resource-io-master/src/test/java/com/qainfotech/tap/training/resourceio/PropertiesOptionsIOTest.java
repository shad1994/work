package com.qainfotech.tap.training.resourceio;

import java.io.IOException;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 *
 * @author Ramandeep
 */
public class PropertiesOptionsIOTest {
    
    PropertiesOptionsIO testPropertiesOptionsIO;
    
    @BeforeTest
    public void loadOptions(){
        testPropertiesOptionsIO = new PropertiesOptionsIO();
    }
    
    @Test
    public void getOptionValueMethodShouldReturnOptionValuesFromPropertiesFile()
            throws IOException{
        assertThat(Boolean.parseBoolean(testPropertiesOptionsIO
                .getOptionValue("ResourceIOTest").toString()))
                .isTrue();
        
        assertThat(testPropertiesOptionsIO.getOptionValue("TestName"))
                .isEqualTo("PropertiesOptionsIOTest");
    }
    
    @Test
    public void addOptionShouldMethodShouldAppendPropertiesFileWithNewProperty()
            throws IOException{
        /* write to properties */
        String timestamp = String.valueOf(System.currentTimeMillis());
        testPropertiesOptionsIO.addOption("Timestamp", timestamp);
        
        /* assert property appended */
        assertThat(testPropertiesOptionsIO.getOptionValue("Timestamp"))
                .isEqualTo(timestamp);
    }
    
}
