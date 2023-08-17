package draw.bytes;

import java.lang.reflect.InvocationTargetException;

public class RequestContext {

    private static final Class<? extends RequestContext> CONTEXT_CLASS = RequestContext.class;
    private static final ThreadLocal<RequestContext> THREAD_LOCAL = new ThreadLocal<RequestContext>() {
        @Override
        protected RequestContext initialValue() {
            try {
                RequestContext requestContext = CONTEXT_CLASS.getConstructor(String.class).newInstance(Thread.currentThread().getName());
                return requestContext;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    };


    public String name;

    public RequestContext(String name) {
        this.name = name;
    }

    public static RequestContext currentRequestContext() {
        return THREAD_LOCAL.get();
    }

    public static void runAsync(Runnable runnable) {
        try {
            new Thread(runnable).start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
