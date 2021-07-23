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
import java.awt.*;

/**
 *
 * @author Robson de Jesus
 */
public class Home extends JFrame {

    private String conection_info;
    private JLabel titulo;
    private JButton btnUsuariosOnlines, btnIniciarConversa; // botoes para ver usuários conectados e o outro para iniciar a conversa
    private JList lista;// lista de usuários
    private JScrollPane scroll;

    public Home(String conection_info) {
        super("Messenger");
        this.conection_info = conection_info;
        inserirComponentes();
        componentesIniciar();
        configurarComponentes();
        inserirAcoes();
        iniciar();
    }

    private void componentesIniciar() {

    }

    // Robson: 127.0.0.1:3333
    private void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(600, 480));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        titulo = new JLabel(" * Usuário : " + conection_info.split(":")[0] + "*", SwingConstants.CENTER);
        btnUsuariosOnlines = new JButton("Atualizar usuários onlines");
        btnIniciarConversa = new JButton("Iniciar Conversa");
        lista = new JList();
        scroll = new JScrollPane(lista); // coloquei um scroll pois pode ter uma lista muito grante, e pode ser que tenha que rolar para baixo 

        //configuração do título 
        titulo.setBounds(10, 10, 370, 40);
        titulo.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        //configuração do botão conectados 
        btnUsuariosOnlines.setBounds(400, 10, 180, 40);
        btnUsuariosOnlines.setFocusable(false);

        //configuração do do botao iniciar conversa
        btnIniciarConversa.setBounds(10, 400, 575, 40);
        btnIniciarConversa.setFocusable(false);

        //configuração da minha lista de onlines
        lista.setBorder(BorderFactory.createTitledBorder("Usuário conectados"));
        lista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        //configuração do scroll
        scroll.setBounds(10, 60, 575, 335);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);

    }

    private void inserirComponentes() {
        this.add(titulo);
        this.add(btnUsuariosOnlines);
        this.add(btnIniciarConversa);
        this.add(lista);
        this.add(scroll);
    }

    private void inserirAcoes() {

    }

    private void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Home home = new Home("Robson:127.0.1:3333");
    }

//        componentesIniciar();
//        configurarComponentes();
//        inserirComponentes();
//        inserirAcoes();
//        iniciar();
}
