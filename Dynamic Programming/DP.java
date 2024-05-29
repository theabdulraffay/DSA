import java.util.Arrays;
class DP {
	// Those who cannot remember the past are condemned to repeat it 
	/*
	Two methods to solve DP
	i. Tabulation -> Bottom up DP
	ii. Memoization -> Top down DP


	In fibonacci   numbers we computer the fib number using numbr, but suppose we are to find fib numbr at index 5, the recursion will call the fib(4) and fib(3), in some cases the resurion will be called twice or more for fib(2), if our recursion has to computer one problem more then 1 time this is called overlapping sub problems
	
	so in this case we use memoization, we store the value of sub problems in some map/ table, we can use 1D array, because we are dealing ith just a single number, also storing the value will save our program to compute a same problem again and again
	
	-------------- What is Memoization????
	for fib(5);
	we will move from fib(0) to atmost fib(5) to caculate the fibonacci   number of 5, we donot need fibonacci   number of 6 to calculate fibonacci   number of 5, its a common sense right?

	so for fib(5) we make an array of length 6 (0 to 5)
	we keep computing the subproblems and store the answer in array, so the next time we get encounter with the same sub problem we simply return the value from the array instead of computing and finding the solution again

	*/

	int fibonacci (int n) { //A normal fibonacci   recursion code 
		if(n <= 1) {
			return n;
		}
		return fibonacci (n - 1) + fibonacci (n - 2);

	}

	static int fibonacci2(int n) { //A fibonacci recursion solution using dynamic programming, we store the previously computed answers in a array, and if that subproblem appears again we j return the answer from the array instead of computing it again
		int[] dp = new int[n + 1];
		Arrays.fill(dp, -1);
		return fibonacci(n, dp);

	}

	static int fibonacci (int n, int[] dp) { // converting a recursion problem to Dynamic programming solution
		if(n <= 1) {
			return n;
		}
		if(dp[n] != -1) { // second step is to check if the answer was already computed we simple return that value
			System.out.println(dp[n]);
			return dp[n];
		}
		int sum = fibonacci (n - 1, dp) + fibonacci (n- 2, dp);
		dp[n] = sum; // first step is to store the answer in an array
		return sum;
	}


	public static void main(String[] args) {
		int n = fibonacci2(5);
		// System.out.println(n);
		
	}
}