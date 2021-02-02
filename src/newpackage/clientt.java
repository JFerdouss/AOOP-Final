package newpackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class clientt implements Runnable{
     BufferedWriter writer;
     BufferedReader reader;
     ArrayList<clientt>client;
     String name;
     public clientt(Socket socket,ArrayList<clientt>client){
         try {
             
             OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream());
             InputStreamReader isr=new InputStreamReader(socket.getInputStream());

             writer=new BufferedWriter(osw);
             reader=new BufferedReader(isr);
             this.client= client;
         } catch (IOException e) {
             e.printStackTrace();
         }


     }

    @Override
    public void run() {
        try{
            
             String input;
            while((input=reader.readLine())!=null){
                try{
                    if(input.equals("add")){
                         String a=reader.readLine();
                            String b=reader.readLine();
                            String c=reader.readLine();
                            String d=reader.readLine();
                            String e=reader.readLine();
                            String f=reader.readLine();
                            
                            
                    }
                    else if(input.equals("search")){
                        
                    }
                    
                }catch(Exception e){
                   e.printStackTrace(); 
                }
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
      
    }
}