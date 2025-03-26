import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

class DP {
    // Those who cannot remember the past are condemned to repeat it
    /*
     * Two methods to solve DP
     * i. Tabulation -> Bottom up DP
     * ii. Memoization -> Top down DP
     * 
     * 
     * In fibonacci numbers we computer the fib number using numbr, but suppose we
     * are to find fib numbr at index 5, the recursion will call the fib(4) and
     * fib(3), in some cases the resurion will be called twice or more for fib(2),
     * if our recursion has to computer one problem more then 1 time this is called
     * overlapping sub problems
     * 
     * so in this case we use memoization, we store the value of sub problems in
     * some map/ table, we can use 1D array, because we are dealing ith just a
     * single number, also storing the value will save our program to compute a same
     * problem again and again
     * 
     * -------------- What is Memoization????
     * for fib(5);
     * we will move from fib(0) to atmost fib(5) to caculate the fibonacci number of
     * 5, we donot need fibonacci number of 6 to calculate fibonacci number of 5,
     * its a common sense right?
     * 
     * so for fib(5) we make an array of length 6 (0 to 5)
     * we keep computing the subproblems and store the answer in array, so the next
     * time we get encounter with the same sub problem we simply return the value
     * from the array instead of computing and finding the solution again
     * 
     */

    int fibonacci(int n) { // A normal fibonacci recursion code
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);

    }

    static int fibonacci2(int n) { // A fibonacci recursion solution using dynamic programming, we store the
                                   // previously computed answers in a array, and if that subproblem appears again
                                   // we j return the answer from the array instead of computing it again
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return fibonacci(n, dp);

    }

    static int fibonacci(int n, int[] dp) { // converting a recursion problem to Dynamic programming solution (TC :
                                            // O(n), SC : O(n))
        if (n <= 1) {
            return n;
        }
        if (dp[n] != -1) { // second step is to check if the answer was already computed we simple return
                           // that value
            System.out.println(n + " Before");
            return dp[n];
        }
        int sum = fibonacci(n - 1, dp) + fibonacci(n - 2, dp);
        dp[n] = sum; // first step is to store the answer in an array
        System.out.println(n + " After");
        return sum;
    }

    static int fibonacci3(int n) { // In tabulation form, using same TC and SC as above
        int dp[] = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];

    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // when we are to calculate all possible ways we use recursion
    // https://leetcode.com/problems/climbing-stairs/
    int climbStairs(int n, int[] dp) {
        if (n == 0)
            return 1;
        else if (n < 0)
            return 0;
        else if (dp[n] != -1)
            return dp[n];
        dp[n] = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
        return dp[n];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return climbStairs(n, dp);

    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/house-robber/description/
    int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length + 1];
        Arrays.fill(memo, -1);
        return rob(nums, nums.length - 1);
    }

    private int rob(int[] nums, int i) {
        if (i < 0) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(rob(nums, i - 2) + nums[i], rob(nums, i - 1));
        memo[i] = result;
        return result;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/jump-game-ii/
    // Integer[] dp;
    // Integer helper(int[] arr,int start, int ind) {
    // if(start == arr.length - 1) return 0;
    // if(start >= arr.length || arr[start] == 0) return null;
    // if(dp[start] != null) return dp[start];
    // int min = arr.length + 1;
    // for(int i = start + 1; i < arr.length && i <= start + ind; i++) {
    // Integer temp = helper(arr, i, arr[i]);
    // if(temp != null) min = Math.min(min, temp);
    // }
    // dp[start] = min + 1;
    // return dp[start];

    // }
    int ans = 0;

    public int jump(int[] nums) {
        // if(nums.length == 1) return 0;
        // int n = nums.length;
        // dp = new Integer[n];
        // return helper(nums, 0, nums[0]);
        int i = 0;
        while (i < nums.length - 1) {
            i = helperJump(nums, i, nums[i]);
        }
        return ans;
    }

    int helperJump(int[] arr, int a, int b) {
        ans++;
        if (a + b >= arr.length - 1)
            return arr.length;
        int max = 0;
        int pos = 0;
        for (int i = a; i <= a + b; i++) {
            int c = arr[i] + i;
            if (c > max) {
                max = c;
                pos = i;
            }
        }
        return pos;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-square-streak-in-an-array/description/
    HashMap<Long, Integer> map;
    HashSet<Long> set;

    int check(long n) {
        if (!set.contains(n))
            return 0;
        if (map.containsKey(n))
            return map.get(n);

        int result = check(n * n) + 1;
        map.put(n, result);
        return result;
    }

    public int longestSquareStreak(int[] nums) {
        set = new HashSet<>();
        map = new HashMap<>();
        for (int i : nums)
            set.add((long) i);

        int max = -1;
        for (long i : set) {
            int temp = check(i);
            if (temp >= 2) {
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static void main(String[] args) {
        int n = fibonacci2(5);
        // System.out.println(n);

    }
}