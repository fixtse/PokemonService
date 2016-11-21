/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import org.bson.types.ObjectId;

/**
 *
 * @author fixt
 */
public class EmpleadoDAO {
    
    public String registrarEmpleado(String nombres, String paterno, 
            String materno, String fecha_nacimiento, String correo,String puesto) throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Empleados");
        BasicDBObject doc = new BasicDBObject("nombres", nombres)
                .append("aPaterno", paterno)
                .append("aMaterno", materno)
                .append("fecnac", fecha_nacimiento)
                .append("correo",correo)
                .append("puesto",puesto);
        coll.insert(doc);
        BasicDBObject query = new BasicDBObject("nombres", nombres).append("aPaterno", paterno).append("aMaterno", materno);
        DBObject obj = coll.findOne(query);
        String id = obj.get("_id").toString();
            
            
        return id;
                
    }
    
    public int getPuesto(String id) throws UnknownHostException, Exception {
        String puesto;
        
        int tipo;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Empleados");
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject obj = coll.findOne(query);
        puesto = obj.get("puesto").toString(); 
        if (puesto.equals("admin")){
            tipo = 1;
        }else{
            tipo = 2;
        }
        
        
        return tipo;
    }
    
    public String getIdEmpleado(String usuario) throws UnknownHostException, Exception {
        String id;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);
        id = obj.get("id_empleado").toString();    
        
        
        return id;
    }
}
