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
import pe.edu.clases.Promocion;

/**
 *
 * @author fixt
 */
public class PromocionDAO {

    public void registrarPromocion(String nombre, String descripcion,
            int cantidad_checkins, String fecha_inicio, String fecha_fin, String url) throws UnknownHostException, Exception {

        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Promociones");
        BasicDBObject doc = new BasicDBObject("nombre", nombre)
                .append("descripcion", descripcion)
                .append("checkins", cantidad_checkins)
                .append("fecha_inicio", fecha_inicio)
                .append("fecha_fin", fecha_fin)
                .append("url", url);
        coll.insert(doc);

    }

    public Promocion obtenerPromocion(String id) throws UnknownHostException, Exception {

        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Promociones");

        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject doc = coll.findOne(query);

        String nombre = doc.get("nombre").toString();
        String descripcion = doc.get("descripcion").toString();
        int checkins = (Integer) doc.get("checkins");
        String fecha_inicio = doc.get("fecha_inicio").toString();
        String fecha_fin = doc.get("fecha_fin").toString();
        String url = doc.get("url").toString();
        Promocion promocion = new Promocion(id, nombre, descripcion, checkins, fecha_inicio, fecha_fin, url);

        return promocion;
    }

    public List<Promocion> listarPromociones() throws UnknownHostException, Exception {

        DB db = Coneccion.connectToMongo();
        DBCollection collectionPosiciones = db.getCollection("Promociones");
        DBCursor cursor = collectionPosiciones.find();

        List<Promocion> promociones = new ArrayList<>();

        while (cursor.hasNext()) {

            DBObject doc = cursor.next();

            String id = doc.get("_id").toString();
            String nombre = doc.get("nombre").toString();
            String descripcion = doc.get("descripcion").toString();
            int checkins = (Integer) doc.get("checkins");
            String fecha_inicio = doc.get("fecha_inicio").toString();
            String fecha_fin = doc.get("fecha_fin").toString();
            String url = doc.get("url").toString();

            Promocion promocion = new Promocion(id, nombre, descripcion, checkins, fecha_inicio, fecha_fin, url);
            promociones.add(promocion);

        }
        cursor.close();

        return promociones;

    }

    public void eliminarPromocion(String id) throws UnknownHostException, Exception {

        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Promociones");

        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        coll.remove(query);

    }

    public void editarPromocion(Promocion p) throws UnknownHostException, Exception {
        String id = p.getId();

        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Promociones");
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        BasicDBObject doc = new BasicDBObject("nombre", p.getNombre())
                .append("descripcion", p.getDescripcion())
                .append("checkins", p.getCantidad_checkins())
                .append("fecha_inicio", p.getFecha_inicio())
                .append("fecha_fin", p.getFecha_fin())
                .append("url", p.getUrl());
        coll.update(query, doc);

    }

    public void registrarPromoCheckin(String id) throws UnknownHostException, Exception {

        DB db = Coneccion.connectToMongo();
        DBCollection coll = db.getCollection("Promociones");
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));
        DBObject ant = coll.findOne(query);
        int cant = (Integer) ant.get("checkins");

        BasicDBObject doc = new BasicDBObject("nombre", ant.get("nombre").toString())
                .append("descripcion", ant.get("descripcion").toString())
                .append("checkins", cant + 1)
                .append("fecha_inicio", ant.get("fecha_inicio").toString())
                .append("fecha_fin", ant.get("fecha_fin").toString())
                .append("url", ant.get("url").toString());
        coll.update(query, doc);

    }

}
