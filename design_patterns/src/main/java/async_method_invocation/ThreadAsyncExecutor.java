package async_method_invocation;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadAsyncExecutor implements AsyncExecutor {

    private static final AtomicInteger index = new AtomicInteger(0);

    @Override
    public <T> AsyncResult<T> start(Callable<T> task) {
        return start(task, null);
    }

    @Override
    public <T> AsyncResult<T> start(Callable<T> task, AsyncCallback<T> callback) {
        CompletableResult<T> completableResult = new CompletableResult<>(callback);

        new Thread(() -> {
            try {
                completableResult.setValue(task.call());
            } catch (Exception exception) {
                completableResult.setException(exception);
            }
        }, "executor-" + index.incrementAndGet()).start();

        return completableResult;
    }

    @Override
    public <T> AsyncResult<T> end(AsyncResult<T> asyncResult) {
        return null;
    }

    public static final class CompletableResult<T> implements AsyncResult<T> {

        public static final int RUNNING = 1;
        public static final int FAILED = 2;
        public static final int COMPLETED = 3;

        volatile int state = RUNNING;

        final Object lock;
        Optional<AsyncCallback<T>> callback;

        T value;
        Exception exception;

        public CompletableResult(AsyncCallback<T> callback) {
            this.lock = new Object();
            this.callback = Optional.ofNullable(callback);
        }

        public void setValue(T value) {
            this.value = value;
            this.state = COMPLETED;
            this.callback.ifPresent(task -> task.onComplete(this.value, Optional.empty()));
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        public void setException(Exception exception) {
            this.exception = exception;
            this.state = FAILED;
            this.callback.ifPresent(task -> task.onComplete(null, Optional.of(this.exception)));
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        @Override
        public boolean isCompleted() {
            return state > RUNNING;
        }

        @Override
        public T getValue() throws ExecutionException {
            if (state == COMPLETED)
                return this.value;
            else if (state == FAILED)
                throw new ExecutionException(exception);
            else
                throw new IllegalStateException("Execution not completed yet");
        }

        @Override
        public void await() throws InterruptedException {
            synchronized (lock) {
                while (!isCompleted()) {
                    lock.wait();
                }
            }
        }
    }
}
