import java.util.Arrays;
class SlidingWindow {
	// https://leetcode.com/problems/maximum-average-subarray-i/
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0;
        for(int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double max = sum/k;
        for(int i = k; i < nums.length; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            max = Math.max(max, sum/k);
        }
        return max;
        
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/permutation-in-string/
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length() < s1.length()) return false;
        int[] ar1 = new int[26];
        int[] ar2 = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            ar1[s1.charAt(i) - 'a']++;
            ar2[s2.charAt(i) - 'a']++;
        }

        if(Arrays.equals(ar1, ar2)) return true;

        int i = s1.length();
        int n = s1.length();

        while(i < s2.length()) {
            ar2[s2.charAt(i) - 'a']++;
            ar2[s2.charAt(i - n) - 'a']--;

            if(Arrays.equals(ar1, ar2)) return true;
            i++;
        }
        return false;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/defuse-the-bomb/
    public int[] decrypt(int[] code, int k) {
        if(k == 0) {
            return new int[code.length];
        }
        int[] ans = new int[code.length];

        if(k > 0) {
            int sum = 0;
            for(int i  = 0; i < k; i++) {
                sum += code[i];
            }

            for(int i = code.length - 1; i >= 0; i--) {
                ans[i] = sum;
                sum -= code[--k];
                sum += code[i];
                if(k == 0) {
                    k = code.length;
                }
                

            }
        } else {
            int sum = 0;
            for(int i = k; i < 0; i++) {
                sum += code[code.length  + i];
            }

            for(int i = 0 ; i < code.length; i++) {
                int n = code[(code.length + k + i) % code.length];
                ans[i] = sum;
                sum = sum - n;
                sum += code[i];
            }
        }
        return ans;        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------


}