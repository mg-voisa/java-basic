
package udp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDP {

    
    public static void main(String[] args) {
        try {
            EchoServer echoserver = new EchoServer();
            echoserver.run();
        } catch (IOException ex) {
            Logger.getLogger(UDP.class.getName()).log(Level.SEVERE, null, ex);
        }
        EchoClient echoclient = new EchoClient();
        echoclient.sendEcho("hello");
        echoclient.close();
    }
    
}
