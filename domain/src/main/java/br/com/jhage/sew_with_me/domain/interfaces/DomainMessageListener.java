package br.com.jhage.sew_with_me.domain.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

/***
 * 
 * @author Alexsander Melo
 * @since 31/03/2024
 *
 */

@Component
public class DomainMessageListener {
	
	@Autowired
    private AmazonSQS amazonSQS;
	
	
	@SqsListener("YOUR_QUEUE_NAME_HERE")
    public void receiveMessage(Message message) {
        try {
            // Process the message
            System.out.println("Received message: " + message.getBody());
            // Acknowledge the message to remove it from the queue
            amazonSQS.deleteMessage("YOUR_QUEUE_URL_HERE", message.getReceiptHandle());
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }

}
