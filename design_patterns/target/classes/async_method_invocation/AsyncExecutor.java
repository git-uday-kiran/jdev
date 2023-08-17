package async_method_invocation;

import java.util.concurrent.Callable;

public interface AsyncExecutor{

    <T> AsyncResult<T> start(Callable<T> task);

    <T> AsyncResult<T> start(Callable<T> task, AsyncCallback<T> callback);

    <T> AsyncResult<T> end(AsyncResult<T> asyncResult);
}
