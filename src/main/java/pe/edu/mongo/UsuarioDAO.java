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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.bson.types.ObjectId;
import pe.edu.clases.Paciente;
import pe.edu.clases.Servidor;
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
    
    public String verSuministro(String sum) throws UnknownHostException, Exception{
        String resp = "0";
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Pacientes");
        BasicDBObject query = new BasicDBObject("suministro", sum);
        DBObject obj = coll.findOne(query);
        
        
        if (obj!=null){            
//            DBCollection coll2 = db.getCollection("Suministros");            
//            BasicDBObject doc = new BasicDBObject("id", sum.getNum())
//                .append("con", sum.getConsumo())
//                .append("fec", sum.getFecha()); 
//            //coll.update(query, doc);
//            coll2.insert(doc);
            
            String nombres = obj.get("nombres").toString();
            String aPaterno = obj.get("aPaterno").toString();
            String aMaterno = obj.get("aMaterno").toString();  
            
            resp = aPaterno+" "+aMaterno+" "+nombres;           
            
        }
                
        return resp;
    }
    
    public Boolean regSuministro(Suministro sum) throws UnknownHostException, Exception{
            
            sum.setFecha(obtenerFechaHoraActual());
            Servidor serv = new Servidor();
            return serv.enviarSuministro(sum);            
            
    }
    
    
    public static String obtenerFechaHoraActual() {
        // Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-05:00")); // No funciona
        // c.getTime(); // Sigue saliendo la hora en GMT-00:00.
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, -5);

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(c.getTime());
    }
    
    public String obtSuministro(String usuario) throws UnknownHostException, Exception{
        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Usuarios");
        BasicDBObject query = new BasicDBObject("usuario", usuario);
        DBObject obj = coll.findOne(query);
        String id = obj.get("id_paciente").toString();
        coll = db.getCollection("Pacientes");
        query = new BasicDBObject("_id", new ObjectId(id));
        obj = coll.findOne(query);
        return obj.get("suministro").toString();
    }
    
    public List<Suministro> obtenercant(String id) throws UnknownHostException, Exception{
        
        DB db = Coneccion.connectToMongo();
        int co=0;
        
        DBCollection collectionChecks = db.getCollection("Suministros");
        BasicDBObject query = new BasicDBObject("id", id);
        DBCursor cursor = collectionChecks.find(query);

        List<Suministro> suministros = new ArrayList<>();

            while(cursor.hasNext()){
                
                DBObject doc = cursor.next();
                
                
               
                String fecnac = doc.get("fec").toString();               
                int con =Integer.valueOf(doc.get("con").toString());
                
                
                Suministro suministro = new Suministro(id,con, fecnac);
                suministros.add(suministro);
            }
        cursor.close();
        
   
        return suministros;
    }
    
    public void pasarSuministor(Suministro sum) throws UnknownHostException, Exception{
        DB db = Coneccion.connectToMongo();
        DBCollection coll2 = db.getCollection("Suministros");            
        BasicDBObject doc = new BasicDBObject("id", sum.getNum())
            .append("con", sum.getConsumo())
            .append("fec", sum.getFecha()); 
        //coll.update(query, doc);
        coll2.insert(doc);
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
