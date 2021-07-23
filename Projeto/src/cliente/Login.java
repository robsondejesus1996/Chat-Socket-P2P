/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import sun.swing.SwingAccessor;

/**
 *
 * @author Robson de Jesus
 */
public class Login extends JFrame {

    private JButton botaoLogin;
    private JLabel usuarioLabel, portaLabel, tituloLabel;
    private JTextField usuarioTexto, portaTexto;

    public Login() {
        super("Login");
        componentesIniciar();
        configurarComponentes();
        inserirComponentes();
        inserirAcoes();
        iniciar();
    }

    private void componentesIniciar() {
        botaoLogin = new JButton("Acessar");
        usuarioLabel = new JLabel("Nome usuário", SwingConstants.CENTER);
        portaLabel = new JLabel("Porta de Acesso", SwingConstants.CENTER);
        tituloLabel = new JLabel();
        usuarioTexto = new JTextField();
        portaTexto = new JTextField();
    }

    private void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(400,300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        
        tituloLabel.setBounds(10, 10, 375, 100);
        //ImageIcon icon = new ImageIcon("logo.png");
        //tituloLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(375, 100, Image.SCALE_SMOOTH)));
        
        botaoLogin.setBounds(10, 220,375, 40);
        
        //configuração jlabel usuario
        usuarioLabel.setBounds(10, 120, 100, 40);
        usuarioLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        //configuração jlabel porta
        portaLabel.setBounds(10, 170, 100, 40);
        portaLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        //configuração input usuário 
        usuarioTexto.setBounds(120, 120, 265, 40);
        
        //configuração input da porta
        portaTexto.setBounds(120, 170, 265, 40);
       
    }

    private void inserirComponentes() {
       this.add(botaoLogin);
       this.add(usuarioLabel);
       this.add(portaLabel);
       this.add(tituloLabel);
       this.add(usuarioTexto);
       this.add(portaTexto);

    }

    private void inserirAcoes() {
        
    }

    private void iniciar() {
        this.pack();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Login login = new Login();
    }

}
