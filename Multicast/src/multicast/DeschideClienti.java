
package multicast;

import com.baeldung.networking.udp.EchoClient;


public class DeschideClienti {
    public static void main(String[] args) {
        //deschide clientii
        int contor = 0;
        for(int i=0;i<10;i++){
//            try {
                EchoClient echoClient = new EchoClient(contor++);
                System.out.println("Clientul "+ echoClient.getId() + " a trimis un mesaj!" );
                String mesajPrimit = echoClient.sendEcho("hello");
                System.out.println("S-a primit catre server: [" + mesajPrimit+"]");
                echoClient.close();
//                Thread.sleep(3000);
//            } catch (InterruptedException ex) {
//                System.out.println("Intrerupere Exceptie prinsa!");
//            }
        }
    }
}
