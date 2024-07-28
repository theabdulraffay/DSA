import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
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
    // https://leetcode.com/problems/minimum-difference-between-highest-and-lowest-of-k-scores/
    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int min = nums[k - 1] - nums[0];

        for(int i = k; i < nums.length; i++) {
            min = Math.min(min, nums[i] - nums[ i - k + 1]);
        }
        return min;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-the-k-beauty-of-a-number/description/
    public int divisorSubstrings(int num, int k) {
        int count = 0;
        String s = String.valueOf(num);
        for(int i = k; i <= s.length(); i++) {
            int n = Integer.parseInt(s.substring(i - k, i));
            if(n == 0) continue;
            if(num % n == 0) count++;

        }
        return count;
        
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/description/
    public int countGoodSubstrings(String s) {
        // char[] ch = s.toCharArray();
        // int count = 0;
        // for(int i = 2; i < ch.length; i++) {
        //     if(ch[i] != ch[i - 1] && ch[i] != ch[i - 2] && ch[i - 1] != ch[i - 2]) count++;
        // }
        // return count;

        if(s.length() < 3) return 0;
        HashSet<Character> st = new HashSet<>();
        int left = 1;
        int right = 0;
        while(right < 3) {
            st.add(s.charAt(right++));
        }
        int count = 0;
        if(st.size() == 3)count++;
        st.clear();

        while(right < s.length()) {
            while(left<= right) {
                st.add(s.charAt(left++));
            }
            if(st.size() == 3) count++;
            right++;
            left -= 2;
            st.clear();
        }
        return count;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/fruit-into-baskets/
    public int totalFruit(int[] nums) {
        int i = 0;
        int firstIndex = 0;
        while(i < nums.length && nums[i] == nums[0]) i++;
        if(i == nums.length) return nums.length;
        
        int secondIndex = i;
        int max = 0;

        while(i < nums.length) {
            if(nums[i] != nums[firstIndex] && nums[i] != nums[secondIndex]) {
                max = Math.max(max, i - firstIndex);
                int ind = i - 1;
                while(ind > 0 && nums[ind] == nums[ind- 1]) ind--;
                firstIndex = ind;
                secondIndex = i;
            }
            i++;
        }
        max = Math.max(max, i - firstIndex);
        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/max-consecutive-ones-iii/description/
    public int longestOnes(int[] nums, int k) {
        if(k == nums.length) return k;
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for(int i= 0; i < nums.length; i++) {
            if(nums[i] == 0) list.add(i);
        }
        list.add(nums.length);
        if(k >= list.size()) return nums.length;

        
        max = Math.max(max, list.get(k));

        for(int i = k + 1; i < list.size(); i++) {
            int ind = i - k - 1;
            int nu = list.get(i) - (list.get(ind) + 1);
            max = Math.max(max, nu);
        }
        return max;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------


}