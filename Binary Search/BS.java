import java.util.Arrays;
public class BS{
	public static void main(String[] args) {
		// int[] arr = {2,5,6,9,14,18,25,33,54,67,87};
		int[] arr = {1,0,1,1,1};
		int target = 4;
		// System.out.println(IndexFromInfiniteArray(arr, target));
		// System.out.println(index(arr, target));
		// System.out.println(Arrays.toString(firstNlast(arr,target)));
		System.out.println(peakIndexInMountainArray(arr));
		System.out.println(pivotOfDuplicatedRotatedArray(arr));
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------
	static int index(int[] n, int target){
		int start = 0;
		int end = n.length - 1;
		while(start <= end){
			// int mid = (start + end)/2;
			int mid = start + (end - start)/2;
			if(n[mid] == target){
				return mid;
			}else if(n[mid] > target){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		return -1;
	}

	static int index(int[] n, int target, int start, int end){
		while(start <= end){
			// int mid = (start + end)/2;
			int mid = start + (end - start)/2;
			if(n[mid] == target){
				return mid;
			}else if(n[mid] > target){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		return -1;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------
	// hep you find ceiling of a number or the number the is closest to the number (smallest number greater or eq to targert element)
	// This function will return the nearest number of the array where target can fit
	//https://leetcode.com/problems/find-smallest-letter-greater-than-target/description/
	static int ceiling(int[] arr, int target){
		int start = 0;
		int end = arr.length - 1;
		int mid = 0;
		while (start <= end){
			// mid = start + (end - start) / 2;
			mid = (start + end) / 2;
			if(arr[mid] <= target){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		return arr[start];
		//and for floor value return err[end] ---->> this will return the floor value of target element in array
	}



	// -------------------------------------------------------------------------------------------------------------------------------------------
	//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
	// nums = [5,7,7,8,8,10] , target = 8
	// this will give firt and last index of 8 in array
	static int[] firstNlast(int[] nums, int target){
		// Time complexity O(log N)
		int[] list = new int[2];
		int start = Occurance(nums, target, true);
		int end = Occurance(nums, target, false);
		list[0] = start;
		list[1] = end;
		return list;

		// This is also a possibility
		// Time Complexity O(N)

		// int[] list = {-1,-1};
		// int start = 0;
		// int end = nums.length -1;
		// int c = 0;
		// int m = 0;
		// while(start <= end){
		// 	if(nums[start] == target){
		// 		list[0] = start; 
		// 		c++;
		// 	}else{
		// 		start++;
		// 	}

		// 	if(nums[end] == target){
		// 		list[1] = end; 
		// 		m++;
		// 	}else{
		// 		end--;
		// 	}

		// 	if(c > 0 && m > 0){
		// 		return list;
		// 	}
		// }
		// return list;
	}

	static int Occurance(int[] n, int target, boolean firstOccurance){
		// This function will tell the first index of repeated element in a sorted list if boolean firstOccurance is true, other wise it will give last index, if element doesnt found this will return -1
		int start = 0;
		int end = n.length - 1;
		int ans = -1;
		while(start <= end){
			// int mid = (start + end)/2;
			int mid = start + (end - start)/2;
			if(n[mid] == target){
				ans = mid;
				if(firstOccurance){
					end = mid - 1;
				}else{
					start = mid + 1;
				}
			}else if(n[mid] > target){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		return ans;
	}

	// -------------------------------------------------------------------------------------------------------------------------------------------
	// This function will take an infinite array and then applying binary search to give the index of target element
	// A function is infinite where e doesnt know the length of the function, so we cannot use length function in the while loop
	static int IndexFromInfiniteArray(int[] arr, int target){
		int start = 0;
		int end = 1;
		// while(start <= end){
		// 	int mid = start + (end - start) / 2;
		// 	if(arr[end] < target){ // we will keep checking until the arr[end] becomes smaller then target, in this way we an index from where arr[end ] is larget then target we so get a start and end in array to search target, now we can apply BS in this specific area of infinit loop
		// 		start = end + 1;
		// 		end *= 2;				
		// 	}
		// 	else if(arr[mid] == target){
		// 		return mid;
		// 	}else if(arr[mid] < target){
		// 		start = mid + 1;
		// 	}else{
		// 		end = mid - 1;
		// 	}
		// }
		// return -1;
		// // No lenght function is taken in this function

		while(target > arr[end]){
			int temp = end + 1;
			end = end + (end - start + 1)*2;
			start = temp;
		}
		// System.out.println(start);
		// System.out.println(end);

		while(start <= end){
			// int mid = (start + end)/2;
			int mid = start + (end - start)/2;
			if(arr[mid] == target){
				return mid;
			}else if(arr[mid] > target){
				end = mid - 1;
			}else{
				start = mid + 1;
			}
		}
		return -1;
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------

	//PEAK IN A MOUNTAIN ARRAY
	// https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
	// https://leetcode.com/problems/find-peak-element/description/
	// The function will give the peak in an mountain array, als acalled bitonic array, in which numbers first increase and then decrease
	// arr = {0, 3, 4, 5, 7, 8, 9, 6, 4, 2, 1, 0} is a bitonic or mountain array, here 9 is the peak
	 static int peakIndexInMountainArray(int[] arr) {
	 	// time complexity is O(log N)
        int start = 0;
        int end = arr.length -1;
        while(start < end){
            int mid = start + (end - start) / 2;
            if(arr[mid] > arr[mid + 1]) end = mid;
            else start = mid + 1;
        }
        return start;

        // while(start <= end){
        //     int mid = start + (end - start) / 2;
        //     if(arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]){
        //         return mid;
        //     }
        //     else if(arr[mid - 1] < arr[mid]){
        //         start = mid;
        //     }else if(arr[mid + 1] < arr[mid]){
        //         end = mid;
        //     }
        // }
        // return -1;



	 	// time complexity is O(N)
        
        // while(start <= end){
        //     if(arr[start] < arr[end]){
        //         start++;
        //     }else if(arr[start] > arr[end]){
        //         end--;
        //     }else if(start == end){
        //         return start;
        //     }else if(arr[start] == arr[end]){
        //         start++;
        //         end--;
        //     }
        // }
        // return start;

    }
    
	// -------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/find-in-mountain-array/description/

	// -------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/search-in-rotated-sorted-array/description/
	// nums = {4, 5, 6, 7, 0, 1, 2} target = 0
	static int rotatedArray(int[] nums, int target){
		// int start = 0;
        // int end = nums.length - 1;
        // while(start <= end){
        //     int mid = start + (end - start) / 2;
        //     if(nums[mid] == target)return mid;
        //     else if(nums[start] <= nums[mid]){
        //         if(target >= nums[start] && target < nums[mid]){
        //             end = mid - 1;
        //         }else start = mid + 1;
        //     }else{
        //         if(target > nums[mid] && target <= nums[end]) start = mid + 1;
        //         else end = mid - 1;
        //     }
        // }
        // return -1;

        // First you can find pivot of rotated array and usig the index of pivot we can find the target element, appy bonary search from 0 to index of pivot if target element if greater then nums[0], other wise apply binary search from pivot + 1 to nums.length - 1

        int peak = pivotOfRotatedArray(nums);
        if (peak == -1){ // This means there is no pivot in the array means the array is sorted already with no pivot {1,3,4,5,6,7} it has no pivot j sorted array
            return index(nums,target,0,nums.length-1);
        }else if(target < nums[0]){
            return index(nums,target,peak+1,nums.length -1);
        }else{
            return index(nums, target, 0, peak);
        }
	}


	// This function will not work in duplicate values

	static int pivotOfRotatedArray(int[] array){
		int start = 0;
		int end = array.length - 1;
		while(start <= end){
			int mid = start + (end - start) / 2;
			if(mid < end && array[mid] > array[mid + 1]){
				return mid; 
			}else if(mid > start && array[mid] < array[mid - 1]){
				return mid -1;
			}
			else if(array[start] > array[mid]){
				end = mid -1;
			}else{
				start = mid + 1;
			}
		}
		return -1;
	}


	// https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
	static int pivotOfDuplicatedRotatedArray(int[] array){
		int start = 0;
		int end = array.length - 1;
		while(start <= end){
			int mid = start + (end - start) / 2;
			if(mid < end && array[mid] > array[mid + 1]){
				return mid; 
			}else if(mid > start && array[mid] < array[mid - 1]){
				return mid -1;
			}else if(array[start] == array[end]){
				if(start < mid && array[start] > array[start + 1]){ // start should be less than mis because in case of [1], the length is only 1 and both start and mid are same in this situation start and start + 1 will show an error so we use start < mid
					return start;
				}
				start++;
				if(end > mid && array[end] < array[end - 1]){
					return end - 1;
				}
				end--;
			}
			else if(array[start] > array[mid]){
				end = mid -1;
			}else{
				start = mid + 1;
			}
		}
		return -1;

	}

	// -------------------------------------------------------------------------------------------------------------------------------------------

	static int rotationCountOfRotatedArray(int[] arr){ // This func will return how many times the array is rotaed
		int pivot = pivotOfRotatedArray(arr); // We find the index of pivot first, index of pivot is the number of rotations of the array
		// arr = {4,5,6,7,0,1,2} here idex of pivot is 3 and if we add 1 to it, it becomes 4, 4 is the number of rotation of the array, you can check manually as well
		return pivot + 1;
	}
	// -------------------------------------------------------------------------------------------------------------------------------------------

	// https://leetcode.com/problems/valid-perfect-square/description/
	// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
	// https://leetcode.com/problems/successful-pairs-of-spells-and-potions/description/
	// https://leetcode.com/problems/arranging-coins/description/
	// https://leetcode.com/problems/koko-eating-bananas/description/
	// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
    public boolean isPerfectSquare(int num) {
        // int k =(int) Math.sqrt(num);
        // return k*k == num;
        int start = 1;
        int end = num/2;
        if(num == 1)return true;
        while(start <= end){
            int mid = start + (end - start) / 2;
            long k = mid * mid;
            if(k == num){
                return true;
            }
           else if(mid<num/mid ){ // This condition can be used to check whether the square of mid is greater smaller then the origional number, we dp this because if we do mid * mid it can exceed the integer limit in java 
                start  = mid+1;
            }
            else{
                end = mid-1;
            }
        }
        return false;
    }
	// -------------------------------------------------------------------------------------------------------------------------------------------
}

