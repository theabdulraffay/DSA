
import java.util.Arrays;
import java.util.HashSet;

class BackTrackingQuestions {
    //---------------------------------------------------------------------
    // https://leetcode.com/problems/construct-smallest-number-from-di-string/
    boolean helper(String pattern, StringBuilder n, int i, boolean[] visited) {
        if(i == pattern.length()) return true;
        int t = n.charAt(i) - '0';
        for(int k = 1; k < 10; k++) {
            if(visited[k]) continue;
            if((pattern.charAt(i) == 'I' && k > t) || (pattern.charAt(i) == 'D' && k < t)){
                n.append(k);
                visited[k] = true;
                if(helper(pattern, n, i + 1, visited)) return true;
                n.deleteCharAt(n.length() - 1);
                visited[k] = false;
            }
        }
        return false;

    }
    public String smallestNumber(String pattern) {
        for(int i = 1; i < 10; i++) {
            StringBuilder sb = new StringBuilder();
            boolean[] v = new boolean[10];
            sb.append(i);
            v[i] = true;
            if(helper(pattern, sb, 0, v)) return sb.toString();
        }
        return "";

        
    }
    //---------------------------------------------------------------------
        // https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/
    boolean helper(int[] arr, boolean[] used, int n, int index) {
        while (index < arr.length && arr[index] != 0) {
            index++;
        }
        if (index == arr.length) {
            return true;
        }
        for (int i = n; i > 0; i--) {
            if (used[i])
                continue;

            if (i == 1) {
                arr[index] = 1;
                used[1] = true;
                if (helper(arr, used, n, index + 1))
                    return true;
                arr[index] = 0;
                used[1] = false;

            } else {
                if (index + i < arr.length && arr[index + i] == 0) {
                    arr[index] = i;
                    arr[index + i] = i;
                    used[i] = true;
                    if (helper(arr, used, n, index + 1))
                        return true;
                    arr[index] = 0;
                    arr[index + i] = 0;
                    used[i] = false;
                }
            }
        }
        return false;

    }

    public int[] constructDistancedSequence(int n) {
        int[] arr = new int[n * 2 - 1];
        helper(arr, new boolean[n + 1], n, 0);
        return arr;

    }
    //---------------------------------------------------------------------
    // https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
    int size;
    String s;

    int helper(int n, StringBuilder sb, char v) {
        if (sb.length() == n) {
            if (--size == 0) {
                s = sb.toString();
                return 1;
            }
            return 0;
        }
        for (char i = 'a'; i <= 'c'; i++) {
            if (v == i) continue;
            sb.append(i);
            if (helper(n, sb, i) == 1) return 1;
            sb.deleteCharAt(sb.length() - 1);
        }
        return -1;
    }

    public String getHappyString(int n, int k) {
        size = k;
        s = "";
        helper(n, new StringBuilder(), '.');
        if (s.length() != 0) return s;
        return "";

    }

    //---------------------------------------------------------------------
    // https://leetcode.com/problems/find-unique-binary-string/
    String helper(int n, HashSet set, String s) {
        if(s.length() == n) {
            if(!set.contains(s)) return s;
            return null;
        }
        String p = helper(n, set, s + '1');
        if(p != null) return p;
        return helper(n, set, s + '0');
    }
    public String findDifferentBinaryString(String[] nums) {
        HashSet<String> set = new HashSet<>();
        set.addAll(Arrays.asList(nums));
        return helper(nums.length, set, "");   
    }

    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------
    //---------------------------------------------------------------------

}