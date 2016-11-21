/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.mongo;

import java.net.UnknownHostException;

/**
 *
 * @author fixt
 */
public class CrearBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, Exception {
        
        EmpleadoDAO empleado = new EmpleadoDAO();
        String id = empleado.registrarEmpleado("Adminitrador", "Administrador", "Administrador", "12/12/12", "administrador@administrador.com", "admin");
        UsuarioDAO user = new UsuarioDAO();
        user.crearUsuario("admin", "admin", 1, id);
        id = empleado.registrarEmpleado("Recepcionista", "Recepcionista", "Recepcionista", "14/14/14", "recepcionista@recepcionista.com", "recepcionista");
        user.crearUsuario("recepcionista", "recepcionista", 1, id);
        
        
        
    }
    
}
