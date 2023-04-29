package JuegoTetris;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jrqui
 */
public class Jugador {
    private String nombre;
    private String contraseña; 
    private int juegosJugados;
    private int juegosPerdidos;
    private int juegosGanados;
    private int puntaje;
    private int nivel;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getJuegosJugados() {
        return juegosJugados;
    }

    public void setJuegosJugados(int juegosJugados) {
        this.juegosJugados = juegosJugados;
    }

    public int getJuegosPerdidos() {
        return juegosPerdidos;
    }

    public void setJuegosPerdidos(int juegosPerdidos) {
        this.juegosPerdidos = juegosPerdidos;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public void setJuegosGanados(int juegosGanados) {
        this.juegosGanados = juegosGanados;
    }
    
    
    public Jugador(){
        
        this.nombre = "";
        this.contraseña = "";
        this.juegosGanados = 0;
        this.juegosPerdidos = 0;
        this.juegosJugados = 0;
        this.puntaje = 0;
        this.nivel = 1;
        
    }
}
