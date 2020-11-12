
package tcpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Gigel Voisa
 */
public class TCPServer {

    
    public static void main(String[] args) {
        int port;
        ServerSocket server_socket;
        BufferedReader input;
        try {
            port = Integer.parseInt(args[0]);
        }catch (Exception e) {
            System.out.println("port = 15876 (default)");
            port = 15876;
        }
        try {
            server_socket = new ServerSocket(port);
            System.out.println("Serverul este activ la portul " +
            server_socket.getLocalPort());
            // bucla
            while(true) {
                Socket socket = server_socket.accept();
                System.out.println("Conexiune acceptata " +
                socket.getInetAddress() + ":" + socket.getPort());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //write object to Socket
                InputStream is = socket.getInputStream();
                input = new BufferedReader(new InputStreamReader(is));
                // afiseaza informatiile primite
                try {
                    while(true) {
                        String message = input.readLine();
                        if (message==null) break;
                        if(message.equals("Hello")) oos.writeObject("Client said: " + message + "\n Server said: Okay!");
                        System.out.println(message);
                    }
                }catch (IOException e) {
                System.out.println(e);
                }
                // inchide conexiunea cu clientul
                try {
                    socket.close();
                    System.out.println("Conexiune inchisa de client ");
                }catch (IOException e) {
                    System.out.println(e);
                }
            }
        }catch (IOException e) {
            System.out.println(e);
        }
        }

    }
  
