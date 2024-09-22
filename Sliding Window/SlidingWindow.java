import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
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
    // https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
    private static boolean isVowel(char c)
    {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public int maxVowels(String s, int k) {
        int count = 0;
        int max = 0;
        for(int i = 0; i < k; i++) {
            if(isVowel(s.charAt(i))) {
                count++;
            }
        }

        max = Math.max(max, count);
        for(int i = k; i < s.length(); i++) {
            if(isVowel(s.charAt(i - k))) count--;
            if(isVowel(s.charAt(i))) count++;
            max = Math.max(max, count);
        }
        return max;

        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element/description/
    public int longestSubarray(int[] nums) {
        int max = 0;
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) { // store index of all the zeros and then compare their index to get the max length
            if(nums[i] == 0) list.add(i);
        }
        list.add(nums.length);
        if(list.size() < 2) return nums.length - 1;

        max = Math.max(max, list.get(1) - 1);
        for(int i = 2; i < list.size(); i++){
            int num = list.get(i) - list.get(i - 2) - 2;
            max = Math.max(max, num);
        }
        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    int helper(String s, int k, char c) { // for every character we check the maximun length of substring with atmost k replacement of characters (same as 'longestSubarray' question)
        List<Integer> list = new ArrayList<>();
        int max = 0;
        for(int i = 0; i < s.length() ; i++) {
            if(s.charAt(i) != c) {
                list.add(i);
            }
        }
        list.add(s.length());
        if(list.size() <= k) return s.length();

        max = Math.max(max, list.get(k));
        for(int i = k + 1; i < list.size(); i++) {
            int num = list.get(i) - (list.get(i - k - 1) + 1);
            max = Math.max(max, num);
        }
        return max;
    }
    public int characterReplacement(String s, int k) {
        HashSet<Character> set = new HashSet<>();
        for(char c : s.toCharArray()) {
            set.add(c);
        }
        List<Character> arr = new ArrayList<>(set);
        int max = 0;
        for(char c : arr) {
            int n = helper(s, k, c);
            max = Math.max(max, n);

        }
        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximize-the-confusion-of-an-exam/description/
    int check(String s, char c, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) != c)list.add(i);
        }
        if(list.size() <= k) return s.length();
        list.add(s.length());
        int max = 0;
        max = list.get(k);
        for(int i = k + 1; i < list.size(); i++) {
            int num = list.get(i) - (list.get(i - k - 1) + 1);
            max = Math.max(max, num);
        }
        return max;

    }
    public int maxConsecutiveAnswers(String answerKey, int k) {

        return Math.max(check(answerKey, 'T', k), check(answerKey, 'F', k));
        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/count-number-of-nice-subarrays/description/
    public int numberOfSubarrays(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) if(nums[i] % 2 == 1) list.add(i);

        if(k > list.size()) return 0;
        list.add(nums.length);
        int num = 0;
        int n1 = list.get(k) - list.get(k - 1);
        int n2 = list.get(0) + 1;
        num += (n1 * n2);
        for(int i = k + 1; i < list.size(); i++) {
            n1 = list.get(i) - list.get(i - 1);
            n2 = list.get(i - k) - list.get(i - k - 1);
            num += (n1 * n2);
        }
        return num;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/minimum-size-subarray-sum/
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0;
        int sum = 0;
        int left = 0;
        int min = nums.length;
        while(i < nums.length && sum < target) {
            sum += nums[i++];
        } 
        if(sum < target) return 0;
        min = Math.min(min, i);

        while(i < nums.length) {

            while(sum >= target) {
                min = Math.min(min, i - left);
                sum -= nums[left++];
            }

            sum += nums[i++];
        }
        
        while(sum >= target) {
            min = Math.min(min, i - left);
            sum -= nums[left++];
        }

        return min;



        // int i = 0;
        // int sum = 0;
        // int left = 0;
        // int min = Integer.MAX_VALUE;
        // while(i <= nums.length) {

        //     while(sum >= target) {
        //         min = Math.min(min, i - left);
        //         sum -= nums[left++];
        //     }

        //     if(i < nums.length) sum += nums[i];
        //     i++;
        // }
        
        // return min == Integer.MAX_VALUE? 0 : min;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/description/
    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int win = n - k;
        int i = 0;
        int tsum = 0;
        int left = 0;
        for(int j : cardPoints) tsum += j;
        int min = tsum;
        int sum = 0;
        while(i < win) {
            sum += cardPoints[i++];
        }

        while(i < n) {
            min = Math.min(min, sum);
            sum -= cardPoints[left++];
            sum += cardPoints[i++];
        }
        min = Math.min(min, sum);

        return tsum - min;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
    public int minOperations(int[] nums, int x) { // we j find the total sum and then subtract 'x' from this, now we j have to find subarray of max length we that we can get minimun remaining indexes that would be our minimun operations for the 'x'
        int tsum = 0;
        for(int i : nums) tsum += i;
        if(tsum < x) return -1;
        int i = 0; 
        int left = 0;
        int sum = 0;
        int max = -1;
        int tofind = tsum - x;
        while(i < nums.length && sum < tofind){
            sum += nums[i++];
        } 
        if(sum == x) max = Math.max(max, i);

        while(i <= nums.length) {
            while(sum > tofind) {
                sum -= nums[left++];
            }
            if(sum == tofind) max = Math.max(max, i - left);
            if(i < nums.length)sum += nums[i];
            i++;
        }
        return max == -1 ? -1 : nums.length - max; // if max is unchanged this means we cannot find max subarray of desired value so we return -1

        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
    public int numberOfSubstrings(String s) {
        int count = 0;
        int i = 0;
        int left = 0;
        int n = s.length();
        int[] arr =new int[3];
        while(i < n) {
            char c = s.charAt(i);
            arr[c - 'a']++;
            while(arr[0] >= 1 && arr[1] >= 1 && arr[2] >= 1) {
                count += (n - i);
                arr[s.charAt(left++) - 'a']--;
            }
            i++;
        }
        return count;   
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-erasure-value
    public int maximumUniqueSubarray(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int sum = 0;
        int i = 0;
        int left = 0;
        int max = 0;
        while(i < nums.length) {
            while(set.contains(nums[i])){
                set.remove(nums[left]);
                sum -= nums[left++];
            }
            set.add(nums[i]);
            sum += nums[i++];
            max = Math.max(max, sum);
        }
        return max;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
    public int minSwaps(int[] nums) {
        int k = 0; 
        for (int num : nums) if (num == 1) k++;
        int zeroCount = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] == 0) zeroCount++;
        }
        
        int minSwaps = zeroCount;
        
        for (int i = k; i < nums.length + k; i++) {
            if (nums[i % nums.length] == 0) zeroCount++;
            if (nums[(i - k) % nums.length] == 0) zeroCount--;
            
            minSwaps = Math.min(minSwaps, zeroCount);
        }
        
        return minSwaps;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-sum-of-almost-unique-subarray/description/
    public long maxSum(List<Integer> nums, int m, int k) {
        HashMap<Integer, Integer> set = new HashMap<>();
        long sum = 0;
        long max = 0;
        for(int i = 0; i < k; i++) {
            sum += nums.get(i);
            set.put(nums.get(i), set.getOrDefault(nums.get(i), 0) + 1);
        }
        for(int i = k; i < nums.size(); i++) {
            if(set.size() >= m) max = Math.max(max, sum);
            int prev = nums.get(i - k);
            int forward = nums.get(i);
            sum -= (long)prev;
            sum += (long)forward;
            set.put(prev, set.getOrDefault(prev, 0) - 1);
            set.put(forward, set.getOrDefault(forward, 0) + 1);
            if(set.get(prev) == 0) set.remove(prev);
        }
        if(set.size() >= m) max = Math.max(max, sum);
        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/count-complete-subarrays-in-an-array/
    public int countCompleteSubarrays(int[] nums) {
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i : nums) set.add(i);

        int size = set.size();
        // set.clear();
        int count = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while(map.size() == size) {
                count += (nums.length - i);
                map.put(nums[left], map.get(nums[left]) - 1);
                if(map.get(nums[left]) == 0) map.remove(nums[left]);
                left++;
            }
         
        }
        return count;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/range-sum-of-sorted-subarray-sums/
    public int rangeSum(int[] nums, int n, int left, int right) {
        ArrayList<Integer> que = new ArrayList<>();
        int k = 1;
        for(int i=0; i<n; i++){
            int sum = 0; 
            for(int j=i; j<n; j++){
                sum += nums[j];
                que.add(sum);
            }
        }
        // while(k++ < left) {
        //     que.poll();
        // }
        int sum = 0;
        int mod = 1000000007;
        // while(k++ <= right + 1) {
        //     sum = (sum + que.poll()) % mod; 
        // }
        Collections.sort(que);
        for(int i = left - 1; i < right; i++) {

            sum = (sum + que.get(i )) % mod; 
        }
        return sum;

        
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/subarrays-with-k-different-integers/
    int helper(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int c = 0;
        int left = 0;

        for(int i =0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

            while(map.size() > k) {
                int m = nums[left++];
                map.put(m, map.get(m) - 1);
                if(map.get(m) == 0) map.remove(m);
            }
            c += (i - left + 1);
        }     
        return c;
    }
    public int subarraysWithKDistinct(int[] nums, int k) {
        return helper(nums, k) - helper(nums, k - 1);
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/minimum-window-substring/
    // HashMap<Character, Integer> main;
    // HashMap<Character, Integer> assistant;

    // boolean contains() {
    //     for(char c : main.keySet()) {
    //         if(!assistant.containsKey(c)) return false;
    //         if(assistant.get(c) < main.get(c)) return false;
    //     }
    //     return true;
    // }
    // public String minWindow(String s, String t) {
    //     if(s.length() < t.length()) return "";
    //     main = new HashMap<>();
    //     assistant = new HashMap<>();
    //     for(char c : t.toCharArray()) {
    //         main.put(c, main.getOrDefault(c, 0) + 1);
    //     }

    //     int left = 0;
    //     int min = Integer.MAX_VALUE;
    //     int toret = 0;
    //     char[] str = s.toCharArray();
    //     for(int i = 0; i < s.length(); i++) {
    //         char c = str[i];
    //         assistant.put(c, assistant.getOrDefault(c, 0) + 1);
    //         while(contains()) {
    //             if(i - left + 1 < min) {
    //                 min = i - left + 1;
    //                 toret = left;
    //             }
    //             char m = str[left++];
    //             assistant.put(m, assistant.get(m) - 1);
    //             if(assistant.get(m) == 0) assistant.remove(m);
    //         }
    //     }

    //     return min == Integer.MAX_VALUE ? "" : s.substring(toret, toret + min);
    // }
    int[] main;
    int[] assistant;

    boolean contains() {
    for (int i = 0; i < main.length; i++) {
        if (main[i] > assistant[i]) return false;
    }
    return true;
}
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        main = new int[58];
        assistant = new int[58];
        for(char c : t.toCharArray()) {
            main[c - 'A']++;
        }

        int left = 0;
        int min = Integer.MAX_VALUE;
        int toret = 0;
        char[] str = s.toCharArray();
        for(int i = 0; i < str.length; i++) {
            char c = str[i];
            assistant[c - 'A']++;
            while(contains()) {
                if(i - left + 1 < min) {
                    min = i - left + 1;
                    toret = left;
                }
                char m = str[left++];
                assistant[m - 'A']--;
            }
        }

        return min == Integer.MAX_VALUE ? "" : s.substring(toret, toret + min);
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/get-equal-substrings-within-budget/
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int cost = 0;
        char[] str = s.toCharArray();
        char[] ttr = t.toCharArray();
        int max = 0;

        for(int i = 0; i < str.length; i++) {
            cost += Math.abs(str[i] - ttr[i]);
            while(cost > maxCost) {
                cost -= Math.abs(str[left] - ttr[left]);
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-k-th-smallest-pair-distance/description/?envType=daily-question&envId=2024-08-14
    int numberOfPairs(int[] nums, int desiredPairs) {
        int left = 0;
        int count = 0;
        for(int i = 1; i < nums.length; i++) {
            while(nums[i] - nums[left] > desiredPairs) {
                left++;
            }
            count+= i - left;
        }
        return count;
    }
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int start = 0;
        int en = nums[nums.length - 1] - nums[0];
        while(start < en) {
            int mid  = start + (en - start) / 2;
            int temp = numberOfPairs(nums, mid);
            if(temp < k)start = mid + 1;
            else en = mid;
        }
        return start;

    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/
    public int takeCharacters(String s, int k) {
        if(k == 0) return 0;
        int[] arr = new int[3];
        int n = s.length();
        int j = n - 1;
        while(arr[0] < k || arr[1] < k || arr[2] < k) {
            if(j == -1) return -1;
            arr[s.charAt(j--) - 'a']++;
        }
        int min = n;

        for(int i = 0; i <= n; i++) {
            while(j < n && arr[0] >= k && arr[2] >= k && arr[1] >= k) {
                min = Math.min(min, i + (n - j - 1));
                ++j;
                if(j < n)arr[s.charAt(j) - 'a']--;
            }
            if(i < n)arr[s.charAt(i) - 'a']++;
        }
        return min;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/sliding-window-maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(k == 1) return nums;
        int n = nums.length;
        Deque<Integer> que = new LinkedList();
        int[] toret = new int[n - k + 1];
        int l = k;
        for(int i = 0; i < n; i++) {
            int num = nums[i];
            while(!que.isEmpty() && que.getLast() < num) {
                que.pollLast();
            }
            que.addLast(num);

            if(i >= k && nums[i - k] == que.getFirst()) {
                que.pollFirst();
            }
            if(i >= k - 1){
                toret[i - k + 1] = que.getFirst();
            }
        }
        return toret;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-beauty-of-an-array-after-applying-operation/description/
    public int maximumBeauty(int[] pair, int k) {
        Arrays.sort(pair);
        int left = 0;
        int max = 0;

        for(int i = 0; i < pair.length; i++) {
            while(pair[i] - k > pair[left] + k) {
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    public int maximumBeauty2(int[] pair, int k) {
        Arrays.sort(pair);
        int n = pair.length;
        // Pair[] pair = new Pair[n];
        // for(int i = 0; i < nums.length; i++) {
        //     int p = nums[i];
        //     pair[i] = new Pair(p - k, p + k);
        // }
        // System.out.println(Arrays.toString(pair));
        // int max = 0;
        int left = 0;
        int i = 0;
        int fir = pair[0] + k;
        while(i < n && pair[i] - k <= fir) {
            i++;
        }
        if(i == n) return n;
        int max = i;

        while(i < n) {
            while(pair[i] - k > pair[left] + k) {
                left++;
            }
            max = Math.max(max, i - left + 1);
            i++;
        }
        return max;
    }

    class Pair{
        int fir;
        int sec;
        Pair(int x, int y) {
            fir = x;
            sec = y;
        }

        public String toString() {
            return fir + " " + sec;
        }
    }


    public int maximumBeauty3(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        Pair[] pair = new Pair[n];
        for(int i = 0; i < nums.length; i++) {
            int p = nums[i];
            pair[i] = new Pair(p - k, p + k);
        }
        // System.out.println(Arrays.toString(pair));
        // int max = 0;
        int left = 0;
        int i = 0;
        int fir = pair[0].sec;
        while(i < n && pair[i].fir <= fir) {
            i++;
        }
        if(i == n) return n;
        int max = i;

        while(i < n) {
            while(pair[i].fir > pair[left].sec) {
                left++;
            }
            max = Math.max(max, i - left + 1);
            i++;
        }
        return max;
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
    public int longestSubarray(int[] nums, int limit) {
        int maxLength = 0;
        int left = 0;
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedList<Integer> l2 = new LinkedList<>();
        
        for (int right = 0; right < nums.length; right++) {
            int m = nums[right];
            while(!l1.isEmpty() && l1.getLast() < m) {
                l1.pollLast();
            }
            l1.addLast(m);

            while(!l2.isEmpty() && l2.getLast() > m) {
                l2.pollLast();
            }
            l2.addLast(m);
            // Ensure the window satisfies the condition
            while (l1.getFirst() - l2.getFirst() > limit) {
                // Remove the element at 'left' from the queues
                if(nums[left] == l1.getFirst())l1.removeFirst();
                if(nums[left] == l2.getFirst())l2.removeFirst();
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/
    // https://leetcode.com/problems/alternating-groups-ii/
    // https://leetcode.com/problems/find-the-power-of-k-size-subarrays-ii/description/
    public int[] resultsArray(int[] nums, int k) {
        if(k == 1) return nums;
        int n = nums.length;
        int[] toret = new int[n - k + 1];
        int c = 1;
        toret[0] = -1;
        // for(int i = 1; i < k; i++) {
        //     if(nums[i] == nums[i - 1] + 1) {
        //         c++;
        //         if(c >= k)toret[i - k + 1] = nums[i];
        //     } else c = 1;
        // }
        
        for(int i = 1; i < n; i++) {
            if(nums[i] == nums[i - 1] + 1) {
                c++;
                if(c >= k)toret[i - k + 1] = nums[i];
                else if(i >= k)toret[i - k + 1] = -1;
            } else {
                if(i >= k)toret[i - k + 1] = -1;
                c = 1;
            }
        }
        return toret;
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
    public int longestSubstring(String s, int k) {
        if(s.length() == 0 || s.length() < k) return 0;
        int[] count = new int[26];
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        int max = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
        }
        // System.out.println(Arrays.toString(count));
        for(int i = 0; i < 26; i++) {
            if(count[i] != 0 && count[i] < k) set.add((char)(i + 97));
        }
        if(set.isEmpty()) return s.length();
        // System.out.println(s.substring(0, 1) + "fsj");

        // HashMap<Character, Integer> map = new HashMap<>();
        int startIndex = 0;
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(set.contains(c)) {
                max = Math.max(max, longestSubstring(s.substring(startIndex, i), k));
                startIndex = i + 1;
            }
            // else max = Math.max(max, i - startIndex + 1);
        }
        max = Math.max(max, longestSubstring(s.substring(startIndex, s.length()), k));

        return max;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/replace-the-substring-for-balanced-string/
    int index(char c) {
        if(c == 'Q') return 0;
            else if(c == 'W') return 1;
            else if(c == 'E') return 2;
            else return 3;
    }
    public int balancedString(String s) {
        int[] arr = new int[4];
        for(char c : s.toCharArray()) {
            arr[index(c)]++;
        }
        int target = s.length()/4;
        int left = 0;
        int min = s.length();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[index(c)]--;
            while(left < s.length()
                && arr[index('Q')] <= target
                && arr[index('E')] <= target
                && arr[index('R')] <= target
                && arr[index('W')] <= target
                ) {
                min = Math.min(min, i - left + 1);
                char m = s.charAt(left++);
                arr[index(m)]++;
            }
        }
        return min;// == s.length() + 1 ? 0 :min;

        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/grumpy-bookstore-owner/description/
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int sum = 0;
        int max = 0;
        int another = 0;
        int n = grumpy.length; 
        for(int i = 0; i < n; i++) {
            if(grumpy[i] == 0)sum += customers[i];
        }
        for(int i = 0; i < minutes; i++) {
            if(grumpy[i] == 1) another += customers[i];
        }
        max = Math.max(max, sum + another);

        for(int i = minutes; i < n; i++) {
            if(grumpy[i - minutes] == 1) another -= customers[i - minutes];
            if(grumpy[i] == 1) another += customers[i];
            max = Math.max(max, sum + another);
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
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------


}