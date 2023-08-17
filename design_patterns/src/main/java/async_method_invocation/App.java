package async_method_invocation;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.Callable;

public class App {

    public static final Set<Integer> SET_INSTANCE = new HashSet<>();

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

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] arr : prerequisites) {
            int u = arr[0], v = arr[1];
            if (!map.containsKey(u))
                map.put(u, new HashSet<>());
            map.get(u).add(v);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!canTakeCourse(map, new Boolean[numCourses],i))
                return false;
        }

        return true;
    }

    public static boolean canTakeCourse(Map<Integer, Set<Integer>> edges, Boolean[] visit, int course) {
        if (visit[course] != null)
            return visit[course];
        visit[course] = false;
        for (int childCourse : edges.getOrDefault(course, SET_INSTANCE)){
            if (!canTakeCourse(edges, visit, childCourse))
                return false;
        }
        return visit[course] = true;
    }

}
