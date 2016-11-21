/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import pe.edu.clases.Paciente;


/**
 *
 * @author fixt
 */
public class PacienteDAO {
    
    public String registrarPaciente(String nombres, String paterno, 
            String materno, String fecha_nacimiento, String correo) throws UnknownHostException, Exception{
        
       DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Pacientes");
        BasicDBObject doc = new BasicDBObject("nombres", nombres)
                .append("aPaterno", paterno)
                .append("aMaterno", materno)
                .append("fecnac", fecha_nacimiento)
                .append("correo",correo);
        coll.insert(doc);
        BasicDBObject query = new BasicDBObject("nombres", nombres).append("aPaterno", paterno).append("aMaterno", materno);
        DBObject obj = coll.findOne(query);
        String id = obj.get("_id").toString();
            
            
        return id;
                
    }
    
    public List<Paciente> listarPacientes() throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        DBCollection collectionPacientes = db.getCollection("Pacientes");
        DBCursor cursor = collectionPacientes.find();
        
        List<Paciente> pacientes = new ArrayList<>();

            while(cursor.hasNext()){
                
                DBObject doc = cursor.next();
                
                String id = doc.get("_id").toString();
                String nombres = doc.get("nombres").toString();
                String aPaterno = doc.get("aPaterno").toString();
                String aMaterno = doc.get("aMaterno").toString();
                String fecnac = doc.get("fecnac").toString();
                String correo = doc.get("correo").toString();
                int checkin = obtenercant(id);
                
                
                Paciente paciente = new Paciente(id, nombres, aPaterno, aMaterno, fecnac, correo,checkin);
                pacientes.add(paciente);
            }
        cursor.close();
            
        return pacientes;
        
    }
    
    public int obtenercant(String id) throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        int co=0;
        DBCollection collectionUsuarios = db.getCollection("Usuarios");
        BasicDBObject query1 = new BasicDBObject("id_paciente", id);
        DBObject obj = collectionUsuarios.findOne(query1);
        
        if (obj!= null){
            String id_usuario=obj.get("_id").toString();
            DBCollection collectionChecks = db.getCollection("checkins");
            BasicDBObject query = new BasicDBObject("id_usuario", id_usuario);
            DBCursor cursor = collectionChecks.find(query);
            
            co=  cursor.count();
            cursor.close();
        }
        
        
        return co;
    }
    
    public Paciente obtenerPaciente(String id) throws UnknownHostException, Exception{
                
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Pacientes");
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject obj = coll.findOne(query);
        
         
        Paciente paciente = new Paciente(obj.get("_id").toString(),obj.get("nombres").toString(), obj.get("aPaterno").toString(), 
                obj.get("aMaterno").toString(), obj.get("fecnac").toString(), obj.get("correo").toString());
             
             
            
        
          
        return paciente;
    }
    
    public void eliminarPaciente(String id) throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Pacientes"); 
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        coll.remove(query);
        new UsuarioDAO().eliminarUsuario(id);
                
    }
    
    public void actualizarPaciente(Paciente p) throws UnknownHostException, Exception{
        String id = p.getId();
        
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Pacientes"); 
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        BasicDBObject doc = new BasicDBObject("nombres", p.getNombres())
                .append("aPaterno", p.getPaterno())
                .append("aMaterno", p.getMaterno())
                .append("fecnac", p.getFecha_nacimiento())
                .append("correo", p.getCorreo());                
        coll.update(query, doc);
    
    }
    
}
