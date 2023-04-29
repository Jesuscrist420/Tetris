/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Estudiantes
 */
public class Lector {
    
    public String traerUsuarios(String archivo) throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        String resultado = "";
        
        while((cadena = b.readLine())!=null) {
            resultado += cadena + "\n";
        }
        if(!resultado.equals("")){
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        b.close();
        return resultado;
    }
    public int evaluarUsuarios(String archivo , String usuarioIng, String passwordIng) throws FileNotFoundException, IOException{
        String cadena;
        int estado = 0;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] array = cadena.split("><");
            String usuario = array[0];
            String password = array[1];
            if(usuario.equals(usuarioIng) && password.equals(passwordIng)){
                estado = 1;
                break;
            }else{
                estado = 2;
            }
        }
        b.close();
        return estado;           
    }
    public String[] traerUsuario(String archivo , String usuarioIng)throws FileNotFoundException, IOException{
        String cadena;
        String[] user = new String[9];
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] array = cadena.split("><");
            String usuario = array[0];
            if(usuario.equals(usuarioIng)){
                user = array;
                break;
            }
        }
        b.close();
        return user;
    }
    public String modificarUsuario(String archivo , String usuarioIng)throws FileNotFoundException, IOException{
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        String resultado = "";
        while((cadena = b.readLine())!=null) {
            String[] array = cadena.split("><");
            String usuario = array[0];
            if(!usuario.equals(usuarioIng)){
               resultado += cadena + "\n";
            }
        }
        if(!resultado.equals("")){
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        //Agregar el modificado y luego escribirlo
        b.close();
        return resultado;
    }
}
