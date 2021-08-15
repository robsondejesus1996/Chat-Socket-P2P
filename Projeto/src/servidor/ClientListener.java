package servidor;

import util.Utilizacao;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.Servidor;

/*
Classe responsável pela escuta de novos clientes
*/
public class ClientListener implements Runnable {

    private boolean running;//rodar numa thread
    private Socket socket;
    private String nome;
    private Servidor servidor;

    /*
    Informações para a conexao (conexao, referencia da sorvidor)
    */
    public ClientListener(String nome, Socket socket, Servidor servidor) {
        this.servidor = servidor;
        running = false; // inicialmente a thread não vai estar rodando 
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
        running = true;// a thread vai comecar a rodar
        String message;
        
        while (running) {// vai ficar recebendo mensagem dessa conexao 
            message = Utilizacao.receberMensagem(socket);
            
            if (message.toLowerCase().equals("quit")) {
                servidor.getClientes().remove(nome);
                
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.err.println("[ClientListener:Run] -> " + ex.getMessage());
                }
                running = false;
                //se abertar o botao vai disporar essa solicitação
            } else if (message.equals("GET_CONNECTED_USERS")) {
                System.out.println("FOI SOLICITADO A ATUALIZAÇÃO DA LISTA DE CONTATOS...");
                String response = "";
                
                //minha resposta é a chave de informação que vai ser concatenado com ';'
                for (Map.Entry<String, ClientListener> pair : servidor.getClientes().entrySet()) {
                    response += (pair.getKey() + ";");
                }
                
                Utilizacao.enviarMensagem(socket, response);
            }
            System.out.println(" >> Mensagem: " + message);
        }
    }

}