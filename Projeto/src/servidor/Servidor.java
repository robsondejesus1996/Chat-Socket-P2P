package servidor;

import util.Utilizacao;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
    
    
    //127.0.0.1
    public static final String HOST = "127.0.0.1";//host padrao da maquina que será usado como servidor, logo as outras pessoas tem que colocar o mesmo host aqui 
    public static final int PORT = 4444;

    private ServerSocket server;
    private Map<String, ClientListener> clientes;//lista para acessar os dados do cliente

    public Servidor() {
        try {
            //informações do meu usuário, como se fosse a minha connection_info 
            String request;
            clientes = new HashMap<String, ClientListener>();
            //inicialização do meu servidor
            server = new ServerSocket(PORT);
            System.out.println("SERVIDOR INICIALIZADO COM SUCESSO...");
            //loop infinito para para sempre ficar rodando o servidor
            while (true) {
                //aceitar notas conexoes vinda do server
                Socket client = server.accept();
                request = Utilizacao.receberMensagem(client);
                if (verificarLogin(request)) {
                    //se passar na verificação vai criar um novo cliente no clienteListner passando as informações e retorna sucess
                    ClientListener listener = new ClientListener(request, client, this);
                    clientes.put(request, listener);
                    Utilizacao.enviarMensagem(client, "SUCESS");
                    //inicializar a thread
                    new Thread(listener).start();
                }else {
                    //caso contrario erro
                    Utilizacao.enviarMensagem(client, "ERROR");
                }
            }
        } catch (IOException ex) {
            System.err.println("ERRO AO INICIAR SERVIDOR!" + ex.getMessage());
        }
    }

    
    /*
    Usado na parte do cliente, para mostrar todos os cliente onlines
    */
    public Map<String, ClientListener> getClientes() {
        return clientes;
    }

    
    /*
    verificação de o usuário que esta tentando entrar pode logar no sistema
    Na minha request são os dados que estão vindo da conexao, sendo separados por ':'
    As condições para verificar o acesso são: 1) Não pode deixar entrar com o mesmo nome 2) Não pode deixar entrar usuários que tem uma rede definida com a mesma porta de acesso 
    */
    private boolean verificarLogin(String request) {
        String[] splited = request.split(":");
        for (Map.Entry<String, ClientListener> p : clientes.entrySet()) {
            //separar a minha informação por partes
            String[] parts = p.getKey().split(":");
            
            //verificar se o nome dessa chave que já esta cadastrada é igual a quem eu quero conectar no momento 
            if (parts[0].toLowerCase().equals(splited[0].toLowerCase())) {
                return false;
                
            //verificar se o endereco ip e a porta são iguais    
            } else if ((parts[1] + parts[2]).toLowerCase().equals((splited[1] + splited[2]).toLowerCase())) {
                return false;
            }
        }
        
        //verificação ok se passar pelo nome, ip e porta
        return true;
    }

    public static void main(String[] args) {
        Servidor server = new Servidor();
    }
}