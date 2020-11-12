package com.baeldung.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;
    private int id;
    public EchoClient(int id) {
        this.id = id;
        System.out.println("S-a pornit o cerere de la un client!");
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public int getId(){
        return id;
    }
    public String sendEcho(String msg) {
        msg+=":"+this.id+"";
        DatagramPacket packet = null;
        try {
            System.out.println("Se trimite catre server. Mesajul = ["+msg+"]");
            buf = msg.getBytes();
            packet = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}
