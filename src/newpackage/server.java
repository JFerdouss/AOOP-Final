package newpackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class server {
    public static void main(String[] args) {
        try {

            ServerSocket server=new ServerSocket(5000);
            System.out.println("Waiting for client");
            ArrayList<clientt>client=new ArrayList<>();
            while(true){
                try {
                    Socket socket = server.accept();
                    System.out.println("connected");
                    clientt h = new clientt(socket, client);
                    client.add(h);
                    Thread t = new Thread(h);
                    t.start();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
}
