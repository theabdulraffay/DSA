import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.*;
import java.util.Comparator;
class QuestionsHashing {
	public List<Integer> majorityElement(int[] nums) {
     	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
     	for(int i : nums) {
     		map.put(i, map.getOrDefault(i,0) + 1);
     	}   

     	int n = nums.length / 3;
     	List<Integer> toReturn = new ArrayList<Integer>();
     	for(Entry<Integer, Integer> entry : map.entrySet()) {
     		if(entry.getValue() > n) {
     			toReturn.add(entry.getKey());
     		}
     	}
     	return toReturn;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    // https://leetcode.com/problems/determine-if-two-strings-are-close/
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) return false; 
        else if (word1.equals(word2))  return true; 
        char[] f = word1.toCharArray();
        char[] s = word2.toCharArray();

        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for(int i = 0; i < f.length; i++) {
            map1.put(f[i], map1.getOrDefault(f[i], 0) + 1);
            map2.put(s[i], map2.getOrDefault(s[i], 0) + 1);

        }
        for (char c : map1.keySet()) {
            if (!map2.containsKey(c)) {
                return false;
            }
        }

        ArrayList<Integer> arr1 = new ArrayList<>(map1.values());
        ArrayList<Integer> arr2 = new ArrayList<>(map2.values()); 
        Collections.sort(arr1);
        Collections.sort(arr2);
        return arr1.equals(arr2);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// https://leetcode.com/problems/unique-number-of-occurrences/
    public boolean uniqueOccurrences(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : array) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        ArrayList<Integer> arr = new ArrayList<>(map.values());
        for(int i = arr.size() - 1; i >= 0; i--) {
            int temp = arr.get(i);
            arr.remove(i);
            if(arr.contains(temp)) return false;
        }
        return true;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-the-difference-of-two-arrays/
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();

        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            set2.add(i);
        }

        List<List<Integer>> toret = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        for (int i : set1) {
            if(!set2.contains(i)) {
                first.add(i);
            }
        }
        toret.add(first);

        List<Integer> sec = new ArrayList<>();
        for (int i : set2) {
            if(!set1.contains(i)) {
                sec.add(i);
            }
        }
        toret.add(sec);
        return toret;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/equal-row-and-column-pairs/
    public int equalPairs(int[][] grid) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int[] i : grid) {
            StringBuilder sb = new StringBuilder();
            for(int j : i) {
                sb.append(j);
                sb.append(" ");
            }
            String t = sb.toString();
            map.put(t , map.getOrDefault(t, 0) + 1);
        }

        int ans = 0;

        for(int i = 0; i < grid[0].length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < grid.length; j++) {
                sb.append(grid[j][i]);
                sb.append(" ");
            }
            String t = sb.toString();
            if(map.containsKey(t)) {
                ans += map.get(t);
            }
        }
        return ans;
        
    }


    boolean check(int row, int col, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] != grid[i][col]) {
                return false;
            }
        }
        return true;
    }
    public int equalPairs2(int[][] grid) {
        int count = 0;
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][0] == grid[0][j] && check(i, j, grid)) {
                        count++;
                }
            }
        }
        return count;
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map2 = new HashMap<>();

        for(int i = 0 ; i < arr1.length; i++) {
            map2.put(arr1[i], map2.getOrDefault(arr1[i], 0) + 1);
        }

        int[] toret= new int[arr1.length];
        int k = 0;
        for(int j : arr2) {
            int c = map2.get(j);
            while(c > 0) {
                toret[k++] = j;
                c--;
            }
            map2.remove(j);
        }

        int[] remi = new int[arr1.length - k];
        int ind = 0;
        for(int j : arr1) {
            if(map2.containsKey(j)) {
                int c = map2.get(j);
                while(c > 0) {
                    remi[ind++] = j;
                    c--;
                }
                map2.remove(j);
            }
        }
        Arrays.sort(remi);
        for(int j : remi) {
            toret[k] = j;
            k++;
        }
        return toret;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/optimal-partition-of-string/
    public int partitionString(String s) {
        HashSet<Character> set = new HashSet<>();
        int count = 1;

        for(char i : s.toCharArray()) {
            if(set.contains(i)) {
                count++;
                set.clear();
            }
            set.add(i);
        }
        return count;
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/k-diff-pairs-in-an-array/
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        Set<Integer> unpair = new HashSet<>();
        int c = 0;
        for(int i : nums) {
            if(set.contains(i - k) && !unpair.contains(i - k)) {
                c++;
                unpair.add(i - k);
            }
            set.add(i);
        }
        return c;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/group-anagrams/description/
    // boolean check(String fi, String se) {
    //     if(fi.length() != se.length()) return false;
    //     char[] a = fi.toCharArray();
    //     char[] b = se.toCharArray();
    //     Arrays.sort(a);
    //     Arrays.sort(b);
    //     for(int i = 0; i < a.length; i++) {
    //         if(a[i] != b[i]) return false;
    //     }
    //     return true;
    // }


    // boolean check(String fi, String se) {
    //     if(fi.length() != se.length()) return false;
    //     HashMap<Character, Integer> m = new HashMap<>();
    //     HashMap<Character, Integer> n = new HashMap<>();
    //     for(int i = 0; i < fi.length(); i++) {
    //         m.put(fi.charAt(i), m.getOrDefault(fi.charAt(i), 0) + 1);
    //         n.put(se.charAt(i), n.getOrDefault(se.charAt(i), 0) + 1);

    //     }
    //     for(int i = 0; i < fi.length(); i++) {
    //         char c = fi.charAt(i);
    //         if(!n.containsKey(c) || m.get(c) != n.get(c)) return false;
    //     }
    //     return true;
    // }
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> fina = new ArrayList<>();

        // boolean[] ch = new boolean[strs.length];
        // for(int i = 0; i < strs.length; i++) {
        //     if(ch[i]) continue;
        //     List<String> s = new ArrayList<>();
        //     s.add(strs[i]);
        //     ch[i] = true;
        //     for(int j = i + 1; j < strs.length; j++) {
        //         if(check(strs[i], strs[j])) {
        //             s.add(strs[j]);
        //             ch[j] = true;
        //         }
        //     }
        //     fina.add(s);
        // }

        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        for(String k : map.keySet()) {
            fina.add(map.get(k));
        }


        return fina;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/top-k-frequent-elements/
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(Comparator.comparingInt(Map.Entry::getValue));

        // Sort the list using a comparator that compares the values
        // entryList.sort(Map.Entry.comparingByValue());
        int[] toret = new int[k];
        int i = 0;
        while(k-- > 0) {
            toret[i] = entryList.get(entryList.size() - 1- i).getKey();
            i++;
        }

        return toret;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/sort-characters-by-frequency/description/
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char te = s.charAt(i);
            map.put(te, map.getOrDefault(te, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> freqList = new ArrayList<>(map.entrySet());
        freqList.sort(Comparator.comparingInt(Map.Entry::getValue));

        StringBuilder str = new StringBuilder();

        for(int i = freqList.size() - 1; i >= 0; i--) {
            int freq = freqList.get(i).getValue();
            char ch = freqList.get(i).getKey();
            for (int j = 0; j < freq; j++) {
                str.append(ch);
            }
        }
        return str.toString();
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

}