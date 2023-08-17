package producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer<T> {

    private AtomicBoolean startedConsuming;
    private AtomicBoolean shouldConsume;
    private BlockingQueue<T> consumingQueue;

    public Consumer(BlockingQueue<T> consumingQueue) {
        this.consumingQueue = consumingQueue;
        this.shouldConsume = new AtomicBoolean(false);
        this.startedConsuming = new AtomicBoolean(false);
    }

    private synchronized void consume() {
        if (startedConsuming.getAcquire())
            throw new IllegalStateException("already started consuming");
        shouldConsume.setRelease(true);
        startedConsuming.setRelease(true);

        new Thread(() -> {
            while (shouldConsume.getAcquire()) {
                try {
                    T element = consumingQueue.take();
                    System.out.println("Consumed: " + element);
                    Timer.sleep(1f);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            shouldConsume.setRelease(false);
            startedConsuming.setRelease(false);
        }).start();
    }

    public void startConsuming() {
        consume();
    }

    public void stopConsuming() {
        shouldConsume.setRelease(false);
    }

    public static <T> Consumer<T> getInstance(BlockingQueue<T> consumingQueue) {
        Consumer<T> consumer = new Consumer<T>(consumingQueue);
        return consumer;
    }
}
