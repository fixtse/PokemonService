package pe.edu.clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Message;
import javax.jms.*;
import javax.jms.MessageListener;
import pe.edu.mongo.UsuarioDAO;

public class Consumer implements MessageListener {
	
	public void onMessage(Message arg0) {

		try {
			if (arg0 instanceof TextMessage) {
				System.out.println("Mensaje Recibido = "+ ((TextMessage)arg0).getText());
			} 
			else if (arg0 instanceof ObjectMessage) {
                                int pos1, pos2, pos3;
				System.out.println("Mensaje Recibido : " +((ObjectMessage)arg0).getObject());
                                String meng = ((ObjectMessage)arg0).getObject().toString();
                                
                                pos1 = meng.indexOf("+");
                                pos2 = meng.indexOf(".");
                                pos3 = meng.length();
                                
                                Suministro sum = new Suministro();
                                sum.setNum(meng.substring(0, pos1));
                                sum.setConsumo(Integer.valueOf(meng.substring(pos1+1, pos2)));
                                sum.setFecha(meng.substring(pos2+1, pos3));
                                UsuarioDAO user = new UsuarioDAO();
                                user.pasarSuministor(sum);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}

   
