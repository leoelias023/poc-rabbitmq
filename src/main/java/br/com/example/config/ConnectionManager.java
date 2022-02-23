package br.com.example.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class ConnectionManager {

    private ConnectionManager() {}

    private static Connection connection;

    public static synchronized Connection getConnection() throws IOException, TimeoutException {

        if (Objects.isNull(connection)) {

            var connectionFactory = new ConnectionFactory();

            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);

            connection = connectionFactory.newConnection("Worker - Buster");
        }


        return connection;
    }

}
