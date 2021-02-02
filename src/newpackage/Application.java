package newpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application extends JFrame {
    BufferedWriter writer;
    BufferedReader reader;

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JLabel label1 = new JLabel("Account No.");
    JLabel label2 = new JLabel("User ID");
    JLabel label3 = new JLabel("User Name");
    JLabel label4 = new JLabel("Mobile No.");
    JLabel label5 = new JLabel("Email Address");
    JLabel label6 = new JLabel("Date of Birth");
    JLabel label7 = new JLabel("User Id");
    JTextField field1 = new JTextField(19);
    JTextField field2 = new JTextField(19);
    JTextField field3 = new JTextField(19);
    JTextField field4 = new JTextField(19);
    JTextField field5 = new JTextField(19);
    JTextField field6 = new JTextField(19);
    JTextField field7 = new JTextField(25);
   // JTextField field8 = new JTextField(37);
    JButton button1 = new JButton("Search");
    JButton button2 = new JButton("ADD");
    //JButton button3 = new JButton("Logout");

    Application() {
        setTitle("Banking System");
        setSize(450, 430);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        panel1.setLayout(new GridLayout(6,2));
        panel1.setPreferredSize(new Dimension(420, 200));
        panel1.setBorder(BorderFactory.createTitledBorder("Employee Account Details"));
        panel2.setPreferredSize(new Dimension(420, 95));
        panel2.setBorder(BorderFactory.createTitledBorder("Search for one"));

        field1.setEditable(false);
        field2.setEditable(false);
        field3.setEditable(false);
        field4.setEditable(false);
        field5.setEditable(false);
        field6.setEditable(false);
       // field8.setEditable(false);




        panel1.add(label1);
        panel1.add(field1);
        panel1.add(label2);
        panel1.add(field2);
        panel1.add(label3);
        panel1.add(field3);
        panel1.add(label4);
        panel1.add(field4);
        panel1.add(label5);
        panel1.add(field5);
        panel1.add(label6);
        panel1.add(field6);
        panel2.add(label7);
        panel2.add(field7);
        panel2.add(button1);
       // panel2.add(button2);
       // panel3.add(button3);
        panel4.add(button2);
        add(panel1);
        add(panel2);
        add(panel4);
        add(panel3);
        setVisible(true);
        
        
        try {
            Socket socket=new Socket("127.0.0.1",5000);
            OutputStreamWriter osw=new OutputStreamWriter(socket.getOutputStream());
            writer=new BufferedWriter(osw);
            InputStreamReader isr=new InputStreamReader(socket.getInputStream());
            reader=new BufferedReader(isr);
             
       
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        
        button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                            String a=field1.getText();
                            String b=field2.getText();
                            String c=field3.getText();
                            String d=field4.getText();
                            String e=field5.getText();
                            String f=field6.getText();
                            
                            try { 
                                writer.write("add"+"\n");
                                writer.write(a+"\n");
                                writer.write(b+"\n");
                                writer.write(c+"\n");
                                writer.write(d+"\n");
                                writer.write(e+"\n");
                                writer.write(f+"\n");
                                
                                writer.flush();
                            } catch (IOException ex) {
                                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                            }
                         
                        }
                    });
        button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent ae) {
                          String a=field7.getText();
                            try {
                                 writer.write("search"+"\n");
                                writer.write(a+"\n");
                                writer.flush();
                                
                             
                                String output;
                                while((output=reader.readLine())!=null){
                                    try{
                                        if(output.equals("found")){
                                            String b=reader.readLine();
                                            String c=reader.readLine();
                                            String d=reader.readLine();
                                            String e=reader.readLine();
                                            String f=reader.readLine();
                                            
                                            field2.setText(a);
                                            
                                            field1.setText(b);
                                            field3.setText(c);
                                            field4.setText(d);
                                            field5.setText(e);
                                            field6.setText(f);
                                
                                            
                                        }
                                        else{
                                            field7.setText("Employee not found");
                                        }
                                    }catch(Exception e){
                                        e.printStackTrace();
                                    }
                                }
                                
                            } catch (IOException ex) {
                                Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
                            }
                          
                        }
                    });
    }
    public static void main(String[] args) {
        Application a=new Application();
    }
}