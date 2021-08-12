/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Robson de Jesus
 */
public class Util {

    public static boolean sendMessage(Socket connection, String message) {

        try {
            ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            output.writeObject(message);
            return true;
        } catch (IOException e) {
            System.out.println("[Erro:sendMessage] -> " + e.getMessage());
        }

        return false;
    }

    //esperar a resposta
    public static String receiveMessage(Socket connection) {
        String response = null;

        try {
            //ler daddos da conexao
            ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
            response = (String) input.readObject();
        } catch (IOException e) {
            System.out.println("[Erro:sendMessage] -> " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Erro:sendMessage] -> " + e.getMessage());
        }
        return response;
    }
}
