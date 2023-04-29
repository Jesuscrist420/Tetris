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
public class L extends Fichas{
    
    public void crearFicha() {
        for(int i = 0; i<3 ; i++){
            ArrayList<Integer> columna = new ArrayList();
            for(int j = 0 ; j<2 ; j++){
                if(i==0 && j == 0){
                    columna.add(5);
                }
                if(i==0 && j==1){
                    columna.add(0);    
                }
                if(i==1 && j == 0){
                    columna.add(5);
                }
                if(i==1 && j==1){
                    columna.add(0);    
                }
                if(i==2 && j == 0){
                    columna.add(5);
                }
                if(i==2 && j==1){
                    columna.add(5);    
                }
            }
            matrizFicha.add(columna);
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
    
    public void girarFicha(int[][] matriz){
        saberPosArri(matriz,this);
        //Ficha viene vertical y la pasamos a horizontal
        for(int i = fila; i<fila+1;i++){
            for(int j = columna; j<columna+1;j++){
                if(posicion.equals("vertical")){
                    //Ficha viene vertical derecha y pasamos a horizontal derecha.
                    if(direccion.equals("derecha")){
                        if(columna+1 == matriz[i].length-1){
                            matriz[i][j]= 0;
                            matriz[i+1][j]= 0;
                            matriz[i+2][j-1]= 5;
                            matriz[i+1][j+1]= 5;
                        }else{
                            matriz[i][j]= 0;
                            matriz[i+1][j]= 0;
                            matriz[i+2][j+2]= 5;
                            matriz[i+1][j+2]= 5;
                        }
                        posicion = "horizontal";
                        filas = 2;
                        columnas = 3;
                        direccion = "derecha";
                    }else{
                        //viene vertical izquierda y la pasamos a horizontal derecha.
                        if(columna==0){
                            matriz[i+1][j]= 5;
                            matriz[i+1][j+1]= 0;
                            matriz[i+2][j+1]= 0;
                            matriz[i][j+2]= 5;
                        }else{
                            matriz[i+1][j+1]= 0;
                            matriz[i+2][j+1]= 0;
                            matriz[i][j-1]= 5;
                            matriz[i+1][j-1]= 5;
                        }
                        posicion = "horizontal";
                        filas = 2;
                        columnas = 3;
                        direccion = "izquierda";
                    }
                }else{
                    //viene horizontal derecha y la queremos pasar a vertical izquierda.
                    if(fila<matriz.length-2){
                        if(direccion.equals("derecha")){
                            matriz[i][j]= 0;
                            matriz[i+2][j]= 5;
                            matriz[i+1][j-2]= 0;
                            matriz[i+3][j]= 5;
                            posicion = "vertical";
                            filas = 3;
                            columnas = 2;
                            direccion = "izquierda";
                        }else{
                        //viene horizontal izquierda y la queremos pasar a vertical derecha.
                            matriz[i][j+1]= 0;
                            matriz[i][j+2]= 0;
                            matriz[i+2][j]= 5;
                            matriz[i+2][j+1]= 5;
                            posicion = "vertical";
                            filas = 3;
                            columnas = 2;
                            direccion = "derecha";
                        }
                    }
                }
            }
        }
    }
    
    public void moverFichaDer(int[][] matriz){
        int y = 0;
        if(analizarBordeDerecho(matriz)){
            saberPosArri(matriz,this);
            for(int i = matriz.length-1; i>=0; i--){
                for(int j = matriz[i].length-1; j>=0 ; j--){
                    if(matriz[i][j]== 5){
                        if(posicion.equals("vertical")){
                            if(j == matriz[i].length-1){
                                y = 1;
                                break;
                            }
                        }else{
                            if(direccion.equals("derecha")){
                                if(columna == matriz[i].length-1){
                                    y = 1;
                                    break;
                                }
                            }else{
                                if(columna+2 == matriz[i].length-1){
                                    y = 1;
                                    break;
                                }
                            }
                        }
                        matriz[i][j] = 0;
                        matriz[i][j+1] = 5;
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
            for(int i = 0; i<matriz.length; i++){
                for(int j = 0; j<matriz[i].length; j++){
                    if(matriz[i][j]== 5){
                        if(posicion.equals("vertical")){
                            if(j == 0){
                                y = 1;
                                break;
                            }
                        }else{
                            if(direccion.equals("derecha")){
                                if(columna-2 == 0){
                                    y = 1;
                                    break;
                                }
                            }else{
                                if(columna == 0){
                                    y = 1;
                                    break;
                                }
                            }
                        }
                        matriz[i][j] = 0;
                        matriz[i][j-1] = 5;
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
            if(direccion.equals("derecha")){
                if(matriz[fila-1][columna+1]!=0 || matriz[fila-2][columna+1]!=0 || matriz[fila][columna+2]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna+1]!=0 || matriz[fila-1][columna+1]!=0 || matriz[fila-2][columna+1]!=0){
                   validacion = false; 
                }
            }
        }else{
            if(direccion.equals("derecha")){
                if(matriz[fila][columna+3]!=0 || matriz[fila-1][columna+3]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna+1]!=0 || matriz[fila-1][columna+3]!=0){
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
            if(direccion.equals("derecha")){
                if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna-1]!=0 || matriz[fila-2][columna-1]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila-2][columna-2]!=0 || matriz[fila-1][columna-1]!=0 || matriz[fila][columna-1]!=0){
                   validacion = false; 
                }
            }
        }else{
            if(direccion.equals("derecha")){
                if(matriz[fila-1][columna+1]!=0 || matriz[fila][columna-1]!=0){
                   validacion = false; 
                }
            }else{
                if(matriz[fila][columna-1]!=0 || matriz[fila-1][columna-1]!=0){
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
            if(direccion.equals("derecha")){
                if(matriz[fila+1][columna]!= 0 || matriz[fila+1][columna+1]!= 0){
                    validacion = false;
                }
            }else{
                if(matriz[fila-1][columna-1]!=0 || matriz[fila+1][columna]!=0){
                    validacion = false;
                }
            }
        }else{
            if(direccion.equals("derecha")){
                if(matriz[fila+1][columna]!=0 || matriz[fila+1][columna+1]!=0 ||matriz[fila+1][columna+2]!=0){
                    validacion = false;
                }
            }else{
                if(matriz[fila+1][columna]!=0 || matriz[fila][columna+1]!=0 ||matriz[fila][columna+2]!=0){
                    validacion = false;
                }
            }
        }
        
        return validacion;
    }
    public void moverFichaAba(int[][]matriz){
        saberPosAba(matriz,this);
        
        if(fila != matriz.length-1){
            if(analizarBordeAbajo(matriz)){
                if(posicion.equals("vertical")){
                    if(direccion.equals("derecha")){
                        matriz[fila-2][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                        matriz[fila][columna+1] = 0;
                        matriz[fila+1][columna+1] = numFicha;
                    }else{
                        matriz[fila-2][columna-1] = 0;
                        matriz[fila-1][columna-1] = numFicha;
                        matriz[fila-2][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                    }
                }else{
                    if(direccion.equals("derecha")){
                        matriz[fila][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                        matriz[fila][columna+1] = 0;
                        matriz[fila+1][columna+1] = numFicha;
                        matriz[fila-1][columna+2] = 0;
                        matriz[fila+1][columna+2] = numFicha;
                    }else{
                        matriz[fila-1][columna] = 0;
                        matriz[fila+1][columna] = numFicha;
                        matriz[fila-1][columna+1] = 0;
                        matriz[fila][columna+1] = numFicha;
                        matriz[fila-1][columna+2] = 0;
                        matriz[fila][columna+2] = numFicha;
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
       movimiento = true;
       fila = 0;
       columna = 0;
       filas = 3;
       columnas = 2;
       numFicha = 5; 
       numFicha2 = 15; 
       posicion = "vertical";
       direccion = "derecha";
       bloqueo = false;
       ubicacion = false;
    }
    public L(){
       var = true;
       movimiento = true;
       fila = 0;
       columna = 0;
       filas = 3;
       columnas = 2;
       numFicha = 5; 
       numFicha2 = 15; 
       posicion = "vertical";
       direccion = "derecha";
       bloqueo = false;
       ubicacion = false;
       this.matrizFicha = new ArrayList();
       crearFicha();
    }
}
