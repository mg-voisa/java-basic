
package udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPServer {

    public UDPServer() {
        System.out.println("UDP Server Started");
        try(DatagramSocket serverSocket = new DatagramSocket(9003)){
            while(true){
                byte[] receiveMessage = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
                serverSocket.receive(receivePacket);
                String message = new String(receivePacket.getData());
                System.out.println("Received from client: [" + message + "] \nFrom: " + receivePacket.getAddress());
                InetAddress inetAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendMessage;
                sendMessage = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, inetAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException ex) {        
            System.out.println("Server Handle exceptions!");
        }
        System.out.println("UDP Server Terminating");    
    }
    public static void main(String[] args) {
        new UDPServer();
    }
    
}
