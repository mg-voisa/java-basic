
package noduri;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;


public class Nod extends Thread{
    private int id;
    private String hostGroup;
    public Nod(int id, String hostGroup) {
        super("Nod:" + id );
        this.id = id;
        this.hostGroup = hostGroup;
    }
    

    @Override
    public void run() {
        System.out.println("Nod cu id = "+this.id + " in grupul " + hostGroup + " Started!");
        //
        
        System.out.println("Nod  cu id = "+this.id + " in grupul " + hostGroup + " Stopped!");
    }
    
}
