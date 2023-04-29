/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUITetris;

import Exceptions.FuncionesException;
import Exceptions.RegistroVacioException;
import archivos.Escritor;
import archivos.Lector;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jrqui
 */
public class Registro extends JFrame implements ActionListener {
    
    Font fuente = new Font("Castellar", 3 , 14);
    
    
    private JTextField usuarioRegistro;
    private JTextField nombreRegistro;
    private JPasswordField passwordRegistro;
    
    private JLabel usuarioLabReg;
    private JLabel nombreLabReg;
    private JLabel passwordLabReg;
    
    
    private JButton guardar;
    private JButton salir;
    
    public Registro(){
        
        
        usuarioRegistro = new JTextField();
        nombreRegistro = new JTextField();
        passwordRegistro = new JPasswordField();
        
        usuarioLabReg = new JLabel("Usuario");
        usuarioLabReg.setFont(fuente);
        usuarioLabReg.setForeground(Color.BLACK);
        nombreLabReg = new JLabel("Nombre");
        nombreLabReg.setFont(fuente);
        nombreLabReg.setForeground(Color.BLACK);
        passwordLabReg = new JLabel("Password");
        passwordLabReg.setFont(fuente);
        passwordLabReg.setForeground(Color.BLACK);
        
        guardar = new JButton("Guardar");
        guardar.setBackground(Color.BLACK);
        guardar.setFont(fuente);
        guardar.setForeground(Color.WHITE);
        guardar.setFocusable(false);
        guardar.addActionListener(this);
        guardar.setActionCommand("guardar");
        
        salir = new JButton("Salir");
        salir.setBackground(Color.BLACK);
        salir.setFont(fuente);
        salir.setForeground(Color.WHITE);
        salir.setFocusable(false);
        salir.addActionListener(this);
        salir.setActionCommand("salir");
        
        //Crea la ventana
        this.getContentPane().setLayout(null);
        this.setBounds( 400 , 170 , 620 , 400 );
        this.getContentPane().setBackground(Color.DARK_GRAY);
        
        //Etiquetas para nombrar los textfields
        this.getContentPane().add(usuarioLabReg);
        usuarioLabReg.setBounds(50 , 35 , 80 , 30);
        this.getContentPane().add(nombreLabReg);
        nombreLabReg.setBounds(50 , 90 , 90 , 30);
        this.getContentPane().add(passwordLabReg);
        passwordLabReg.setBounds(50 , 145 , 90 , 30);
        
        //Textfield y ubicaciones
        this.getContentPane().add(usuarioRegistro);
        usuarioRegistro.setBounds(160 , 35 , 260 , 30);
        this.getContentPane().add(nombreRegistro);
        nombreRegistro.setBounds(160 , 90 , 260 , 30);
        this.getContentPane().add(passwordRegistro);
        passwordRegistro.setBounds(160 , 145 , 260 , 30);
        
        //Botones y Ubicaciones
        this.getContentPane().add(guardar);
        guardar.setBounds(300 , 240 , 130 , 40);
        this.getContentPane().add(salir);
        salir.setBounds(150 , 240 , 130 , 40);
  
        
        
        
        this.setTitle("Inicio");
        this.setResizable(true);      
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("salir")){
            GuiInicio inicio = new GuiInicio();  
            this.dispose();
        }
        if(e.getActionCommand().equals("guardar")){
            Escritor escribe = new Escritor();
            try {
                FuncionesException.regVacio(usuarioRegistro.getText(), nombreRegistro.getText(), String.valueOf(passwordRegistro.getPassword()));
                escribe.escribeContenido("src/main/java/archivos/archivo.txt", usuarioRegistro.getText(), String.valueOf(passwordRegistro.getPassword()) , nombreRegistro.getText());
                GuiInicio inicio = new GuiInicio();
                this.dispose();
            
            } catch (RegistroVacioException ex) {
                JOptionPane.showMessageDialog(null, "No ha completado el registro","Registro vacio" , JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
            } 
            
        }
    }
}
