package cliente;

import util.Utilizacao;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
Classe responsável por escutar outros clientes
Usado como um objeto para a thread, toda a vez que receber uma conexao do nosso cliente vai ser ativado o clientListner que vai ficar escutando a conversa com aquele cliente.
E toda a vez que enviar mensagem ele vai atualizar o chat
A classe tem uma representação de um para um, ela representa a escuta entre um cliente e outro cliente. Ou seja cada conversa tem um clientListener rodando 
 */
public class ClientListener implements Runnable {

    private boolean running;//variavel para guardar se esta rodando ou não a nossa thread
    private Socket socket;
    private Home home;
    private boolean chatOpen;//vai guardar se o chat esta aberto ou nao, se o chat esta aberto = true se esta fechado = false
    private String connection_info;
    private Chat chat;

    /*
    É necessário a referencia com a pagina home e também a conexao de origem 
    */
    public ClientListener(Home home, Socket socket) {
        chatOpen = false;
        this.home = home;
        running = false;
        this.socket = socket;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isChatOpen() {
        return chatOpen;
    }

    public void setChatOpen(boolean chatOpen) {
        this.chatOpen = chatOpen;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    /*
    
    */
    @Override
    public void run() {
        running = true;
        String message;
        
        //Enquando a thread estiver rodando vai receber a mensagem 
        while (running) {
            message = Utilizacao.receberMensagem(socket);
            
            //se a mensagem for nula, e a mensagem chat _close 
            if (message == null || message.equals("CHAT_CLOSE")) {
                //se o chat estiver aberto 
                if (chatOpen) {
                    //vai remover ele da lista de chats abertos
                    home.getOpened_chats().remove(connection_info);
                    //tb vou remover ele da lista de escutas do clientListner
                    home.getConnected_listeners().remove(connection_info);
                    chatOpen = false;//chat não vai estar mas aberto
                    try {
                        socket.close();
                    } catch (IOException ex) {
                        System.err.println("ERRO CLIENTLISTNER RUN " + ex.getMessage());
                    }
                    chat.dispose();//fechar o chat 
                }
                running = false;//matar a thread
                
            //pode ser que foi enviado outra coisa     
            /*
            Vai ter o comando e para quem vai e pra quem vai ser esse comando. Ex abrir conversa com quem
            Esta sendo separado com ';' os campos: (comando, pra quem)
            */
            } else {
                //definição dos meus campos, separando pro ';'
                String[] fields = message.split(";");
                 
                //casso fields seja maior que 1, deve um comando e pra quem 
                if (fields.length > 1) {
                    //se na posicao 0 for open 
                    if (fields[0].equals("OPEN_CHAT")) {
                        //separando e atribuindo os ':' coonection_info vai ser igual a compo 1 inteiro  
                        String[] splited = fields[1].split(":");
                        connection_info = fields[1];
                        //se eu recebe o commando para abrir o chat vou abrir o chat e ele não pode estar aberto 
                        if (!chatOpen) {
                            //adicionar a minha string de informação nos chats abertos
                            home.getOpened_chats().add(connection_info);
                            //colocando minha lista de escutas na minha string de informações
                            home.getConnected_listeners().put(connection_info, this);
                            chatOpen = true;//definir ele como aberto
                            //criando um novo chat com as informações da conexao  
                            chat = new Chat(home, socket, connection_info, home.getConnection_info().split(":")[0]);
                        }
                    } else if (fields[0].equals("MESSAGE")) {
                        chat.append_message(fields[1]);
                    }
                }
            }
            System.out.println(" >> Mensagem: " + message);
        }
    }
}