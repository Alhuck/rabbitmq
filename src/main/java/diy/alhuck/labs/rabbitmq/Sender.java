package diy.alhuck.labs.rabbitmq;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		try(Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel()) {
			
			channel.queueDeclare("queue", false, false, false, null);
			String message = "Hello Chennai! here we go!";
			channel.basicPublish("", "queue", null, message.getBytes());
			System.out.println("Message sent");
		}

	}

}
