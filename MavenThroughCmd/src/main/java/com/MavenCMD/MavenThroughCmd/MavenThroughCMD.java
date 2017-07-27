package com.MavenCMD.MavenThroughCmd;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Objective: Create a maven project to compile and execute this class from
 * command line. When you run following command from command prompt inside the 
 * maven project the exact amount of rupees will be printed on the output for 
 * the amount of dollars entered:
 *   $> mvn -q clean test -Ddollars=[AMOUNT-OF-DOLLARS]
 * 
 * for example:
 *   $> mvn -q clean test -Ddollars=1 
 *   1USD == 64.5781INR 
 *   $> 
 * 
 * @author Ramandeep
 */
public class MavenThroughCMD {
    
    /**
     * entry method to convert dollar units to rupees.
     * Prints the output on console 
     * 
     * @param args Command line arguments array 
     * @throws IOException 
     */
    public static void main(String... args) throws IOException{
        Document page = Jsoup.connect(
                "http://www.xe.com/currencyconverter/convert/?Amount="
                + args[0]
                + "&From=USD&To=INR"
        ).get();
        
        System.out.println(args[0] 
                + "USD == " 
                + page.select("span.uccResultAmount").text() 
                + "INR"
        );
    }
}
