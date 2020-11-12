
package consoleread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConsoleRead {

    
    public static void main(String[] args) {
        //Enter data using BufferReader 
        BufferedReader reader =   new BufferedReader(new InputStreamReader(System.in)); 
        // Reading data using readLine 
        String command = null; 
        try {
            command = reader.readLine();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
  
        // Printing the read line 
        System.out.println(command);       
    }
    
}
