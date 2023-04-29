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
public class O extends Fichas{
    public void crearFicha() {
        for(int i = 0; i<2 ; i++){
            ArrayList<Integer> columna = new ArrayList();
            for(int j = 0 ; j<2 ; j++){
                if(i==0 && j == 0){
                    columna.add(1);
                }
                if(i==0 && j==1){
                    columna.add(1);    
                }
                if(i==1 && j==0){
                    columna.add(1);
                }
                if(i==1 && j==1){
                    columna.add(1);
                }
            }
            matrizFicha.add(columna);
        }
    }
    public void ubicarFicha(int[][] matriz){
        boolean espacioLibre = true;
        for (int i = 0; i < 2; i++) {
            int inicio = ((matriz[i].length-1) - (matriz[i].length/2));
            for (int j = inicio; j < inicio+2; j++) {
               if(matriz[i][j]==0){
                    matriz[i][j] = matrizFicha.get(i).get(j-inicio);
               }else{
                   espacioLibre = false;
                   break;
               }
            }
            if(!espacioLibre){
                break;
            }
        }
        movimiento = false;
        ubicacion = true;
    }
    public void girarFicha(int[][] matriz){
        
    }
    @Override
    public void moverFichaDer(int[][] matriz){
        if(analizarBordeDerecho(matriz)){
            for(int i = 0; i<matriz.length; i++){
                for(int j = matriz[i].length - 1 ; j>=0 ; j--){
                    if(matriz[i][j] == 1){
                        if(j == matriz[i].length - 1 ){
                            break;
                        }
                        matriz[i][j] = 0;
                        matriz[i][j+1] = 1;
                    }
                }
            }
        }
    }
    
    public void moverFichaIzq(int[][] matriz) {
        if(analizarBordeIzquierdo(matriz)){
            for(int i = 0; i<matriz.length; i++){
                for(int j = 0;j<matriz[i].length; j++){
                    if(matriz[i][j] == 1){
                        if(j==0){
                            break;
                        }
                        matriz[i][j] = 0;
                        matriz[i][j-1] = 1;
                    }
                }
            }
        }
    }
    public boolean analizarBordeDerecho(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        for(int i = fila ; i>fila-1 ; i--){
            for(int j = columna ; j<columna+1 ; j++){
                if(matriz[i][j+2]!=0 || matriz[i-1][j+2]!=0){
                    validacion = false;
                    break;
                }
                if(!validacion){
                    break;
                }
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeIzquierdo(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        for(int i = fila ; i>fila-1 ; i--){
            for(int j = columna ; j<columna+1 ; j++){
                if(matriz[i][j-1]!=0 || matriz[i-1][j-1]!=0){
                    validacion = false;
                    break;
                }
                if(!validacion){
                    break;
                }
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeAbajo(int[][] matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        for(int i = fila; i<fila+1 ;i++){
            for(int j = columna; j<columna+2 ; j++){
                if(matriz[i+1][j] != 0){
                    validacion = false;
                    break;
                }
            }
            
        }
        return validacion;
    }
    public void moverFichaAba(int[][]matriz ){
        movimiento = true;
        saberPosAba(matriz,this);
        if(fila != matriz.length-1){
            if(analizarBordeAbajo(matriz)){
                matriz[fila-1][columna] = 0;
                matriz[fila-1][columna+1] = 0;
                matriz[fila+1][columna] = numFicha;
                matriz[fila+1][columna+1] = numFicha;
            }else{
                movimiento = false;
                bloquearFicha(matriz,this);
            }
        }else{
            movimiento = false;
            bloquearFicha(matriz,this);
        }
    }
    public void reset(){
       var = true;
       numFicha = 1;
       numFicha2 = 11;
       fila = 0;
       columna = 0;
       filas = 2;
       columnas = 2;
       movimiento = false;
       bloqueo = false;
       ubicacion = false;
    }
    public O(){
       var = true;
       numFicha = 1;
       numFicha2 = 11;
       filas = 2;
       columnas = 2;
       fila = 0;
       columna = 0;
       movimiento = false;
       ubicacion = false;
       this.matrizFicha = new ArrayList();
       crearFicha();
       bloqueo = false;
    }

    
    

   
    
}
