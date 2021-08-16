package cliente;

import util.GUI;
import util.Utilizacao;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import servidor.Servidor;

public class Home extends GUI {

    private JLabel titulo;
    private ServerSocket servidor;
    private final Socket connection;
    private final String connection_info;
    private JButton btn_usuarios_conectados, btn_iniciar_conversa;
    private JList lista_usuarios;
    private JScrollPane scroll;

    private ArrayList<String> connected_users;
    private ArrayList<String> opened_chats;//lista para guardar os chats que estão abertos 
    private Map<String, ClientListener> connected_listeners;//guardar a referencia de todos os clienteListener dos chats que estão abertos 

    
    /*
    No construtor temos a comunicação com o servidor principal, logo ele vai estar verificando se  tem como entrar com aquele nome, e porta. Para isso vai ser enviado uma 
    string connection_info que tem os dados de nome, ip e porta. (nome, ip, porta)
    exemplo de conexao: Robson:127.0.0.1:8888
    */
    public Home(Socket connection, String connection_info) {
        super("UDESC CHAT");
        titulo.setText("* Usuário : " + connection_info.split(":")[0] + " *");//espeficicando quem esta conectado, pegando a primeira string, e separando por :
        this.connection = connection;
        this.setTitle("Home - " + connection_info.split(":")[0]);
        this.connection_info = connection_info;
        connected_users = new ArrayList<String>();
        opened_chats = new ArrayList<String>();
        connected_listeners = new HashMap<String, ClientListener>();
        iniciarClienteServidor(this, Integer.parseInt(connection_info.split(":")[2]));
    }

    @Override
    protected void inicializarComponentes() {
        titulo = new JLabel();
        btn_usuarios_conectados = new JButton("Contatos Atualizar");
        lista_usuarios = new JList();
        scroll = new JScrollPane(lista_usuarios);
        btn_iniciar_conversa = new JButton("Iniciar");
    }

    @Override
    protected void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(600, 480));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(10, 10, 370, 40);
        titulo.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        btn_usuarios_conectados.setBounds(400, 10, 180, 40);
        btn_usuarios_conectados.setFocusable(false);

        btn_iniciar_conversa.setBounds(10, 400, 575, 40);
        btn_iniciar_conversa.setFocusable(false);

        lista_usuarios.setBorder(BorderFactory.createTitledBorder("Onlines"));
        lista_usuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// não selecionar mais de um usuário ao mesmo tempo 

        
        /*
        configuração da barra de rolagem, para se caso ter muitos usuários creser mas a barra
        */
        scroll.setBounds(10, 60, 575, 335);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
    }

    @Override
    protected void inserirComponenetes() {
        this.setLocationRelativeTo(null);
        this.add(titulo);
        this.add(btn_usuarios_conectados);
        this.add(scroll);
        this.add(btn_iniciar_conversa);
    }

    @Override
    protected void inserirAcoes() {
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }
            
            /*
            quando fechar a home enviar uma mensagem quit e matar thread
            */
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Conexão encerrada...");
                Utilizacao.enviarMensagem(connection, "QUIT");
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
        
        btn_usuarios_conectados.addActionListener(event -> getConnectedUsers());//inserindo a acao no botao de conectados 
        btn_iniciar_conversa.addActionListener(event -> abrirConversa());//inserindo a ação de iniciar a conversa
    }

    @Override
    protected void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    /*
    vai enviar a mensagem para pegar os usuários conectados e
    */
    private void getConnectedUsers() {
        Utilizacao.enviarMensagem(connection, "GET_CONNECTED_USERS");
        String response = Utilizacao.receberMensagem(connection);
        lista_usuarios.removeAll();
        connected_users.clear();
        
        //para cada string serapara com ;
        for (String user : response.split(";")) {
            //pegando todas as string de conexao do servidor menos aquela que é diferente da nossa propria 
            if (!user.equals(connection_info)) {
                connected_users.add(user);
            }
        }
        lista_usuarios.setListData(connected_users.toArray());
    }
    
    /*
    
    */
    private void abrirConversa() {
        //index que vai receber a minha lista de selecionado
        int index = lista_usuarios.getSelectedIndex();
        
        //se tiver alguem selecionado
        if (index != -1) {
            //pegar quem foi selecionado 
            String value = lista_usuarios.getSelectedValue().toString();
            //pegar esse valor e separar por :
            String[] splited = value.split(":");
            
            
            //se a minha lista de chats abertos não contem as informações da connection_info. Quer dizer que pode abrir a conversa
            if (!opened_chats.contains(value)) {
                try {
                    //nova conexao com o cliente 1 = ip 2 = porta
                    Socket socket = new Socket(splited[1], Integer.parseInt(splited[2]));
                    //enviar uma mensagem para abrir e um ; no final (enviando para a minha conexao com o usuário que eu escolhi que foi aberto com o split da conexao escolhida)
                    //tem a lista de usuários e eu posso escolher um usuário para conversar, quando eu seleciono esse usuário eu pego as informações dele. Dai vou partir as informações dele
                    Utilizacao.enviarMensagem(socket, "OPEN_CHAT;" + connection_info);
                    //vai ser criado um novo clientListner para ficar escutando 
                    //vai abrir o chat com com a connection_info da minha classe 
                    ClientListener cl = new ClientListener(this, socket);
                    cl.setChat(new Chat(this, socket, value, this.connection_info.split(":")[0]));
                    cl.setChatOpen(true);
                    connected_listeners.put(value, cl);
                    opened_chats.add(value);
                    new Thread(cl).start();

                } catch (IOException ex) {
                }
            }
        }
    }
    
    
    /*
    Preparando o cliente para rodar como um servidor
    Vai receber a referencia da minha propria home
    vamos ter uma thread aqui que sempre vai ficar escutando se tem clientes tentando que conectar com cliente
    vai ser criado duas thread uma para ficar escutando conexoes com cliente e a outra funciona quando escutar um novo cliente ele vai criar uma nova e theread e vai ficar escutando um novo cliente que ele liberou  
    */
    private void iniciarClienteServidor(Home home, int porta) {
        new Thread() { // vai ser iniciado um thread e ela vai ficar escutando infinitamente as conexoes que outros clientes vai tentar fazer com ela, ou seja cliente vai ficar conectando com cliente
            @Override
            public void run() {
                try {
                    servidor = new ServerSocket(porta);
                    System.out.println("P2P SERVIDOR DO CLIENTE INICIADO NA PORTA " + porta);
                    while (true) {//loop infinido 
                        //vai ter uma nova conexao, aceitando a conexao 
                        Socket client = servidor.accept();
                        //vai ser criado uma novo clientListener vai ser passado a home e o cliente = conexao 
                        ClientListener cl = new ClientListener(home, client);
                        new Thread(cl).start();//iniciar um nova thread do cliente
                    }
                } catch (IOException ex) {
                    System.err.println("ERRO INICIAR-CLIENTE-SERVIDOR " + ex.getMessage());
                }
            }
        }.start();
    }
    
    public ArrayList<String> getOpened_chats() {
        return opened_chats;
    }

    public String getConnection_info() {
        return connection_info;
    }

    public Map<String, ClientListener> getConnected_listeners() {
        return connected_listeners;
    }
}