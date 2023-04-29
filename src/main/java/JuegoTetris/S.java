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
public class S extends Fichas {
    
    public void crearFicha() {
        for(int i = 0; i<2 ; i++){
            ArrayList<Integer> columna = new ArrayList();
            for(int j = 0 ; j<3 ; j++){
                if(i==0 && j == 0){
                    columna.add(0);
                }
                if(i==0 && j==1){
                    columna.add(3);    
                }
                if(i==0 && j==2){
                    columna.add(3);
                }
                if(i==1 && j==0){
                    columna.add(3);
                }
                if(i==1 && j==1){
                    columna.add(3);
                }
                if(i==1 && j==2){
                    columna.add(0);
                }
            }
            matrizFicha.add(columna);
        }
    
    }
    public void girarFicha(int[][] matriz){
        saberPosArri(matriz, this);
        for(int i = fila ; i<fila+1 ;i++){
            for(int j = columna ; j<columna+1 ; j++){
                if(posicion.equals("horizontal")){
                    matriz[i+1][j] = 0;
                    matriz[i+1][j+1] = 3;
                    matriz[i+1][j-1] = 0;
                    matriz[i-1][j] = 3;
                    posicion = "vertical";
                    filas = 3;
                    columnas = 2;
                }else{
                    if(columna+1 == matriz[i].length-1){
                        matriz[i][j] = 0;
                        matriz[i+2][j] = 3;
                        matriz[i+2][j+1] = 0;
                        matriz[i+2][j-1] = 3;
                    }else{
                        matriz[i][j] = 0;
                        matriz[i+1][j] = 0;
                        matriz[i+2][j] = 3;
                        matriz[i+1][j+2] = 3;
                    }
                    posicion = "horizontal";
                    filas = 2;
                    columnas = 3;
                }
            }
        }
    }
    
    @Override
    public void ubicarFicha(int[][] matriz) {
        for (int i = 0; i < 2; i++) {
            int inicio = ((matriz[i].length-2) - (matriz[i].length/2));
            for (int j = inicio; j < inicio+3; j++) {
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
               for(int j = matriz[i].length-1; 0<=j ; j--){
                    if(matriz[i][j]== 3){
                        if(posicion.equals("horizontal")){
                            if(j == matriz[i].length-1){
                                y = 1 ;
                                break;
                            }
                        }else{
                            if(columna+1 == matriz[i].length-1){
                                y = 1 ;
                                break;
                            }
                        }   
                        matriz[i][j] = 0;
                        matriz[i][j+1] = 3;
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
            for(int i = matriz.length-1 ; i>=0; i--){
                for(int j = 0; j<matriz[i].length; j++){
                    if(matriz[i][j]== 3){
                        if(posicion.equals("horizontal")){
                            if(j == 0){
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
                        matriz[i][j-1] = 3;
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
        if(posicion.equals("horizontal")){
            if(matriz[fila][columna+2]!= 0 || matriz[fila-1][columna+3]!= 0){
                validacion = false;
            }
        }else{
            if(matriz[fila-2][columna]!= 0 || matriz[fila-1][columna+1]!= 0 || matriz[fila][columna+1]!= 0){
                validacion = false;
            }
        }
        return validacion;
    }
    
    public boolean analizarBordeIzquierdo(int[][]matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("horizontal")){
            if(matriz[fila][columna-1]!= 0 || matriz[fila-1][columna]!= 0){
                validacion = false;
            }
        }else{
            if(matriz[fila-2][columna-2]!= 0 || matriz[fila-1][columna-2]!= 0 || matriz[fila][columna-1]!= 0){
                validacion = false;
            }
        }
        return validacion;
    }
    public boolean analizarBordeAbajo(int[][] matriz){
        boolean validacion = true;
        saberPosAba(matriz,this);
        if(posicion.equals("horizontal")){
            if(matriz[fila][columna+2]!=0){
                validacion=false;
            }else{
                if(matriz[fila+1][columna]!=0 || matriz[fila+1][columna+1]!=0){
                    validacion = false;
                }
            }
        }else{
            if(matriz[fila][columna-1]!= 0 || matriz[fila+1][columna]!= 0){
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
                if(posicion.equals("horizontal")){
                    matriz[fila][columna] = 0;
                    matriz[fila+1][columna] = numFicha;
                    matriz[fila-1][columna+1] = 0;
                    matriz[fila+1][columna+1] = numFicha;
                    matriz[fila-1][columna+2] = 0;
                    matriz[fila][columna+2] = numFicha;
                }else{
                    matriz[fila-2][columna-1] = 0;
                    matriz[fila][columna-1] = numFicha;
                    matriz[fila-1][columna] = 0;
                    matriz[fila+1][columna] = numFicha;
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
       filas = 2;
       columnas = 3;
       numFicha = 3;
       numFicha2 = 13;
       posicion = "horizontal";
       bloqueo = false;
       ubicacion = false;
    }
    public S(){
       var = true;
       movimiento = false;
       filas = 2;
       columnas = 3;
       fila = 0;
       columna = 0;
       numFicha = 3;
       numFicha2 = 13;
       posicion = "horizontal";
       bloqueo = false;
       ubicacion = false;
       this.matrizFicha = new ArrayList();
       crearFicha();
    }

    
}
