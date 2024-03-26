import java.util.ArrayList;
class Recursion2 {
	public static void main(String[] args) {
		System.out.println(searchInRotatedArrays(new int[]{6,7,8,9,1,2,3,4,5}, 0, 8, 8));
		
		
	}
	// ALWAYS DIVIDE THE PROBLEM INTO SMALLER PROBLEMS
	static boolean sortedArray (int arr[] , int i) {
		if (i == arr.length - 1) return true;
		// if (arr[i] > arr[i+1]) return false;
		// return sortedArray(arr, i + 1);
		return arr[i] < arr[i+1] && sortedArray(arr, i + 1);
	}

	static boolean linearSearch(int[] arr, int i, int target) {
		if (i == arr.length) return false;
		// if(arr[i] == target) return i;
		// return linearSearch(arr, i + 1, target);
		return arr[i] == target || linearSearch(arr, i + 1, target);
	}

	static ArrayList findAllIndex(int[] arr, int i, int target) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		if (i == arr.length) return result;
		if(arr[i] == target) result.add(i);
		ArrayList<Integer> ans = findAllIndex(arr, i + 1, target);
		result.addAll(ans);
		return result;
	}

	static int rotatedBinarySearch(int[] arr, int start, int end) { // return the index of pivot element 
		if (start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if (arr[mid] > arr[mid + 1]) {
			return mid;
		} else if (arr[start] > arr[mid]) {
			return rotatedBinarySearch(arr, start, mid - 1);
		}
		return rotatedBinarySearch(arr, mid + 1, end);
	}

	static int searchInRotatedArrays(int[] arr, int start, int end, int target) {
		if (start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if(arr[mid] == target) {
			return mid;
		}
		if (arr[start] <= arr[mid]) {
			if (target < arr[mid]) {
				return searchInRotatedArrays(arr, start, mid - 1, target);
			} else {
				return searchInRotatedArrays(arr, mid + 1, end, target);
			}
		}
		if (target > arr[mid] && target <= arr[end]) {
			return searchInRotatedArrays(arr, mid + 1, end, target);
		}
		return searchInRotatedArrays(arr, start, mid - 1, target);
	}
}