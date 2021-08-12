/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author admin
 */
public class Chat extends JFrame {

    private JLabel jl_title;//com quem esta conversando
    private JEditorPane messages;//mensagens que vai ser enviada
    private JTextField jt_message;
    private JButton jb_message;
    private JPanel panel;
    private JScrollPane scroll;

    //minha lista de mensagem
    private ArrayList<String> message_list;
    //minhas informações de conexão
    private String connection_info;

    public Chat(String connection_info, String title) {//Informações de com quem estou conectado o connection_info é a informação de com quem estou conectando, e o titulo de quem é o chat
        super("Chat " + title);
        this.connection_info = connection_info;
        iniciarComponentes();
        configurarComponentes();
        inserirComponentes();
        inserirAcoes();
        iniciar();
    }

    private void iniciarComponentes() {
        //iniciar minha lista de mensagens vazia
        message_list = new ArrayList<String>();
        jl_title = new JLabel(connection_info.split(":")[0], SwingConstants.CENTER);//configuração com quem estou conversando
        messages = new JEditorPane();
        scroll = new JScrollPane(messages);
        jt_message = new JTextField();
        jb_message = new JButton("Enviar");
        panel = new JPanel(new BorderLayout());

    }

    private void configurarComponentes() {
        this.setMinimumSize(new Dimension(480, 720));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        messages.setContentType("text/html");
        messages.setEditable(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jb_message.setSize(100, 40);
    }

    private void inserirComponentes() {
        this.add(jl_title, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        panel.add(jt_message, BorderLayout.CENTER);
        panel.add(jb_message, BorderLayout.EAST);
    }

    private void inserirAcoes() {
        jb_message.addActionListener(event -> send());
        jt_message.addKeyListener(new KeyListener() {

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
    }

    //para quando receber a mensagem o editor da área de mensagem vai ser atualizada
    public void append_message(String received) {

        message_list.add(received);
        String message = "";
        for (String str : message_list) {
            message += str;
        }
        messages.setText(message);
    }

    //para enviar uma mensagem para alguem 
    private void send() {
        if (jt_message.getText().length() > 0) {
            DateFormat df = new SimpleDateFormat("hh:mm:ss");
            append_message("<br>[" + df.format(new Date()) + "] Eu: </b><i>" + jt_message.getText() + "</i><br>");
            jt_message.setText("");
        }

    }

    private void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Chat chat = new Chat("Robson:127.0.0.1:5555", "fernada");
    }
}
