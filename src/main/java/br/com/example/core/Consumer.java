package br.com.example.core;

import br.com.example.config.ConnectionManager;
import br.com.example.config.RabbitMQConfig;
import br.com.example.core.consumer.DefaultConsumer;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

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

    public static void receiveMessage(String queue) throws IOException {
        channel.queueDeclare(queue, RabbitMQConfig.DURABLE, RabbitMQConfig.EXCLUSIVE, RabbitMQConfig.AUTO_DELETE, null);
        channel.basicConsume(queue, RabbitMQConfig.AUTO_ACK, DefaultConsumer.getInstance(channel));
    }
}
