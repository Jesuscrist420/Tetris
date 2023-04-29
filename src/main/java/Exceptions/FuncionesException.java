/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author jrqui
 */
public class FuncionesException {
   
   
   public static Boolean regVacio(String usuario, String nombre, String password) throws RegistroVacioException{
       if(usuario.equals("") || nombre.equals("") || password.equals("")){
           throw new RegistroVacioException("Registro vacio");
       }
       return true;
       
   }
}
