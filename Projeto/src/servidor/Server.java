/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import util.Util;

/**
 *
 * @author admin
 */
public class Servidor {

    //servidor que vai guardar todos os que então onlines 
    //para testar a aplicação mude o host e a porta com o ip da maquina da pessoa que sera o servidor padrao, replique as duas informações para os host que vão se conectar na aq
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 4444;

    private ServerSocket server;
    
    //lista de cliente 
    private Map<String, ListaClientes> clientes;
    

    public Servidor() {
        //inicializar o servidor

        try {
            String connection_info;
            clientes = new HashMap<String, ListaClientes>();
            server = new ServerSocket(PORT);
            System.out.println("Sucesso na inicialização do servidor, no host: " + HOST + " na porta: " + PORT);

            while (true) {
                Socket connection = server.accept();//aceitar a conexao vindo de alguem 
                connection_info = Util.receiveMessage(connection);
                
                //se eu consegui fazer a conexao, criaçao de um novo cliente e sucesso 
                if(checklogin(connection_info)){
                    ListaClientes cl = new ListaClientes();
                    clientes.put(connection_info, cl);
                    Util.sendMessage(connection,"SUCESSO");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao iniciar servidor" + e.getMessage());
        }

    }
    
    
    //verificar o login se por acesso ele tem o mesmo nome de usuário ou porta
    private boolean checklogin(String connection_info){
        String[] splited = connection_info.split(":");
        for(Map.Entry<String, ListaClientes> pair: clientes.entrySet()){
            String[] parts = pair.getKey().split(":");
            //se os nomes forem iguais, retorna false
            if(parts[0].toLowerCase().equals(splited[0].toLowerCase())){
                return false;
            //verificando se o endereco mais a porta é igual     
            }else if((parts[1] + parts[2]).equals(splited[1] + splited[2])){
                return false;
            }
        }
        //login deu certo
        return true;
        
    }
}
