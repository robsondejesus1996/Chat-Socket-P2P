package servidor;

import util.Utilizacao;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;


public class ClientListener implements Runnable {

    private boolean running;
    private Socket socket;
    private String nome;
    private Servidor servidor;


    public ClientListener(String nome, Socket socket, Servidor servidor) {
        this.servidor = servidor;
        running = false; 
        this.socket = socket;
        this.nome = nome;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        running = true;
        String message;
        
        while (running) {
            message = Utilizacao.receberMensagem(socket);
            
            
            if (message.toLowerCase().equals("quit")) {
                servidor.getClientes().remove(nome);
                
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("[ClientListener:Run] -> " + ex.getMessage());
                }
                running = false;
            } else if (message.equals("GET_CONNECTED_USERS")) {
                System.out.println("FOI SOLICITADO A ATUALIZAÇÃO DA LISTA DE CONTATOS...");
                String response = "";
                
                for (Map.Entry<String, ClientListener> pair : servidor.getClientes().entrySet()) {
                    response += (pair.getKey() + ";");
                }
                
                Utilizacao.enviarMensagem(socket, response);
            }
            System.out.println(" >> Mensagem: " + message);
        }
    }

}