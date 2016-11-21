/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 *
 * @author fixt
 */
public class Coneccion {
    
    private static MongoClient client = null;

public static DB connectToMongo() throws Exception {
    
    String textUri = "mongodb://fixt:ulima@ds047602.mlab.com:47602/bd_produccion"; 
    
    if (null != client) {
        return client.getDB("bd_produccion");
    }       
    MongoClientURI uri = new MongoClientURI(textUri);
    client = new MongoClient(uri);
    //client = new MongoClient("localhost", 27017);
    return client.getDB("bd_produccion");    
        
    
    
        
}
    
}
