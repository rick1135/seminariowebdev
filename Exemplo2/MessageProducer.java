/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplo2;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author Rick
 */

@ApplicationScoped
public class MessageProducer{

    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/myQueue")
    private Queue queue;

    public void sendMessage(String messageText) {
        try (JMSContext context = connectionFactory.createContext()) {
            context.createProducer().send(queue, messageText);
        }
    }
}
