import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
class QuestionsRecursion {
    Map<String, Boolean> map = new HashMap<>();
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n != s2.length()) {
            return false;
        }
        if(s1.equals(s2) || n == 0) return true;

        String temp = s1 + s2;
        if(map.containsKey(temp)) return map.get(temp);

        if(n == 1) return false;
        for(int i = 1; i <= n - 1; i++) {
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && 
            isScramble(s1.substring(i), s2.substring(i))){
                map.put(temp, true);
                 return true;
            }

            if(isScramble(s1.substring(0, i), s2.substring(n - i)) && 
            isScramble(s1.substring(i), s2.substring(0, n - i))){
                map.put(temp, true);
                return true;
            }
        }
        map.put(temp, false);
        return false;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/regular-expression-matching/
    Boolean[][] dp;    
    public boolean isMatch(String s, String p) {
        dp = new Boolean[s.length() + 1][p.length() + 1];
        return fina(0, 0, s, p);
    }
    
    boolean fina(int i, int j, String s, String p) {
        if (j == p.length()) {
            return i == s.length();
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        boolean ans;
        boolean currentMatch = (i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));
        
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            ans = fina(i, j + 2, s, p) || (currentMatch && fina(i + 1, j, s, p));
        } else {
            ans = currentMatch && fina(i + 1, j + 1, s, p);
        }
        
        dp[i][j] = ans;
        return ans;
    }
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/word-break-ii/description/
    HashSet<String> set;
    List<String> list;

    void helper(String s, int ind, String newstr) {
        if (ind == s.length())
            list.add(newstr.trim());

        StringBuilder sb = new StringBuilder();
        for (int i = ind; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (set.contains(sb.toString())) {
                sb.append(" ");
                helper(s, i + 1, newstr + sb.toString());
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        list = new ArrayList<>();
        helper(s, 0, "");
        return list;

    }


    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------------

}