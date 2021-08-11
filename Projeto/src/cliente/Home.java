/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

/**
 *
 * @author Robson de Jesus
 */
public class Home extends JFrame {

    private String connection_info;

    private JLabel jl_title;
    private JButton jb_get_connected, jb_start_talk;//pegar os usuários conectados e iniciar a conversa
    private JList jList;//lista de usuários que vai estar la dentro
    private JScrollPane scroll;

    public Home(String connection_info) {//inviar uma string de dados que tenha o nome e a porta
        super("Chat UDESC - HOME");
        this.connection_info = connection_info;
        iniciarComponentes();
        configurarComponentes();
        inserirComponentes();
        inserirAcoes();
        iniciar();
    }

    private void iniciarComponentes() {
        jl_title = new JLabel("< Usuário : " + connection_info.split(":")[0] + " >", SwingConstants.CENTER); // configuracao do nome do usuário que esta na tela home, deixar centralizado
        jb_get_connected = new JButton("Atualizar Contatos");
        jb_start_talk = new JButton("Iniciar Conversa");
        jList = new JList();
        scroll = new JScrollPane(jList); // para se por acesso tiver muitos usuários onlines no momento 
      
    }

    private void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(600, 490));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);

        jl_title.setBounds(10, 10, 370, 40);
        jl_title.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jb_get_connected.setBounds(400, 10, 180, 40);
        jb_get_connected.setFocusable(false);
        
        jb_start_talk.setBounds(10, 400, 565, 40);
        jb_start_talk.setFocusable(false);

        jList.setBorder(BorderFactory.createTitledBorder("Usuários online"));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scroll.setBounds(10, 60, 575, 335);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);

    }

    private void inserirComponentes() {
        this.add(jl_title);
        this.add(jb_get_connected);
        this.add(scroll);
        this.add(jb_start_talk);
    }

    private void inserirAcoes() {

    }

    private void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Home home = new Home("Robson:127.0.0.1:4444");
    }

}
