package diy.alhuck.labs.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {
  public static void main (String[] args)  throws Exception {
	  ConnectionFactory connectionFactory = new ConnectionFactory();
	  connectionFactory.setHost("localhost");
	  Connection connection = connectionFactory.newConnection();
	  Channel channel = connection.createChannel();
	  channel.queueDeclare("queue", false, false, false, null);
	  System.out.println("Wating for message!");
	  
	  DeliverCallback deliverCallback = (consumerTag, delivery) -> {
		  String message = new String(delivery.getBody(), "UTF-8");
		  System.out.println("Received" + message );
	  };
	  channel.basicConsume("queue", true, deliverCallback, consumerTag -> {});	
  }
}
