import java.util.HashMap;
class SubArray {
	public static void main(String[] args) {
        double x = 0.00001;
        int n = 2147483647;

        if (n == 0)System.out.println("noooooo");
        double t = x;
        int temp = Math.abs(n);
        for(int i = 1; i < temp; i++) {
            x *= t;
        }
        System.out.println((n < 0) ? 1/x : x);
		
	}
    
    // https://leetcode.com/problems/maximum-subarray/description/
    public int maxSubArray(int[] nums) { // Input: nums = [-2,1,-3,4,-1,2,1,-5,4], Output: 6
        if (nums.length == 1) return nums[0];
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            if (sum < 0) sum = 0;
        }
        return max;   
    }

	// https://leetcode.com/problems/subarray-product-less-than-k/
	public int numSubarrayProductLessThanK(int[] nums, int k) { // Input: nums = [10,5,2,6], k = 100, Output: 8
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int prod = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (prod < k) count++;
                else break;
                prod *= nums[j];
            }
            if (prod < k) count++;
        }
        return count;
    }

    // https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/
    public int maxSubarrayLength(int[] nums, int k) { // Input: nums = [1,2,3,1,2,3,1,2], k = 2, Output: 6
        int ans = 0;
        int i = 0;
        int j = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (i < nums.length) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.get(nums[i]) > k) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) - 1);
                j++;
            }
            ans = Math.max(ans, i - j + 1);
            i++;
        }
        return ans;
    }

    // https://leetcode.com/problems/subarrays-with-k-different-integers/description/
    // https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/

    
}