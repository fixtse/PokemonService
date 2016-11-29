/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.clases;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 *
 * @author fixt
 */
public class Servidor {
    
    private static ActiveMQConnectionFactory connectionFactory;
    private static Connection connection;
    private static Session session;
    private static Destination destination;
    private static boolean transacted = false;
    
    public boolean enviarSuministro(Suministro sum){
        try {
			connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnection.DEFAULT_USER,
			ActiveMQConnection.DEFAULT_PASSWORD,
			ActiveMQConnection.DEFAULT_BROKER_URL);
			
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(transacted,Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("Cola_Consumos");
			
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
//			TextMessage message = session.createTextMessage("<cliente><nombre>Joshep Aliaga</nombre></cliente>");			
//			//Enviamos un mensaje
//			producer.send(message);
			String msg = sum.getNum()+"+"+sum.getConsumo()+"."+sum.getFecha();
			javax.jms.Message message2 = session.createObjectMessage(msg);

			//Enviamos otro mensaje
			producer.send(message2);
			transacted = true;
			//System.out.println("Se enviaron 2 mensajes...");
		} 
		catch (JMSException e) {
			System.out.print(e);
		}
        return transacted;
    }
    
}
