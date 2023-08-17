package draw.bytes;

import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Main");

        /**
         *
         * @ a A . . . # #
         * # . # . # A . b
         * # . . . # . # .
         */

        Thread.sleep(100000);

    }

    public static int shortestPathAllKeys(String[] grid) {
        final Pair[][] dp = new Pair[grid.length][grid[0].length()];
        boolean[][] visit = new boolean[grid.length][grid[0].length()];
        int minimumSteps = Integer.MAX_VALUE;

        Comparator<Pair> comparator = (a, b) -> {
            if (a.keys.size() == b.keys.size()) {
                return Integer.compare(a.steps, b.steps);
            } else {
                return Integer.compare(a.keys.size(), b.keys.size());
            }
        };
        PriorityQueue<Pair> queue = new PriorityQueue<>(comparator);


        int[] start = getStartingPoint(grid);
        dp[start[0]][start[1]] = new Pair(start, 0, new HashSet<>());

        while (not(queue.isEmpty())) {
            Pair curPair = queue.poll();
            int curRow = curPair.node[0];
            int curCol = curPair.node[1];

            for (int[] child : getChildren(grid, visit, curRow, curCol)) {
                int childRow = child[0], childCol = child[1];
                Pair childPair = dp[childRow][childCol];

                if (grid[childRow].charAt(childCol) == '.') {

                    if (childPair.keys.size() == curPair.keys.size()) {
                        if (curPair.steps + 1 < childPair.steps) {
                            dp[childRow][childCol] = new Pair(child, curPair.steps + 1, curPair.keys);
                        }
                    }

                }
                if (not(grid[childRow].charAt(childCol) == '#')) {

                } else {

                }

            }

        }

        return 0;
    }

    public static boolean not(final boolean condition) {
        return !condition;
    }

    public static List<int[]> getChildren(final String[] grid, final boolean[][] visit, int row, int col) {
        List<int[]> children = new ArrayList<>();

        if (row != 0 && not(visit[row - 1][col]))
            children.add(new int[]{row - 1, col});
        if (col != grid[0].length() - 1 && not(visit[row][col + 1]))
            children.add(new int[]{row, col + 1});

        if (row != grid.length - 1 && not(visit[row + 1][col]))
            children.add(new int[]{row + 1, col});
        if (col != 0 && not(visit[row][col - 1]))
            children.add(new int[]{row, col - 1});

        return children;
    }

    public static int[] getStartingPoint(final String[] grid) {
        for (int i = 0; i < grid.length; i++) {
            int index = grid[i].indexOf('@');
            if (index != -1)
                return new int[]{i, index};
        }
        throw new RuntimeException("starting point not found");
    }

    private static class Pair {
        public final int[] node;
        public int steps;
        public Set<Integer> keys;

        public Pair(final int[] node, final int steps, final Set<Integer> keys) {
            this.keys = keys;
            this.node = node;
            this.steps = steps;
        }
    }
}
