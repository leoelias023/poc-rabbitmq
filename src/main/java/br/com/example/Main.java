package br.com.example;


import br.com.example.core.Consumer;
import br.com.example.core.Producer;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Thread(Main::produce).start();
        TimeUnit.MILLISECONDS.sleep(10000);
        new Thread(Main::consume).start();
    }

    private static void produce() {
        try {
            Producer.sendMessage("example_queue", "Hello World!");
            TimeUnit.MILLISECONDS.sleep(1000);
            produce();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void consume() {
        try {
            Consumer.receiveMessage("example_queue");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
