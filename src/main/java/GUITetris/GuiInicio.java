/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUITetris;

import JuegoTetris.Jugador;
import archivos.Lector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 *
 * @author Estudiantes
 */
public class GuiInicio extends JFrame implements ActionListener {

    private JTextField usuarioTxt;
    private JPasswordField passTxt;
    Font fuente = new Font("Castellar", 3 , 14);
    
    
    private JLabel usuarioLabel;
    private JLabel passLabel;
    
    private JButton iniciar;
    private JButton registro;
    
  
    
    public GuiInicio(){
        
        iniciar = new JButton(" Iniciar ");
        iniciar.setBackground(Color.BLACK);
        iniciar.setFont(fuente);
        iniciar.setForeground(Color.WHITE);
        iniciar.setFocusable(false);
        iniciar.addActionListener(this);//Escuchar al boton
        iniciar.setActionCommand("iniciar");// Comando del boton
        
        registro = new JButton(" Registro ");
        registro.setBackground(Color.BLACK);
        registro.setFont(fuente);
        registro.setFocusable(false);
        registro.setForeground(Color.WHITE);
        registro.addActionListener(this);
        registro.setActionCommand("registro");
        
        
        usuarioTxt = new JTextField();
        passTxt = new JPasswordField();
        
        usuarioLabel = new JLabel("Usuario");
        usuarioLabel.setForeground(Color.BLACK);
        usuarioLabel.setFont(fuente); 
        
        passLabel = new JLabel("Password");
        passLabel.setForeground(Color.BLACK);
        passLabel.setFont(fuente); 
        
        this.getContentPane().setLayout(null);
        this.setBounds( 400 , 170 , 620 , 400 );
        this.getContentPane().setBackground(Color.DARK_GRAY);
        
        this.getContentPane().add(iniciar);
        iniciar.setBounds( 180 , 280 , 120 , 50 );
        
        this.getContentPane().add(registro);
        registro.setBounds( 320 , 280 , 120 , 50 );
        
        this.getContentPane().add(usuarioTxt);
        usuarioTxt.setFont(fuente);
        usuarioTxt.setBounds(180 , 170 , 260 , 30);
        
        this.getContentPane().add(passTxt);
        passTxt.setBounds( 180 , 225 , 260 , 30 );
        
        
        this.getContentPane().add(usuarioLabel);
        usuarioLabel.setBounds( 100 , 170 , 90 , 30 );
        
        
        this.getContentPane().add(passLabel);
        passLabel.setBounds( 85 , 225 , 90 , 30 );
        
        
        this.setTitle("Inicio");
        this.setResizable(true);      
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals("iniciar")){
            Lector lee = new Lector();
            try{
                int estado = lee.evaluarUsuarios("src/main/java/archivos/archivo.txt", usuarioTxt.getText(), String.valueOf(passTxt.getPassword()));
                if(estado == 1){
                    String[] player = lee.traerUsuario("src/main/java/archivos/archivo.txt", usuarioTxt.getText());
                    GuiJuego game = new GuiJuego(player);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario o password incorrectos.", "Error ", JOptionPane.ERROR_MESSAGE);
                }
            }catch(IOException ex){
                System.out.println(ex);
            }
            
        }
        if(e.getActionCommand().equals("registro")){
            Registro reg = new Registro();
            this.dispose();
                
        }
        
    }
    
}
