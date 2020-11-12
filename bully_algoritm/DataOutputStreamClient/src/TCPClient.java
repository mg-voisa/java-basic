import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public TCPClient() {
    }

    public static void main(String args[]) throws UnknownHostException, IOException {
        InetAddress thisAddress = InetAddress.getLocalHost();
        Socket socket = new Socket(thisAddress.getHostAddress(), 1234);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        boolean stop = false;
        int i=1;
        while (!stop) {
            System.out.println("client->server option send: [ok]");
            output.writeUTF("ok");
            System.out.println("Server said: " + input.readUTF());
            stop=true;
            if(i>5) i=1;
            output.writeUTF(i + "");
            i++;
            System.out.println("client->server option send: [ok]");
//            String response = input.readUTF();
//            System.out.printf("client: got response: %s\n", response);
        }
        socket.close();
    }
}