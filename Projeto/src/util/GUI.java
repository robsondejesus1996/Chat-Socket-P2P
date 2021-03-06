package util;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

public abstract class GUI extends JFrame{
    public GUI(String title){
        super(title);
        inicializarComponentes();
        configurarComponentes();
        inserirComponenetes();
        inserirAcoes();
        iniciar();
    }
    
    protected abstract void inicializarComponentes();
    protected abstract void configurarComponentes();
    protected abstract void inserirComponenetes();
    protected abstract void inserirAcoes();
    protected abstract void iniciar();
}