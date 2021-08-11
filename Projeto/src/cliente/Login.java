/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

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

    private JButton jb_login;
    private JLabel jl_user, jl_port, jl_title;
    private JTextField jt_user, jt_port;

    public Login() {
        super("Login");
        componentesIniciar();
        configurarComponentes();
        inserirComponentes();
        inserirAcoes();
        iniciar();
    }

    private void componentesIniciar() {
        jb_login = new JButton("Entrar");
        jl_user = new JLabel("Usuario", SwingConstants.CENTER);
        jl_port = new JLabel("Porta", SwingConstants.CENTER);
        jl_title = new JLabel();
        jt_user = new JTextField();
        jt_port =  new JTextField();
    }

    private void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(410, 300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        jl_title.setBounds(10, 10, 375, 100);
        ImageIcon icon = new ImageIcon("udesc.png");
        jl_title.setIcon(new ImageIcon(icon.getImage().getScaledInstance(375, 100, Image.SCALE_SMOOTH)));

        jb_login.setBounds(10, 220, 375, 40);

        jl_user.setBounds(10, 120, 100, 40);
        jl_user.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jl_port.setBounds(10, 170, 100, 40);
        jl_port.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        jt_user.setBounds(120, 120, 265, 40);
        jt_port.setBounds(120, 170, 265, 40);
        
       
    }

    private void inserirComponentes() {
        this.add(jl_title);
        this.add(jb_login);
        this.add(jl_port);
        this.add(jl_user);
        this.add(jt_port);
        this.add(jt_user);

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
