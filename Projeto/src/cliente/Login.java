package cliente;

import util.GUI;
import util.Utilizacao;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;
import servidor.Servidor;

public class Login extends GUI {

    private JButton btn_login;
    private JLabel label_usuario, label_porta, label_titulo;
    private JTextField text_usuario, text_porta;

    public Login() {
        super("Login");
    }
    
    
    /*
    iniciar os componetes em tela com os seus respecitivos nomes
    */
    @Override
    protected void inicializarComponentes() {
        btn_login = new JButton("Entrar");
        label_usuario = new JLabel("Cliente", SwingConstants.CENTER);//centralizar texto dos componentes no centro 
        label_porta = new JLabel("Porta de Acesso", SwingConstants.CENTER);
        label_titulo = new JLabel();
        text_usuario = new JTextField();
        text_porta =  new JTextField();
    }

    /*
    espefificação de tamalho de cada componente na tela de login 
    */
    @Override
    protected void configurarComponentes() {
        this.setLayout(null);
        this.setMinimumSize(new Dimension(410, 300));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);

        label_titulo.setBounds(10, 10, 375, 100);
        ImageIcon icon = new ImageIcon("udesc.png");
        label_titulo.setIcon(new ImageIcon(icon.getImage().getScaledInstance(375, 100, Image.SCALE_SMOOTH)));

        btn_login.setBounds(10, 220, 375, 40);

        label_usuario.setBounds(10, 120, 100, 40);
        label_usuario.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        label_porta.setBounds(10, 170, 100, 40);
        label_porta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        text_usuario.setBounds(120, 120, 265, 40);
        text_porta.setBounds(120, 170, 265, 40);
    }

    @Override
    protected void inserirComponenetes() {
        this.add(label_titulo);
        this.add(btn_login);
        this.add(label_porta);
        this.add(label_usuario);
        this.add(text_porta);
        this.add(text_usuario);
    }
    
    /*
    Responsavel pela verificação de acesso de novos usuários fazendo aquela verificação no servidor de (nome, ip, porta)
    */
    @Override
    protected void inserirAcoes() {
        btn_login.addActionListener(event -> {
            Socket connection;
            try {
                String nome = text_usuario.getText();
                int port = Integer.parseInt(text_porta.getText());
                text_usuario.setText("");
                text_porta.setText("");
                //iniciando minha conexao
                connection = new Socket(Servidor.HOST, Servidor.PORT);
                //minha conexao é nome:enderecoIP:porta
                String request = nome + ":" + connection.getLocalAddress().getHostAddress() + ":" + port;
                Utilizacao.enviarMensagem(connection, request);
                if (Utilizacao.receberMensagem(connection).toLowerCase().equals("sucess")) {
                    new Home(connection, request);
                    this.dispose();//fechar a janela atual 
                } else {
                    JOptionPane.showMessageDialog(this, "Erro nome pode estar igual a algum usuário conectado ou a porta de conexão pode estar igual!");
                }
            } catch (IOException ex) {
                System.err.println("ERRO LOGIN" + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Erro ao conectar. Verifique se o servidor está em execução.");
            }

        });
    }

    @Override
    protected void iniciar() {
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
    }

}