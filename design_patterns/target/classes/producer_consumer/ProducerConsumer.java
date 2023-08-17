package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Stream;


public class ProducerConsumer {

    public static void main(String[] args) {
        System.out.println("Hello..........");
        System.out.println(Thread.currentThread());

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        List<Integer> list = Stream.iterate(1, integer -> integer + 1).limit(20).toList();

        Producer<Integer> producer = Producer.getInstance(queue);
        producer.startProducing(list);

        Timer.sleep(15f);

        List<Integer> liist = new ArrayList<>();

        Consumer<Integer> consumer = Consumer.getInstance(queue);
        consumer.startConsuming();

    }
}
