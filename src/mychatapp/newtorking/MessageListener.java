/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mychatapp.newtorking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rumi
 */
public class MessageListener extends Thread{
    
    ServerSocket server;
    int port = 8877;
    
    writableGUI gui;
    
    public MessageListener(writableGUI gui,int port)
    {
        try {
            this.gui = gui;
            this.port = port;
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MessageListener()
    {
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        try {
            Socket clientSocket;
            
            while((clientSocket = server.accept()) != null)
            {
                InputStream is = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                if(line != null)
                {
                    gui.write("Friend: "+line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MessageListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
