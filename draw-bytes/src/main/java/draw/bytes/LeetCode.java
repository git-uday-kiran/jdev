package draw.bytes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LeetCode {

    public static int count = 10000;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello....");
        Runtime runtime = Runtime.getRuntime();

        totalMemory();
        availableMemory();
        usageMemory();
        System.out.println("---------------------------");

        AtomicInteger threadCount = new AtomicInteger(0);

//        while (true) {
//            new Thread(() -> {
//                int count = threadCount.incrementAndGet();
//                System.out.println("Count = " + count);
//                LockSupport.park();
//            }).start();
//        }

//        System.out.println("\n--------------------------");
//        System.out.println("After thread....");
//        totalMemory();
//        availableMemory();
//        usageMemory();


    }

    public static void maxMemory() {
        System.out.println("max memory %d mb".formatted((Runtime.getRuntime().maxMemory() / (1024 * 1024))));
    }

    public static void availableMemory() {
        System.out.println("available memory %d mb".formatted((Runtime.getRuntime().freeMemory() / (1024 * 1024))));
    }

    public static void totalMemory() {
        System.out.println("total memory %d mb".formatted((Runtime.getRuntime().totalMemory() / (1024 * 1024))));
    }

    public static void usageMemory() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("usage memory %d mb".formatted((runtime.totalMemory() - runtime.freeMemory()) / (1024 * 1024)));
    }

}