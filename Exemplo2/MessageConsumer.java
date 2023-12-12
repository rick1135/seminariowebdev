/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplo2;
import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

/**
 *
 * @author Rick
 */

@MessageDriven
public class MessageConsumer implements MessageListener{

    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/myQueue")
    private Queue queue;

    @Override
    public void onMessage(Message message) {
        try (JMSContext context = connectionFactory.createContext()) {
            String receivedMessage = message.getBody(String.class);
            System.out.println("Messagem recebida: " + receivedMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}


