package producer_consumer;

public interface Timer {
    static void sleep(float time) {
        try {
            Thread.sleep((long) (time * 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
