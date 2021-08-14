package cliente;

import util.GUI;
import util.Utilizacao;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Chat extends GUI {

    private JLabel titulo_dono;
    private JEditorPane mensagens;
    private JTextField enviar_mensagem;
    private JButton btn_mensagem;
    private JPanel panel;
    private JScrollPane scroll;

    private ArrayList<String> lista_mensagens;
    private Home home;
    private Socket connection;
    private String connection_info;

    /*
    Recebendo uma string de informaçoes, com quem eu estou me conectando, e o titulo é de quem é esse chat o dono 
    */
    public Chat(Home home, Socket connection, String connection_info, String title) {
        super("UDESC - Chat " + title);
        this.home = home;
        this.connection_info = connection_info;
        lista_mensagens = new ArrayList<String>();
        this.connection = connection;
        this.titulo_dono.setText(connection_info.split(":")[0]);
        this.titulo_dono.setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void inicializarComponentes() {
        titulo_dono = new JLabel();
        mensagens = new JEditorPane();
        scroll = new JScrollPane(mensagens);
        enviar_mensagem = new JTextField();
        btn_mensagem = new JButton("Enviar");
        panel = new JPanel(new BorderLayout());
    }

    @Override
    protected void configurarComponentes() {
        this.setMinimumSize(new Dimension(480, 720));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// pegar apenas o chat, assim não fecha toda a aplicação 
        mensagens.setContentType("text/html");//configuração para a mensagem ficar mais bonita no formato html 
        mensagens.setEditable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        btn_mensagem.setSize(100, 40);
    }

    @Override
    protected void inserirComponenetes() {
        this.add(titulo_dono, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        panel.add(enviar_mensagem, BorderLayout.CENTER);
        panel.add(btn_mensagem, BorderLayout.EAST);
    }

    
    /*
    Ações que vão ser executadas na tela
    quando enviar uma mensagem: ketPressed enviar mensagem 
    quando precionar o enter: enviar mensagem
    */
    @Override
    protected void inserirAcoes() {
        enviar_mensagem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    send();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        btn_mensagem.addActionListener(event -> send());
        
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Utilizacao.enviarMensagem(connection, "CHAT_CLOSE");
                home.getOpened_chats().remove(connection_info);
                home.getConnected_listeners().get(connection_info).setChatOpen(false);
                home.getConnected_listeners().get(connection_info).setRunning(false);
                home.getConnected_listeners().remove(connection_info);
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
    }

    /*
    Para quando receber a mensagem na área do editor ser atualizado setando nada no campo "" quando enviar e receber 
    */
    public void append_message(String received) {
        lista_mensagens.add(received);
        String message = "";
        //para cada string de str dentro de message, colocar message += str
        for (String str : lista_mensagens) {
            message += str;
        }
        
        mensagens.setText(message);
    }

    @Override
    protected void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    
    /*
    Quando for enviar uma mensagem para alguem 
    Com o formato de horas, minutos e segundos. 
    */
    private void send() {
        DateFormat formato = new SimpleDateFormat("hh:mm:ss");
        lista_mensagens.add("<b>[" + formato.format(new Date()) + "] Eu: </b><i>" + enviar_mensagem.getText() + "</i><br>");
        Utilizacao.enviarMensagem(connection, "MESSAGE;<b>[" + formato.format(new Date()) + "] " + home.getConnection_info().split(":")[0] + ": </b><i>" + enviar_mensagem.getText() + "</i><br>");
        String message = "";
        
        for (String str : lista_mensagens) {
            message += str;
        }
        
        mensagens.setText(message);
        enviar_mensagem.setText("");
    }
}