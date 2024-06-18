import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Arrays;
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
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

}