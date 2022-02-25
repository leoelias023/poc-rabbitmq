package br.com.example.core.consumer;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class DefaultConsumer implements Consumer {

    private static DefaultConsumer instance;
    private final Channel channel;

    private DefaultConsumer(Channel channel) {
        this.channel = channel;
    }

    public static synchronized DefaultConsumer getInstance(Channel channel) {
        if (Objects.isNull(instance)) {
            instance = new DefaultConsumer(channel);
        }

        return instance;
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("[COSUME OK]");
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        System.out.println("[CANCEL OK]");
    }

    @Override
    public void handleCancel(String consumerTag) {
        System.out.println("[CANCEL]");
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        System.out.println("[SHUTDOWN SIGNAL]");
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        System.out.println("[RECOVER OK]");
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
        throws IOException {
        try {
            System.out.printf(
                "[RECEIVE] Exchange: %s | %s%n",
                envelope.getExchange(), new String(body, StandardCharsets.UTF_8)
            );
        } finally {
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    }
}
