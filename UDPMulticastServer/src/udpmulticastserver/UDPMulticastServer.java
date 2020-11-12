
package udpmulticastserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Date;


public class UDPMulticastServer {
    public int id;
    public String hostGroup;
    public UDPMulticastServer(int id, String hostGroup) {
        this.id = id;
        this.hostGroup = hostGroup;
        System.out.println("Client activ cu id = "+this.id + " in grupul " + hostGroup);
        System.out.println("UDP Multicast Time server started");
        try{
            MulticastSocket multicastSocket = new MulticastSocket();
            InetAddress inetAddress = InetAddress.getByName(hostGroup);
            multicastSocket.joinGroup(inetAddress);
            byte[] data;
            DatagramPacket packet;
            for(int i=0;i<20000;i++){
                Thread.sleep(100);
                String message = (new Date()).toString();
                System.out.println("Sending: [" + message + "]");
                data = message.getBytes();
                packet = new DatagramPacket(data, message.length(), inetAddress, 9877);
                multicastSocket.send(packet);
            }
        }catch(IOException | InterruptedException ex){
            System.out.println("Handler exceptions");
        }
        System.out.println("UDP Multicast Time server terminated");
    }
    
    
    public static void main(String[] args) {
        String[] hosturi = new String[3];
        hosturi[0] = "228.5.6.7";
        hosturi[1] = "228.5.6.8";
        hosturi[2] = "228.5.6.9";
        for(int i=0;i<3;i++){
            new UDPMulticastServer(i,hosturi[i]);
        }
        for(int i=0;i<3;i++){
            new UDPMulticastServer(i,hosturi[3-i]);
        }
        for(int i=0;i<3;i++){
            new UDPMulticastServer(3-i,hosturi[i]);
        }
    }
    
}
