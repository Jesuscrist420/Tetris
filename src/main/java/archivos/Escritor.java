/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Estudiantes
 */
public class Escritor {
    public static void escribeContenido(String archivo, String usuario, String pass , String nombre ) throws FileNotFoundException, IOException {
        
        String data, datosBD;
        String juegosJug = "0";
        String juegosGan = "0";
        String juegosPerd = "0";
        String puntaje = "0";
        String ultimoPuntaje = "0";
        String nivel = "0";
        data = usuario+"><"+pass+"><"+nombre+"><"+puntaje+"><"+juegosJug+"><"+juegosGan+"><"+juegosPerd+"><"+ultimoPuntaje+"><"+nivel;
        Lector lee = new Lector();
        datosBD = lee.traerUsuarios(archivo);
        if(data.equals("><><><")){
            data = "";
        }
        if(!datosBD.equals("")){
            data = lee.traerUsuarios(archivo) + "\n" + data;
        }
        FileWriter  f = new FileWriter (archivo);
        BufferedWriter  bw = new BufferedWriter(f);
        bw.write(data);
        bw.close();
    }
    public static void guardar(String archivo, String[] player) throws FileNotFoundException, IOException{
        Lector lee = new Lector();
        String data , datosBD;
        data = player[0]+"><"+player[1]+"><"+player[2]+"><"+player[3]+"><"+player[4]+"><"+player[5]+"><"+player[6]+"><"+player[7]+"><"+player[8];
        datosBD = lee.modificarUsuario(archivo, player[0]);
        if(!datosBD.equals("")){
            data = datosBD+"\n" +data;
        }
        FileWriter  f = new FileWriter (archivo);
        BufferedWriter  bw = new BufferedWriter(f);
        bw.write(data);
        bw.close();
        
    }
}
