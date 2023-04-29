/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JuegoTetris;

import java.util.ArrayList;

/**
 *
 * @author jrqui
 */
public abstract class Fichas {
    public abstract void crearFicha();
    public abstract void ubicarFicha(int[][] matriz);
    public abstract void moverFichaIzq(int[][] matriz);
    public abstract void moverFichaDer(int[][] matriz);
    public abstract void moverFichaAba(int[][] matriz);
    public abstract void girarFicha(int[][] matriz);
    public abstract void reset();
    public abstract boolean analizarBordeAbajo(int[][] matriz);
    
  
    public boolean var;
    public boolean bloqueo;
    public boolean movimiento;
    public boolean ubicacion;
    public int numFicha;
    public int numFicha2;
    public String posicion;
    public String direccion;
    ArrayList<ArrayList<Integer>>matrizFicha;
    public int filas;
    public int columnas;
    public int fila;
    public int columna;
    
    
    public void saberPosArri(int[][] matriz, Fichas ficha){
        ficha.var = false;
        for(int i = 0 ; i<matriz.length ; i++){
            for(int j = 0 ; j<matriz[i].length ; j++){
                if(!ficha.movimiento){
                    if(matriz[i][j]== ficha.numFicha2){
                       ficha.fila = i;
                       ficha.columna = j;
                       ficha.var = true;
                       break;
                    }
                }else{
                    if(matriz[i][j]== ficha.numFicha){
                        ficha.fila = i;
                        ficha.columna = j;
                        ficha.var = true;
                        break;
                    }
                }
            }
            if(ficha.fila == i && ficha.var){
                break;
            }
        }
    }
    
    public void saberPosAba(int[][] matriz, Fichas ficha){
        ficha.var = false;
        for(int i = matriz.length-1 ; i>=0 ; i--){
            for(int j = 0 ; j<matriz[i].length ; j++){
                if(matriz[i][j] == ficha.numFicha){
                    ficha.fila = i;
                    ficha.columna = j;
                    ficha.var = true;
                    break;
                }
            }
            if(ficha.fila == i && ficha.var){
                break;
            }
        }
    }
    
    public void bloquearFicha(int[][]matriz,Fichas ficha){
        saberPosArri(matriz,ficha);
        if(!ficha.movimiento){
            for(int x = matriz.length-1; x>=0 ; x--){
                for(int y = 0; y<matriz[x].length; y++){
                    if(matriz[x][y] == ficha.numFicha){
                        matriz[x][y] = ficha.numFicha2;
                    }
                }
            }
        }
        ubicacion = false;
        bloqueo = true;
    }
}
