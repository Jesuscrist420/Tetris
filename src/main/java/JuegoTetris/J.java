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
public class J extends Fichas{
    
    
    public void crearFicha() {
        for(int i = 0; i<3 ; i++){
            ArrayList<Integer> columna = new ArrayList();
            for(int j = 0 ; j<2 ; j++){
                if(i==0 && j == 0){
                    columna.add(0);
                }
                if(i==0 && j==1){
                    columna.add(6);    
                }
                if(i==1 && j == 0){
                    columna.add(0);
                }
                if(i==1 && j==1){
                    columna.add(6);    
                }
                if(i==2 && j == 0){
                    columna.add(6);
                }
                if(i==2 && j==1){
                    columna.add(6);    
                }
            }
            matrizFicha.add(columna);
        }    
    }
    public void girarFicha(int[][] matriz){
        saberPosArri(matriz,this);
        //Viene vertical y la pasamos a horizontal
        for(int i = fila; i<fila+1;i++){
            for(int j = columna; j<columna+1;j++){
                if(posicion.equals("vertical")){
                    //Ficha viene vertical izquierda y pasamos a horizontal izquierda.
                    if(direccion.equals("izquierda")){
                        if(columna-1 == 0){
                            matriz[i][j]= 0;
                            matriz[i+1][j]= 0;
                            matriz[i+1][j-1]= 6;
                            matriz[i+2][j+1]= 6;
                        }else{
                            matriz[i][j]= 0;
                            matriz[i+1][j]= 0;
                            matriz[i+2][j-2]= 6;
                            matriz[i+1][j-2]= 6;
                        }
                        posicion = "horizontal";
                        filas = 2;
                        columnas = 3;
                        direccion = "izquierda";
                    }else{
                        //viene vertical derecha y la pasamos a horizontal derecha.
                        if(columna==0){
                            matriz[i][j+2]= 6;
                            matriz[i+1][j]= 0;
                            matriz[i+2][j]= 0;
                            matriz[i+1][j+2]= 6;
                        }else{
                            matriz[i+1][j+1]= 6;
                            matriz[i+1][j]= 0;
                            matriz[i+2][j]= 0;
                            matriz[i][j-1]= 6;
                        }
                        posicion = "horizontal";
                        filas = 2;
                        columnas = 3;
                        direccion = "derecha";
                    }
                }else{
                    //viene horizontal izquierda y la queremos pasar a vertical derecha.
                    if(fila<matriz.length-2){
                        if(direccion.equals("izquierda")){
                            matriz[i-1][j]= 6;
                            matriz[i-1][j+1]= 6;
                            matriz[i+1][j+1]= 0;
                            matriz[i+1][j+2]= 0;
                            posicion = "vertical";
                            filas = 3;
                            columnas = 2;
                            direccion = "derecha";
                        }else{
                        //viene horizontal derecha y la queremos pasar a vertical izquierda.
                            matriz[i][j]= 0;
                            matriz[i][j+1]= 0;
                            matriz[i+1][j+1]= 6;
                            matriz[i-1][j+2]= 6;
                            posicion = "vertical";
                            filas = 3;
                            columnas = 2;
                            direccion = "izquierda";
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public void ubicarFicha(int[][] matriz) {
        for (int i = 0; i < 3; i++) {
            int inicio = ((matriz[i].length-1) - (matriz[i].length/2));
            for (int j = inicio; j < inicio+2; j++) {
                matriz[i][j] = matrizFicha.get(i).get(j-inicio);
            }
        }
        ubicacion = true;
        
    }
    
    public void moverFichaDer(int[][] matriz){
        int y = 0;
        if(analizarBordeDerecho(matriz)){
            saberPosArri(matriz,this);
            for(int i = 0; i<matriz.length; i++){
                for(int j = matriz[i].length-1; j>=0 ; j--){
                    if(matriz[i][j]== 6){
                        if(posicion.equals("vertical")){
                            if(j == matriz[i].length-1){
                               y = 1;
                               break;
                            }
                        }else{
                            if(columna+2 == matriz[i].length-1){
                                y = 1;
                                break;
                            }  
                        }
                        matriz[i][j] = 0;
                        matriz[i][j+1] = 6;
                    }
                }
                if(y == 1){
                    break;
                }
            }
        }
    }
    
    public void moverFichaIzq(int[][] matriz) {
        int y = 0;
        if(analizarBordeIzquierdo(matriz)){
            saberPosArri(matriz,this);
            for(int i = matriz.length-1; i>=0; i--){
                for(int j = 0; j<matriz[i].length; j++){
                    if(matriz[i][j]== 6){
                        if(posicion.equals("vertical")){
                            if(j==0){
                                y = 1;
                                break;
                            }
                        }else{
                            if(columna == 0){
                                y = 1;
                                break;
                            }
                        }
                        matriz[i][j] = 0;
                        matriz[i][j-1] = 6;
                    }
                }
                if(y == 1){
                    break;
                }
            }
        }
    }
    public boolean analizarBordeDerecho(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(direccion.equals("izquierda")){
                if(matriz[fila][columna+2]!=0 || matriz[fila-1][columna+2]!=0 || matriz[fila-2][columna+2]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna+1]!=0 || matriz[fila-1][columna+1]!=0 || matriz[fila-2][columna+2]!=0){
                   validacion = false; 
                }
            }
        }else{
            if(direccion.equals("izquierda")){
                if(matriz[fila-1][columna+1]!=0 || matriz[fila][columna+3]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna+1]!=0 || matriz[fila-1][columna+1]!=0){
                   validacion = false; 
                }
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeIzquierdo(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(direccion.equals("izquierda")){
                if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna]!=0 || matriz[fila-2][columna]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna-1]!=0 || matriz[fila-2][columna-1]!=0){
                   validacion = false; 
                }
            }
        }else{
            if(direccion.equals("izquierda")){
                if(matriz[fila-1][columna-1]!=0 || matriz[fila][columna-1]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna-3]!=0){
                   validacion = false; 
                }
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeAbajo(int[][] matriz){
        boolean validacion = true;
        
        saberPosAba(matriz,this);
        if(posicion.equals("vertical")){
            if(direccion.equals("izquierda")){
                if(matriz[fila+1][columna]!= 0 || matriz[fila+1][columna+1]!= 0){
                    validacion = false;
                }
            }else{
                if(matriz[fila-1][columna+1]!= 0 || matriz[fila+1][columna]!= 0){
                    validacion = false;
                }
            }
        }else{
            if(direccion.equals("izquierda")){
                if(matriz[fila+1][columna]!= 0 || matriz[fila+1][columna+1]!= 0 || matriz[fila+1][columna+2]!= 0){
                    validacion = false;
                }
            }else{
                if(matriz[fila+1][columna]!= 0 || matriz[fila][columna-1]!= 0 || matriz[fila][columna-2]!= 0){
                    validacion = false;
                }
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
                    if(direccion.equals("izquierda")){
                        matriz[fila][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                        matriz[fila-2][columna+1] = 0;
                        matriz[fila+1][columna+1] = numFicha;
                    }else{
                        matriz[fila-2][columna+1] = 0;
                        matriz[fila-1][columna+1] = numFicha;
                        matriz[fila-2][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                    }
                }else{
                    if(direccion.equals("izquierda")){
                        matriz[fila-1][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                        matriz[fila][columna+1] = 0;
                        matriz[fila+1][columna+1] = numFicha;
                        matriz[fila][columna+2] = 0;
                        matriz[fila+1][columna+2] = numFicha;
                    }else{
                        matriz[fila-1][columna-2] = 0;
                        matriz[fila][columna-2] = numFicha;
                        matriz[fila-1][columna-1] = 0;
                        matriz[fila][columna-1] = numFicha;
                        matriz[fila-1][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
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
       fila = 0;
       columna = 0;
       filas = 3;
       columnas = 2;
       numFicha = 6; 
       numFicha2 = 16; 
       posicion = "vertical";
       direccion = "izquierda"; 
       bloqueo = false;
       ubicacion = false;
    }
    
    public J(){
       var = true;
       movimiento = false;
       fila = 0;
       columna = 0;
       filas = 3;
       columnas = 2;
       numFicha = 6; 
       numFicha2 = 16; 
       posicion = "vertical";
       direccion = "izquierda";
       bloqueo = false;
       ubicacion = false;
       this.matrizFicha = new ArrayList();
       crearFicha();
    }

}
