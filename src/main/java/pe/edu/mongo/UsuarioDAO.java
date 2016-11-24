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
import pe.edu.clases.Suministro;
import pe.edu.clases.Usuario;

/**
 *
 * @author fixt
 */
public class UsuarioDAO {
    
    public void crearUsuario(String codigo, String contrasena, int tipo,String id) throws UnknownHostException, Exception{
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        if (tipo ==1){
            BasicDBObject doc = new BasicDBObject("usuario", codigo)
                .append("contrasena", contrasena)
                .append("tipo",  tipo)
                .append("id_empleado",id);
            coll.insert(doc);
        }else if(tipo ==2){
            BasicDBObject doc = new BasicDBObject("usuario", codigo)
                .append("contrasena", contrasena)
                .append("tipo",  tipo)
                .append("id_paciente",id);
            coll.insert(doc);
        }
        
                    
    }
    
    public boolean validarUsuario(String usuario, String contrasena) throws UnknownHostException, Exception {        
        boolean b=false;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);
        if (obj!=null){
            String cont = obj.get("contrasena").toString();  
            if (cont.equals(contrasena)){
                b=true;
            }
            
        }
        return b;
    }
    
    public boolean verificarExisteUsuario(String usuario) throws UnknownHostException, Exception {        
        boolean b=false;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);         
        if (obj!=null){
            b=true;
        }
        
        
        
        return b;
    }
    
    public int getTipo(String usuario) throws UnknownHostException, Exception {
        int tipo;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);
        tipo = (Integer)obj.get("tipo");    
        
        
        return tipo;
    }
    
    public Usuario obtenerUsuarioP(String id) throws UnknownHostException, Exception{
                
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject obj = coll.findOne(query);
         
        Usuario usuario = new Usuario(obj.get("_id").toString(),obj.get("usuario").toString(), obj.get("contrasena").toString(), 
                (Integer)obj.get("tipo"), obj.get("id_paciente").toString());
         
        return usuario;
    }
    
    public Suministro verSuministro(Suministro sum) throws UnknownHostException, Exception{
        Suministro resp = new Suministro();
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Suministros");
        BasicDBObject query = new BasicDBObject("id", sum.getNum());
        DBObject obj = coll.findOne(query);
        
        resp.setNum("1");
        if (obj!=null){
            resp.setNum(obj.get("id").toString());
            resp.setConsumo(Integer.valueOf(obj.get("con").toString()));
            resp.setFecha(obj.get("fec").toString());
            BasicDBObject doc = new BasicDBObject("id", sum.getNum())
                .append("con", sum.getConsumo())
                .append("fec", sum.getFecha()); 
            //coll.update(query, doc);
            coll.insert(doc);
        }
                
        return resp;
    }
    
    
    
    public String obtenerNombreUsuario(String id) throws UnknownHostException, Exception{
                
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("id_paciente", id);
        DBObject obj = coll.findOne(query);
         
        String nombre = obj.get("usuario").toString();
         
        return nombre;
    }
    
    public void actualizarUsuario(String id,Usuario u) throws UnknownHostException, Exception{
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        
         
        BasicDBObject query = new BasicDBObject("id_paciente", id);
        BasicDBObject doc = new BasicDBObject("usuario", u.getNombre())
                .append("contrasena", u.getContrasena())
                .append("tipo", 2)
                .append("id_paciente", id);                          
        coll.update(query, doc);
    
    }  
    
    public void registrarCheckIn(String idpromo, String nombre, String fec) throws UnknownHostException, Exception {
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("checkins");
        String id = getId(nombre);
        
            BasicDBObject doc = new BasicDBObject("id_usuario", id)
                .append("fecha", fec)
                .append("id_promocion", idpromo);
                
        coll.insert(doc);
        new PromocionDAO().registrarPromoCheckin(idpromo);
    }
    
    public String getId(String usuario) throws UnknownHostException, Exception {
        String id;
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);
        id = obj.get("_id").toString();    
        
        
        return id;
    }
    
    public void eliminarUsuario(String id) throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios"); 
        BasicDBObject query = new BasicDBObject("id_paciente", id);
        coll.remove(query);
        
                
    }
    
    
    
}
