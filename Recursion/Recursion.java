class Recursion{
	public static void main(String[] args) {
		// trees, linked list, Dynamic programming, heaps, graphs, traversals, all uses recursion
		// Every recursie function consist of 3 parts, terminating condition, body and a recursive call
		// For each call a seperate space in stack in created for that function, considering that call a seperate function in stack.
		// System.out.println(binarySearch(new int[]{2,3,5,6,7,8,23,45,76,89}, 0, 9, 24));
		System.out.println(countZeros(540020100, 0));
		
	}

	static int fibonachi(int n) {
		if (n < 2) {
			return n;
		}
		return fibonachi(n - 1) + fibonachi(n - 2);
	}
	// How to understand and spproach a problem
	// i. identify if you can break down problem into smaller problem
	// ii. write down recurrence relation if needed
	// iii. draw recursive tree
			// i.	see the flow of unctions, how they are getting in stack
			// ii. identify on left and right tree which is going to get executed first
			// iii. draw tree and pointer again and again 
			// iv. use debugger to see the steps
	// iv. see how the values and what type of value are returned at each step, see where the function calls are comeout 


	/* Variables in Recursion
	Arguments (Parameter)
	return type
	body of the function */
	
	/*Type of recurrence relation 
	i. Linear RR ---> fibonachi number
	ii. Divide and conquer RR ---> Binary search (Dividing by a factor)*/

	static int binarySearch(int[] arr, int start, int end, int target) {
		int mid = start + (end - start) / 2;
		if (arr[mid] == target) return mid;
		else if (start > end) return -1;
		else if (arr[mid] < target) start = mid + 1;
		else end = mid - 1;
		return binarySearch(arr, start, end, target);
	} 

	static void printNto1(int n){
		System.out.println(n); // the statement before the function calls are executed before the function meets its terminating condition
		if (n == 1) return; // --> This is terminating condition
		printNto1(n-1); // -----> This is called function call

	}

	static void print1toN(int n){
		if (n == 0) return;
		print1toN(n-1);
		System.out.println(n); // the statement after the function calls are executed after the function meets its terminating condition
	}

	static int product1ToN(int n) {
		if (n == 1) return 1;
		return n * product1ToN(n-1);
	}

	static int sum1ToN(int n) {
		if (n == 1) return 1;
		return n + sum1ToN(n-1);
	}

	static int sumOfDigits(int n) { 
		if (n == 0) {
			return 0;
		}
		return n%10 + sumOfDigits(n/10);
	}

	static int prodOfDigits(int n) {
		if (n == 0) {
			return 1;
		}
		return n%10 * prodOfDigits(n/10);
	}

	static int reverseOfDigits(int n, int rev) { // rev is initially at 0
		if (n == 0) {
			return rev/10;
		}
		rev += n%10;
		rev *= 10;
		return reverseOfDigits(n/10, rev);
	}

	static boolean palindrome(int n) {
		return n == reverseOfDigits(n, 0);
	}

	static int countZeros(int n, int count) {
		if (n == 0){
			return count;
		}
		if(n % 10 == 0) count++;
		return countZeros(n/10, count);
	}

	// https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/description/
	int helperNumberOfSteps(int n,int m) {
        if (n == 0) return m;
        if(n % 2 == 1) return helperNumberOfSteps(n - 1, m + 1);
        return helperNumberOfSteps(n / 2, m + 1);
    }
    public int numberOfSteps(int num) { // Input: num = 14, Output: 6
        return helperNumberOfSteps(num, 0);
        
    }
}