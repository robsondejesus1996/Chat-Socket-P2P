/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import com.sun.security.ntlm.Server;
import java.net.Socket;
import util.Util;

/**
 *
 * @author Robson de Jesus
 */
public class ListaClientes implements Runnable {
    //responsavel pela lista de clientes que vao estar conectados 

    private String connection_info;
    private Socket connection;
    private Server server;
    private boolean running;

    //receber a minha conexao de informações e a minha referencia do servidor que esta rodando 
    public ListaClientes(String connnection_info, Socket connection, Server server) {
        this.connection_info = connection_info;
        this.connection = connection;
        this.server = server;
        this.running = false;
    }

    public ListaClientes() {
    }
    
    

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    ///  enquando rodar ele vai ficar recebendo mensagens dessa conexao, 
    @Override
    public void run() {
        running = true;
        String message;
        while (running) {
            message = Util.receiveMessage(connection);
            if (message.equals("quit")) {
                running = false;
            } else {
                System.out.println("Recebida: " + message);
            }

        }

    }

}
