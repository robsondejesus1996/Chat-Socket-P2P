package servidor;

import util.Utilizacao;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
    
    
    //127.0.0.1,    192.168.1.3    192.168.0.117
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 6666;

    private ServerSocket server;
    private Map<String, ClientListener> clientes;

    public Servidor() {
        try {
            String request;
            clientes = new HashMap<String, ClientListener>();
            server = new ServerSocket(PORT);
            System.out.println("SERVIDOR INICIALIZADO COM SUCESSO..." + HOST);
            while (true) {
                Socket client = server.accept();
                request = Utilizacao.receberMensagem(client);
                if (verificarLogin(request)) {
                    ClientListener listener = new ClientListener(request, client, this);
                    clientes.put(request, listener);
                    Utilizacao.enviarMensagem(client, "SUCESS");
                    new Thread(listener).start();
                }else {
                    Utilizacao.enviarMensagem(client, "ERROR");
                }
            }
        } catch (IOException ex) {
            System.err.println("ERRO AO INICIAR SERVIDOR!" + ex.getMessage());
        }
    }

    

    public Map<String, ClientListener> getClientes() {
        return clientes;
    }

    

    private boolean verificarLogin(String request) {
        String[] splited = request.split(":");
        for (Map.Entry<String, ClientListener> p : clientes.entrySet()) {
            String[] parts = p.getKey().split(":");
            
            if (parts[0].toLowerCase().equals(splited[0].toLowerCase())) {
                return false;
                
            } else if ((parts[1] + parts[2]).toLowerCase().equals((splited[1] + splited[2]).toLowerCase())) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
    }
}