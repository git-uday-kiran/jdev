package producer_consumer;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer<T> {

    private AtomicBoolean shouldProduce;
    private AtomicBoolean producingInProcess;
    private BlockingQueue<T> producingQueue;

    private Producer(BlockingQueue<T> producingQueue) {
        this.producingQueue = producingQueue;
        this.shouldProduce = new AtomicBoolean(false);
        this.producingInProcess = new AtomicBoolean(false);
    }

    private synchronized void produce(Collection<T> elements) {
        if (producingInProcess.getAcquire()) throw new IllegalStateException("already in process of producing state");
        shouldProduce.setRelease(true);
        producingInProcess.setRelease(true);

        new Thread(() -> {
            elements.stream()
                    .takeWhile(e -> shouldProduce.getAcquire())
                    .forEach(e -> {
                        try {
                            producingQueue.put(e);
                            System.out.println("Produced: " + e);
                            Timer.sleep(1f);
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
            shouldProduce.setRelease(false);
            producingInProcess.setRelease(false);
            System.out.println("Stopped producing....");
        }).start();
    }

    public void startProducing(Collection<T> elements) {
        produce(elements);
    }

    public void stopProducing() {
        shouldProduce.setRelease(false);
    }

    public static <T> Producer<T> getInstance(BlockingQueue<T> producingQueue) {
        Producer<T> producer = new Producer<>(producingQueue);
        return producer;
    }

}
