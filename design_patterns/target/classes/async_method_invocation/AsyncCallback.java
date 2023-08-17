package async_method_invocation;

import java.util.Optional;
import java.util.concurrent.Callable;

@FunctionalInterface
public interface AsyncCallback<T> {
   void onComplete(T value, Optional<Exception> exception);
}
