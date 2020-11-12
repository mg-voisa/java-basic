
package udpmulticastclient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;


public class UDPMulticastClient {
    private int id;
    private String hostGroup;
    public UDPMulticastClient(int id, String hostGroup) {
        this.id = id;
        this.hostGroup = hostGroup;
        System.out.println("Client activ cu id = "+this.id + " in grupul "+hostGroup);
        System.out.println("UDP Multicast Time client Started");
        try{
            MulticastSocket multicastSocket = new MulticastSocket(9877);
            InetAddress inetAddress = InetAddress.getByName(hostGroup);
            multicastSocket.joinGroup(inetAddress);
            byte[] data = new byte[256];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            for(int i=0;i<20000;i++){
                multicastSocket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Message from: " + packet.getAddress() + "Message: [" + message + "]");
            }
        }catch(IOException ex){
             ex.printStackTrace();
        }
        System.out.println("UDP Multicast Time client Terminated");
    }

    
    public static void main(String[] args) {
        String[] hosturi = new String[3];
        hosturi[0] = "228.5.6.7";
        hosturi[1] = "228.5.6.8";
        hosturi[2] = "228.5.6.9";
        for(int i=0;i<3;i++){
            new UDPMulticastClient(i, hosturi[i]);
        }
        for(int i=0;i<3;i++){
            new UDPMulticastClient(3-i, hosturi[i]);
        }
        for(int i=0;i<3;i++){
            new UDPMulticastClient(i, hosturi[3-i]);
        }
        
    }
    
}
