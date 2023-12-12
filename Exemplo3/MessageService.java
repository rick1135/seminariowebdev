/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exemplo3;
import javax.jms.*;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Rick
 */
@ApplicationScoped
public class MessageService {
    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/myQueue")
    private Queue queue;

    public void sendMessage(String messageText, String messageType, int messagePriority) {
        try (JMSContext context = connectionFactory.createContext()) {
            // criand uma mensagem de texto
            TextMessage textMessage = context.createTextMessage(messageText);

            // adicionando cabeçalhos
            textMessage.setStringProperty("MessageType", messageType);

            // definindo a prioridade 
            textMessage.setJMSPriority(messagePriority);

            // enviando a mensagem pra fila
            context.createProducer().send(queue, textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage() {
        try (JMSContext context = connectionFactory.createContext()) {
            // criando consumidor pra fila
            JMSConsumer consumer = context.createConsumer(queue);

            // recebendo a mensagem
            TextMessage receivedMessage = (TextMessage) consumer.receive();

            // lendo
            String messageType = receivedMessage.getStringProperty("MessageType");
            int messagePriority = receivedMessage.getJMSPriority();

            // imprimindo as informações da mensagem
            System.out.println("Mensagem do tipo: " + messageType);
            System.out.println("Corpo: " + receivedMessage.getText());
            System.out.println("Prioridade: " + messagePriority);

            return receivedMessage.getText();
        } catch (JMSException e) {
            e.printStackTrace();
            return null;
        }
    }
}
