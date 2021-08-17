package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Utilizacao {
    
    public static boolean enviarMensagem(Socket sock, String message){     
        try {
            
            ObjectOutputStream output = new ObjectOutputStream(sock.getOutputStream());
            output.flush();
            output.writeObject(message);
            return true;
        } catch (IOException ex) {
            System.err.println("[ERROR:ENVIAR-MENSAGEM] " + ex.getMessage());
        }
        return false;
    }
    
    public static String receberMensagem(Socket sock){
        String response = null;
        
        try {
            ObjectInputStream input = new ObjectInputStream(sock.getInputStream());
            response = (String) input.readObject();
        } catch (IOException ex) {
            System.err.println("[ERROR:RECEBER-MENSAGEM] " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("[ERROR:RECEBER-MENSAGEM] " + ex.getMessage());
        }
         return response;
    }
}