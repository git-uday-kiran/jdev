package draw.bytes;

import org.w3c.dom.Node;

import java.util.*;

public class DailyProblem {

    public static void main(String[] args) {
        System.out.println("Hey.....");

        int[] ints = {1, 5, 2};

        System.out.println(PredictTheWinner(ints));
    }

    public static boolean PredictTheWinner(int[] nums) {
        Integer[][][] dp = new Integer[nums.length][nums.length][2];
        solve(nums, dp, Person.A, 0, nums.length - 1);
        print(dp);
        return dp[0][nums.length - 1][Person.A.ordinal()] >= dp[0][nums.length - 1][Person.B.ordinal()];
    }

    public static void solve(int[] nums, Integer[][][] dp, Person move, int lo, int hi) {
        if (lo == hi) {
            if (lo == 1) {
                System.out.println("move:  " + move + " val: " + nums[lo]);
            }

            if (move.equals(Person.A)) {
                dp[lo][hi][Person.A.ordinal()] = nums[lo];
                dp[lo][hi][Person.B.ordinal()] = nums[0];
            } else {
                dp[lo][hi][Person.B.ordinal()] = nums[lo];
                dp[lo][hi][Person.A.ordinal()] = nums[lo];
            }
            return;
        }
        if (dp[lo][hi][move.ordinal()] != null) return;

        Person nextMove = move.equals(Person.A) ? Person.B : Person.A;

//        System.out.println("move: " + move.ordinal() + ", nextMove: " + nextMove.ordinal());
        solve(nums, dp, nextMove, lo + 1, hi);
        solve(nums, dp, nextMove, lo, hi - 1);

        int leftA = nums[lo] + dp[lo + 1][hi][Person.A.ordinal()];
        int rightB = dp[lo + 1][hi][Person.B.ordinal()];

        int rightA = nums[hi] + dp[lo][hi - 1][Person.A.ordinal()];
        int leftB = dp[lo][hi - 1][Person.A.ordinal()];

        if (leftA >= rightA) {
            doIt(dp, move, lo, hi, leftA, rightB);
        } else {
            doIt(dp, move, lo, hi, rightA, leftB);
        }

    }

    private static void doIt(Integer[][][] dp, Person move, int lo, int hi, int max, int min) {
        if (move.equals(Person.A)) {
            dp[lo][hi][Person.A.ordinal()] = max;
            dp[lo][hi][Person.B.ordinal()] = min;
        } else {
            dp[lo][hi][Person.B.ordinal()] = max;
            dp[lo][hi][Person.A.ordinal()] = min;
        }
    }

    public static void print(Integer[][][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j][0] + " ");
            }
            System.out.println();
        }

        System.out.println("------------------------------");

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j][0] + " ");
            }
            System.out.println();
        }
    }

    public enum Person {
        A, B;
    }

}