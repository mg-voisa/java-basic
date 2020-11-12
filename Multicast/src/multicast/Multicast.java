
package multicast;

import com.baeldung.networking.udp.EchoClient;
import com.baeldung.networking.udp.EchoServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gigel Voisa
 */
public class Multicast {

    
    public static void main(String[] args) {
        EchoServer echoServer = null;
        try {
            echoServer = new EchoServer();
        } catch (IOException ex) {
            System.out.println("Handler IO exception!");
        }
        echoServer.run();
        
        
        
        
    }
    
}
