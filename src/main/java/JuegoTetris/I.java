package JuegoTetris;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jrqui
 */
public class I extends Fichas{
    
    public void crearFicha() {
        for(int i = 0; i<4 ; i++){
            ArrayList<Integer> columna = new ArrayList();
            for(int j = 0 ; j<1 ; j++){
                if(i==0 && j == 0){
                    columna.add(2);
                }
                if(i==1 && j==0){
                    columna.add(2);    
                }
                if(i==2 && j==0){
                    columna.add(2);
                }
                if(i==3 && j==0){
                    columna.add(2);
                }
            }
            matrizFicha.add(columna);
        }
    }
    public void girarFicha(int[][] matriz){
        saberPosArri(matriz,this); 
        for(int i = fila ; i<fila+1;i++){
            for(int j = columna ; j<columna+1 ; j++){
                if(posicion.equals("vertical")){
                    matriz[i+1][j] = 0;
                    matriz[i+2][j] = 0;
                    matriz[i+3][j] = 0;
                    if(columna>=7){
                        matriz[i][j-1] = 2;
                        matriz[i][j-2] = 2;
                        matriz[i][j-3] = 2;
                        direccion = "izquierda";
                    }else{
                        matriz[i][j+1] = 2;
                        matriz[i][j+2] = 2;
                        matriz[i][j+3] = 2;
                        direccion = "derecha";
                    }
                    posicion = "horizontal";
                    filas = 1;
                    columnas = 4;
                }else{
                    if(fila<=matriz.length-4){
                        matriz[i][j+1] = 0;
                        matriz[i][j+2] = 0;
                        matriz[i][j+3] = 0;
                        matriz[i+1][j] = 2;
                        matriz[i+2][j] = 2;
                        matriz[i+3][j] = 2;
                        posicion = "vertical";
                        filas = 4;
                        columnas = 1;
                    }
                }
            }
        }
    }
    
    @Override
    public void ubicarFicha(int[][] matriz) {
        for (int i = 0; i < 4; i++) {
            int inicio = ((matriz[i].length-1) - (matriz[i].length/2));
            for (int j = inicio; j < inicio+1; j++) {
                matriz[i][j] = matrizFicha.get(i).get(j-inicio);
            }
        }
        ubicacion = true;
        movimiento = false;
    }
    
    public void moverFichaDer(int[][] matriz){
        if(analizarBordeDerecho(matriz)){
            for(int i = 0; i<matriz.length; i++){
                for(int j = matriz[i].length - 1 ; j>=0 ; j--){
                    if(matriz[i][j]== 2){
                        if(j == matriz[i].length - 1 ){
                            break;
                        }
                        matriz[i][j] = 0;
                        matriz[i][j+1] = 2;
                    }
                }
            }
        }
    }
    
    public void moverFichaIzq(int[][] matriz) {
        if(analizarBordeIzquierdo(matriz)){
            for(int i = 0; i<matriz.length; i++){
               for(int j = 0; j<matriz[i].length; j++){
                    if(matriz[i][j]== 2){
                        if(j==0){
                            break;
                        }
                        matriz[i][j] = 0;
                        matriz[i][j-1] = 2;
                    }
                }
            }
        }
    }
    public boolean analizarBordeDerecho(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(matriz[fila][columna+1]!=0 || matriz[fila-1][columna+1]!=0 || matriz[fila-2][columna+1]!=0 || matriz[fila-3][columna+1]!=0){
                validacion = false;
            }
        }else{
            if(matriz[fila][columna+4]!=0){
               validacion = false; 
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeIzquierdo(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna-1]!=0 || matriz[fila-2][columna-1]!=0 || matriz[fila-3][columna-1]!=0){
                validacion = false;
            }
        }else{
            if(matriz[fila][columna-1]!=0){
               validacion = false; 
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeAbajo(int[][] matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(matriz[fila+1][columna] !=0){
                validacion = false;
            }
        }else{
            if(matriz[fila+1][columna] !=0 || matriz[fila+1][columna+1] !=0 || matriz[fila+1][columna+2] !=0 ||matriz[fila+1][columna+2] !=0){
                validacion = false;
            }
        }
        return validacion;
    }
    public void moverFichaAba(int[][]matriz){
        movimiento = true;
        
        saberPosAba(matriz,this);
        if(fila != matriz.length-1){
            if(analizarBordeAbajo(matriz)){
                if(posicion.equals("vertical")){
                    matriz[fila-3][columna] = 0;
                    matriz[fila+1][columna] = numFicha;
                }else{
                    for(int i = columna;i < columna+columnas; i++){
                        matriz[fila][i]=0;
                        matriz[fila+1][i]= numFicha;
                    }
                }
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
       movimiento = false;
       numFicha = 2;
       numFicha2 = 12;
       fila = 0;
       columna = 0;
       filas = 4;
       columnas = 1;
       posicion = "vertical"; 
       ubicacion = false;
       bloqueo = false;
    }
    public I(){
       var = true;
       movimiento = false;
       numFicha = 2;
       numFicha2 = 12;
       filas = 4;
       columnas = 1;
       fila = 0;
       columna = 0;
       posicion = "vertical";
       bloqueo = false;
       ubicacion = false;
       this.matrizFicha = new ArrayList();
       crearFicha(); 
    }
    
}
