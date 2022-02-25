package br.com.example.core;

import br.com.example.config.ConnectionManager;
import br.com.example.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    static Connection connection;
    static Channel channel;

    static {
        try {
            connection = ConnectionManager.getConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String queue, String message) throws IOException {
        channel.queueDeclare(queue, RabbitMQConfig.DURABLE, RabbitMQConfig.EXCLUSIVE, RabbitMQConfig.AUTO_DELETE, null);
        channel.basicPublish(RabbitMQConfig.EXCHANGE, queue, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        System.out.printf(
            "[SEND] Queue: %s | %s%n",
            queue, message
        );
    }
}
