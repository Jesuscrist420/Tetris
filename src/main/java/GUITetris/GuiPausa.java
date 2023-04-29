/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUITetris;

import JuegoTetris.Logica;
import archivos.Escritor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author jrqui
 */
public class GuiPausa extends JFrame implements ActionListener {
    Font fuente = new Font("Castellar", 3 , 14);
    private GuiJuego game;
    private String[] player;
    private Escritor escribe;
    private Logica logica;
    private JButton reanudar;
    private JButton instrucciones;
    private JButton salir;
    
    
    
    
    public GuiPausa(String[]jugador, GuiJuego juego, Logica log){
        game = juego;
        player = jugador;
        logica = log;
        escribe = new Escritor();
        reanudar = new JButton("Reanudar");
        reanudar.addActionListener(this);
        reanudar.setActionCommand("reanudar");
        reanudar.setFocusable(false);
        reanudar.setFont(fuente);
        reanudar.setForeground(Color.WHITE);
        reanudar.setBackground(Color.BLACK);
        
        instrucciones = new JButton("Instrucciones");
        instrucciones.addActionListener(this);
        instrucciones.setActionCommand("instrucciones");
        instrucciones.setFocusable(false);
        instrucciones.setFont(fuente);
        instrucciones.setForeground(Color.WHITE);
        instrucciones.setBackground(Color.BLACK);
        
        salir = new JButton("Salir");
        salir.addActionListener(this);
        salir.setActionCommand("salir");
        salir.setFocusable(false);
        salir.setFont(fuente);
        salir.setForeground(Color.WHITE);
        salir.setBackground(Color.BLACK);
        
        this.getContentPane().setLayout(null);
        this.setBounds( 400 , 170 , 300 , 300 );
        this.getContentPane().setBackground(Color.DARK_GRAY);
        
        this.getContentPane().add(reanudar);
        reanudar.setBounds(40 , 20 , 120 , 40);
        this.getContentPane().add(instrucciones);
        instrucciones.setBounds(40, 50 , 120 , 40);
        this.getContentPane().add(salir);
        salir.setBounds(40 , 120 , 120 , 40);
        
        this.setTitle("Inicio");
        this.setResizable(true);      
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("reanudar")){
            game.setEnabled(true);
            game.setEstado(1);
            logica.setEstado(2);
            this.dispose();
        }
        if(e.getActionCommand().equals("instrucciones")){
            
        }
        if(e.getActionCommand().equals("salir")){
            try {
                game.dispose();
                logica.perder(player);
                escribe.guardar("src/main/java/archivos/archivo.txt",player);
                GuiInicio inicio = new GuiInicio();
                this.dispose();
            } catch (IOException ex) {
                Logger.getLogger(GuiJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
