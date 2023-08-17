package async_method_invocation;

import java.sql.Time;
import java.util.concurrent.Callable;

public class App {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Helooo...");
        ThreadAsyncExecutor executor = new ThreadAsyncExecutor();

        AsyncCallback<String> callback = (value, ex) -> {
            System.out.println("executing the call back .... ");
        };

        Callable<String> block = () -> {
            System.out.println("Executing the block... ");
            return "my block string";
        };


        executor.start(block, callback).await();

    }
}
