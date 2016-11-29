/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.clases;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author fixt
 */
public class Cliente {
    
    private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
    private static Session session;
    private static Destination destination;
    private boolean transacted = false;
    
    public boolean pasarConsumos(){
        
        try {
			connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			ActiveMQConnection.DEFAULT_BROKER_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			
			session = connection.createSession(false,
			Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("Cola_Consumos");
			MessageConsumer consumer = session.createConsumer(destination);
			
			Consumer myConsumer = new Consumer();
			consumer.setMessageListener(myConsumer);
			Thread.sleep(3000);
			session.close();
			connection.close();
                        transacted = true;
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
        return transacted;
    }
    
}
