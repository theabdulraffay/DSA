class Recursion{
	public static void main(String[] args) {
		// trees, linked list, Dynamic programming, heaps, graphs, traversals, all uses recursion
		// Every recursie function consist of 3 parts, terminating condition, body and a recursive call
		// For each call a seperate space in stack in created for that function, considering that call a seperate function in stack.
		System.out.println(binarySearch(new int[]{2,3,5,6,7,8,23,45,76,89}, 0, 9, 24));
		
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
			/// i.	see the flow of unctions, how they are getting in stack
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

}