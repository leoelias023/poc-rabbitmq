package br.com.example.config;

public class RabbitMQConfig {

    /** Default exchange for connection (Direct) */
    public static String EXCHANGE = "";

    /** If the queue will be present with the restart of the server. */
    public static boolean DURABLE = true;

    /** If queue will be exclusive of the current connection. */
    public static boolean EXCLUSIVE = false;

    /** Queue will automatically be deleted if unused. */
    public static boolean AUTO_DELETE = false;
}
