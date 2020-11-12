
package udpechoclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UDPEchoClient {

    
    public static void main(String[] args) {
        System.out.println("UDP Echo Client Started");
        try{
            SocketAddress remote = new InetSocketAddress("127.0.0.1", 9000);
            while(true){
                DatagramChannel channel = DatagramChannel.open();
                channel.connect(remote);
                String message = "The message";
                ByteBuffer buffer = ByteBuffer.allocate(message.length());
                buffer.put(message.getBytes());
                buffer.flip();
                channel.write(buffer);
                System.out.println("Sent: [" + message + "]");
                buffer.clear();
                channel.read(buffer);
                buffer.flip();
                System.out.println("Received: [ ");
                while(buffer.hasRemaining()){
                    System.out.print((char)buffer.get());
                }
                System.out.println(" ]");
                Thread.sleep(3000);
            }
            
        }catch(IOException ex){
            System.out.println("UDP Echo Client Handle exceptions!");
        } catch (InterruptedException ex) {
            Logger.getLogger(UDPEchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("UDP Echo Client Terminated");
    }
    
}
