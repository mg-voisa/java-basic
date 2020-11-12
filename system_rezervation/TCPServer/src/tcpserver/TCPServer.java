
package tcpserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
//static ServerSocket variable

    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9876;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        //create the socket server object
        server = new ServerSocket(port);
        boolean[] locuri = new boolean[10];
        boolean conectat = false;
        for(int i=0;i<10;i++) locuri[i] = false;
        //keep listens indefinitely until receives 'exit' call or program terminates
        while (true) {
            System.out.println("Waiting for client request.");
            
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            String[] itemsCommand = message.split(":");
            if(itemsCommand.length!=0){
              if(itemsCommand.length==1){
                  //Hello, L, R, Q commands
                  switch(itemsCommand[0]){
                      case "Hello": ;
                      case "hello":{
                            //write object to Socket
                            oos.writeObject("Esti conectat!");
                            conectat = true; break;
                      }
                      case "quit":;
                      case "Quit":;
                      case "Q": {
                            //write object to Socket
                            oos.writeObject("Esti deconectat!");
                            conectat = false; break;
                      }
                      case "all":;
                      case "All":;
                      case "ALL":{
                          if(conectat){
                              String mesaj = "";
                              for(int i=0;i<10;i++){
                                  mesaj += "Locul "+i+" : " + (locuri[i]?"OCUPAT":"LIBER")+ "\n";
                              }
                              //write object to Socket
                              oos.writeObject(mesaj);
                          }else{
                              //write object to Socket
                              oos.writeObject("Nu esti conectat! Trebuie sa te loghezi!");
                          } break;
                      }
                      case "l": ;
                      case "L": {
                          if(conectat){
                              String mesaj = "";
                              for(int i=0;i<10;i++){
                                  mesaj+=((locuri[i] == false)? "Locul " + i + "este LIBER": "");
                              }
                              //write object to Socket
                              oos.writeObject(mesaj);
                          } else{
                              //write object to Socket
                              oos.writeObject("Nu esti conectat! Trebuie sa te loghezi!");
                          } break;
                      }
                      case "r": ;
                      case "R":{
                          if(conectat){
                              String mesaj = "";
                              for(int i=0;i<10;i++){
                                  mesaj+=((locuri[i] == true)? "Locul " + i + "este OCUPAT": "");
                              }
                              //write object to Socket
                              oos.writeObject(mesaj);
                          } else{
                              //write object to Socket
                              oos.writeObject("Nu esti conectat! Trebuie sa te loghezi!");
                          } break;
                      }
                  }
              }
              if(itemsCommand.length == 2){
                  //S:<nr_loc>, R:<nr_loc> 
                  switch(itemsCommand[0]){
                      case "s":;
                      case "S":{
                          if(conectat){
                              if(!itemsCommand[1].isEmpty() && itemsCommand[1].matches("[0-9]")){
                                  int pozitie = Integer.parseInt(itemsCommand[1]);
                                  if(locuri[pozitie]){
                                        //write object to Socket
                                        oos.writeObject("\nLocul acesta este Ocupat!\n");
                                  }
                                  else{
                                        //write object to Socket
                                        oos.writeObject("\nLocul acesta nu este Ocupat!\n");
                                  }
                                  
                              }
                          } else{
                              //write object to Socket
                              oos.writeObject("Nu esti conectat! Trebuie sa te loghezi!");
                          } break;
                      }
                      case "r":;
                      case "R":{
                          if(conectat){
                              if(!itemsCommand[1].isEmpty() && itemsCommand[1].matches("[0-9]")){
                                  int pozitie = Integer.parseInt(itemsCommand[1]);
                                  if(locuri[pozitie]){
                                        //write object to Socket
                                        oos.writeObject("\nLocul acesta este deja rezervat!\n");
                                  }
                                  else{
                                      locuri[pozitie] = true;
                                      //write object to Socket
                                      oos.writeObject("\nREZERVAT!\n");
                                  }
                              }
                          } else{
                              //write object to Socket
                              oos.writeObject("Nu esti conectat! Trebuie sa te loghezi!");
                          } break;
                      }
                  }
              }
            }
            else{
                //write object to Socket
                oos.writeObject("");
            }
            //write object to Socket
            oos.writeObject("Client said: " + message + "\nServer said: Okay!");
            //close resources
            ois.close();
            oos.close();
            socket.close();
            //terminate the server if client sends exit request
            if (message.equalsIgnoreCase("exit")) {
                break;
            }
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
    
}

