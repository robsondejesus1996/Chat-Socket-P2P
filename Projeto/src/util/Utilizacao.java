package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
Classe utilizada pelo servidor para o envio de mensagens e receber mensagens 
*/
public class Utilizacao {
    
    public static boolean enviarMensagem(Socket sock, String message){     
        try {
            
            //granvado os dados na conexao 
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
            //leitura dos dados da conexao 
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