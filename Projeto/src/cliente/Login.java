package cliente;

import util.GUI;
import util.Utils;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import servidor.Server;

public class Login extends GUI {

    private JButton jb_login;
    private JLabel jl_user, jl_port, jl_title;
    private JTextField jt_user, jt_port;

    public Login() {
        super("Login");
    }

    @Override
    protected void initComponents() {
        jb_login = new JButton("Entrar");
        jl_user = new JLabel("Usuario", SwingConstants.CENTER);
        jl_port = new JLabel("Porta", SwingConstants.CENTER);
        jl_title = new JLabel();
        jt_user = new JTextField();
        jt_port =  new JTextField();
    }

    @Override
    protected void configComponents() {
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

    @Override
    protected void insertComponents() {
        this.add(jl_title);
        this.add(jb_login);
        this.add(jl_port);
        this.add(jl_user);
        this.add(jt_port);
        this.add(jt_user);
    }

    @Override
    protected void insertActions() {
        jb_login.addActionListener(event -> {
            Socket connection;
            try {
                String nickname = jt_user.getText();
                int port = Integer.parseInt(jt_port.getText());
                jt_user.setText("");
                jt_port.setText("");
                connection = new Socket(Server.HOST, Server.PORT);
                String request = nickname + ":" + connection.getLocalAddress().getHostAddress() + ":" + port;
                Utils.sendMessage(connection, request);
                if (Utils.receiveMessage(connection).toLowerCase().equals("sucess")) {
                    new Home(connection, request);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Algum usuário já está conectado com este apelido ou tem alguém na mesma rede utilizando a mesma porta que vocÊ.");
                }
            } catch (IOException ex) {
                System.err.println("[ERROR:login] -> " + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao conectar. Verifique se o servidor está em execução.");
            }

        });
    }

    @Override
    protected void start() {
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
    }

}