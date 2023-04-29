/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUITetris;

import JuegoTetris.Jugador;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import JuegoTetris.Logica;
import archivos.Escritor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author jrqui
 */
public class GuiJuego extends JFrame implements ActionListener, KeyListener{
    private Logica game;
    private String[] player;
    private Escritor escribe;
    Font fuente = new Font("Castellar", 3 , 14);
    private JLabel[][] matrizLabel = new JLabel[20][10];
    private JLabel[][] matrizLabel2 = new JLabel[6][6];
    
    
    private JPanel matrizPan;
    private JPanel matrizPan2;
    
    private JLabel nombre1;
    private JLabel puntaje1;
    private JLabel juegosJu1;
    private JLabel juegosPer1;
    private JLabel juegosGa1;
    private JLabel ultimoPuntaje1;
    private JLabel nivel1;
    
    private JLabel nombre;
    private JLabel puntaje;
    private JLabel juegosJu;
    private JLabel juegosPer;
    private JLabel juegosGa;
    private JLabel ultimoPuntaje;
    private JLabel nivel;
    
    private JButton jugar;
    private JButton pausa;
    
    
    private Timer timer;
    private TimerTask task;
    
    private int estado;
    private int tamX;
    private int tamX2 = 30;
    private int tamY;
    private int tamY2 = 30;
    private int posInX = 0;
    private int posInX2 = 0;
    private int posInY = 0;
    private int posInY2 = 0;
    Border raisedbevel = BorderFactory.createBevelBorder(1, Color.DARK_GRAY , Color.BLACK);
    
    public void tamañoLabel(int cantX, int cantY){
        tamX = 300/cantX;
        tamY = 600/cantY;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public void crearMatriz(){
        for(int i = 0; i<20;i++){
            for(int j = 0; j<10; j++){
                JLabel lblNuevo = new JLabel("1");
                matrizLabel[i][j] = lblNuevo;
                matrizLabel[i][j].setFocusable(true);
                matrizPan.add(matrizLabel[i][j]);
                matrizLabel[i][j].setBackground(Color.BLACK);
                matrizLabel[i][j].setForeground(Color.WHITE);
                matrizLabel[i][j].setOpaque(true);
                matrizLabel[i][j].setBounds(posInX , posInY , tamX , tamY );
                matrizLabel[i][j].setBorder(raisedbevel);
                posInX += tamX;
            }
            posInX = 0;
            posInY+=tamY;
        }
        for(int i = 0; i<6;i++){
            for(int j = 0; j<6;j++){
                JLabel lblNew = new JLabel("1");
                matrizLabel2[i][j] = lblNew;
                matrizLabel2[i][j].setFocusable(true);
                matrizPan2.add(matrizLabel2[i][j]);
                matrizLabel2[i][j].setBackground(Color.BLACK);
                matrizLabel2[i][j].setForeground(Color.WHITE);
                matrizLabel2[i][j].setOpaque(true);
                matrizLabel2[i][j].setBounds(posInX2 ,posInY2, tamX2, tamY2);
                matrizLabel2[i][j].setBorder(raisedbevel);
                posInX2 += tamX2;
            }
            posInX2 = 0;
            posInY2+= tamY2;
        }
        
    }
    
    public void igualarMatrices(){
        for(int i = 0; i<20;i++){
            for(int j = 0; j<10; j++){
                matrizLabel[i][j].setText(String.valueOf(game.matriz[i][j]));  
            }
        }
        for(int i = 0; i<6;i++){
            for(int j = 0; j<6; j++){
                matrizLabel2[i][j].setText(String.valueOf(game.matrizSig[i][j]));  
            }
        }
       
    }
    public void pintarMatriz(JLabel[][] matriz){
        for(int i = 0; i<matriz.length;i++){
            for(int j = 0; j<matriz[i].length; j++){
                if(matriz[i][j].getText().equals("0")){
                    matriz[i][j].setForeground(Color.BLACK);
                    matriz[i][j].setBackground(Color.BLACK);
                }
                if(matriz[i][j].getText().equals("1") || matriz[i][j].getText().equals("11")){
                    matriz[i][j].setForeground(Color.YELLOW);
                    matriz[i][j].setBackground(Color.YELLOW);
                }
                if(matriz[i][j].getText().equals("2") || matriz[i][j].getText().equals("12")){
                    matriz[i][j].setForeground(Color.CYAN);
                    matriz[i][j].setBackground(Color.CYAN);
                }
                if(matriz[i][j].getText().equals("3") || matriz[i][j].getText().equals("13")){
                    matriz[i][j].setForeground(Color.RED);
                    matriz[i][j].setBackground(Color.RED);
                }
                if(matriz[i][j].getText().equals("4") || matriz[i][j].getText().equals("14")){
                    matriz[i][j].setForeground(Color.GREEN);
                    matriz[i][j].setBackground(Color.GREEN);
                }
                if(matriz[i][j].getText().equals("5") || matriz[i][j].getText().equals("15")){
                    matriz[i][j].setForeground(Color.ORANGE);
                    matriz[i][j].setBackground(Color.ORANGE);
                }
                if(matriz[i][j].getText().equals("6") || matriz[i][j].getText().equals("16")){
                    matriz[i][j].setForeground(Color.PINK);
                    matriz[i][j].setBackground(Color.PINK);
                }
                if(matriz[i][j].getText().equals("7") || matriz[i][j].getText().equals("17")){
                    matriz[i][j].setForeground(Color.MAGENTA);
                    matriz[i][j].setBackground(Color.MAGENTA);
                }
            }
        }
    }
    public void mostrarResultado( Logica juego, String[] jugador) throws IOException{
        if(juego.getEstado()==3){
            JOptionPane.showMessageDialog(null, "Has Perdido" , "Perdedor" , JOptionPane.ERROR_MESSAGE);
            juego.setEstado(0);
            escribe.guardar("src/main/java/archivos/archivo.txt",jugador);
            this.dispose();
            GuiJuego gui = new GuiJuego(jugador);
        }else{
            if(juego.getEstado()==4){
                JOptionPane.showMessageDialog(null, "Has Ganado" , "Ganador" , JOptionPane.INFORMATION_MESSAGE);
                juego.setEstado(0);
                escribe.guardar("src/main/java/archivos/archivo.txt",jugador);
                this.dispose();
                GuiJuego gui = new GuiJuego(jugador);
                
            }
        }
    }
    //Actualiza los datos en la pantalla para saber el puntaje y demas.
    public void actualizarDatos(String[]jugador){
        puntaje.setText(jugador[3]);
        juegosJu.setText(jugador[4]);
        juegosPer.setText(jugador[6]);
        juegosGa.setText(jugador[5]);
        ultimoPuntaje.setText(jugador[7]);
        nivel.setText(jugador[8]);
    }
    public GuiJuego(String[] jugador){
        escribe = new Escritor();
        player = jugador;
        game = new Logica(player);
        matrizPan = new JPanel();
        matrizPan2 = new JPanel();
        estado = 0;
        
        jugar = new JButton("Jugar");
        jugar.addActionListener(this);
        jugar.setActionCommand("jugar");
        jugar.setFocusable(false);
        jugar.setFont(fuente);
        jugar.setForeground(Color.WHITE);
        jugar.setBackground(Color.BLACK);
        
        pausa = new JButton("Pausa");
        pausa.addActionListener(this);
        pausa.setEnabled(false);
        pausa.setActionCommand("pausa");
        pausa.setFocusable(false);
        pausa.setFont(fuente);
        pausa.setForeground(Color.WHITE);
        pausa.setBackground(Color.BLACK);
        
        nombre1 = new JLabel("Nombre :");
        nombre1.setFont(fuente);
        nombre1.setForeground(Color.CYAN);
        puntaje1 = new JLabel("Puntaje:");
        puntaje1.setFont(fuente);
        puntaje1.setForeground(Color.blue);
        juegosJu1 = new JLabel("Juegos jugados:");
        juegosJu1.setFont(fuente);
        juegosJu1.setForeground(Color.MAGENTA);
        juegosPer1 = new JLabel("Juegos perdidos:");
        juegosPer1.setFont(fuente);
        juegosPer1.setForeground(Color.RED);
        juegosGa1 = new JLabel("Juegos ganados:");
        juegosGa1.setFont(fuente);
        juegosGa1.setForeground(Color.GREEN);
        ultimoPuntaje1 = new JLabel("Ultimo puntaje:");
        ultimoPuntaje1.setFont(fuente);
        ultimoPuntaje1.setForeground(Color.YELLOW);
        nivel1= new JLabel("Nivel:");
        nivel1.setFont(fuente);
        nivel1.setForeground(Color.BLACK);
        
        
        nombre = new JLabel(player[2]);
        nombre.setFont(fuente);
        nombre.setForeground(Color.WHITE);
        puntaje = new JLabel(player[3]);
        puntaje.setFont(fuente);
        puntaje.setForeground(Color.WHITE);
        juegosJu = new JLabel(player[4]);
        juegosJu.setFont(fuente);
        juegosJu.setForeground(Color.WHITE);
        juegosGa = new JLabel(player[5]);
        juegosGa.setFont(fuente);
        juegosGa.setForeground(Color.WHITE);
        juegosPer = new JLabel(player[6]);
        juegosPer.setFont(fuente);
        juegosPer.setForeground(Color.WHITE);
        ultimoPuntaje = new JLabel(player[7]);
        ultimoPuntaje.setFont(fuente);
        ultimoPuntaje.setForeground(Color.WHITE);
        nivel = new JLabel("1");
        nivel.setFont(fuente);
        nivel.setForeground(Color.WHITE);
        
        
        
        tamañoLabel(10,20);
        
        this.getContentPane().setLayout(null);
        this.setBounds( 50 , 50 , 900 , 700 );
        this.setFocusable(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.addKeyListener(this);
        
        this.getContentPane().add(nombre1);
        nombre1.setBounds( 10 , 10 , 200 , 60 );
        this.getContentPane().add(puntaje1);
        puntaje1.setBounds( 10 , 80 , 200 , 60 );
        this.getContentPane().add(juegosJu1);
        juegosJu1.setBounds( 10 , 150 , 200 , 60 );
        this.getContentPane().add(juegosPer1);
        juegosPer1.setBounds( 10 , 220 , 200 , 60 );
        this.getContentPane().add(juegosGa1);
        juegosGa1.setBounds( 10 , 290 , 200 , 60 );
        this.getContentPane().add(ultimoPuntaje1);
        ultimoPuntaje1.setBounds( 10 , 360 , 200, 60 );
        this.getContentPane().add(nivel1);
        nivel1.setBounds( 10 , 430 , 120 , 60 );
        
        this.getContentPane().add(nombre);
        nombre.setBounds( 10 , 30 , 200 , 60 );
        this.getContentPane().add(puntaje);
        puntaje.setBounds( 10 , 100 , 100 , 60 );
        this.getContentPane().add(juegosJu);
        juegosJu.setBounds( 10 , 170 , 100 , 60 );
        this.getContentPane().add(juegosPer);
        juegosPer.setBounds( 10 , 240 , 100 , 60 );
        this.getContentPane().add(juegosGa);
        juegosGa.setBounds( 10 , 310 , 100 , 60 );
        this.getContentPane().add(ultimoPuntaje);
        ultimoPuntaje.setBounds( 10 , 380 , 100, 60 );
        this.getContentPane().add(nivel);
        nivel.setBounds( 10 , 450 , 120 , 60 );
        
        
        
        
        
        this.getContentPane().add(matrizPan);
        matrizPan.setLayout(null);
        matrizPan.setBorder(raisedbevel);
        matrizPan.setBounds( 240 , 30 , 300 , 600 );
        matrizPan.setVisible(true);
        
        this.getContentPane().add(matrizPan2);
        matrizPan2.setLayout(null);
        matrizPan2.setBorder(raisedbevel);
        matrizPan2.setBounds(590 , 30 , 180 , 180);
        matrizPan2.setVisible(true);
        
        this.getContentPane().add(jugar);
        jugar.setBounds( 600 , 450 , 90 , 50 );
        this.getContentPane().add(pausa);
        pausa.setBounds( 600 , 550 , 90 , 50 );
        
        
       
        
        crearMatriz();
        igualarMatrices();
        pintarMatriz(matrizLabel);
        pintarMatriz(matrizLabel2);
        
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                if(estado == 1 ){
                    igualarMatrices();
                    pintarMatriz(matrizLabel);
                    pintarMatriz(matrizLabel2);
                    if(game.getEstado2()== 1){
                        player = jugador;
                        actualizarDatos(player);
                    }
                    try {
                        mostrarResultado(game,player);
                    } catch (IOException ex) {
                        Logger.getLogger(GuiJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        timer.schedule(task, 0, 70);
        
       

        this.setTitle("TETRIS");
        this.setResizable(true);      
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("jugar")){
            estado = 1;
            game.setEstado(1);
            game.setEstado2(1);
            jugar.setEnabled(false);
            pausa.setEnabled(true);
        }
        if(e.getActionCommand().equals("pausa")){
            this.setEnabled(false);
            estado = 2;
            GuiPausa pausa = new GuiPausa(player,this, game);
            game.setEstado(5);
        }
        if(e.getActionCommand().equals("salir")){
            
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(!game.fichaActual.bloqueo){
                if(game.fichaActual.ubicacion){
                    game.fichaActual.moverFichaIzq(game.matriz);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(!game.fichaActual.bloqueo){
                if(game.fichaActual.ubicacion){
                    game.fichaActual.moverFichaDer(game.matriz);
                }
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(game.fichaActual.ubicacion){
                game.fichaActual.girarFicha(game.matriz);
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(!game.fichaActual.bloqueo){
                if(game.fichaActual.ubicacion){
                    if(game.fichaActual.analizarBordeAbajo(game.matriz)){
                        game.fichaActual.moverFichaAba(game.matriz);
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
