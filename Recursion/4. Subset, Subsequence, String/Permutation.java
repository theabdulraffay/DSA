import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Permutation {
	public static void main(String[] args) {
		String s = "abcd";
		// permutation("", s);	
		System.out.println(Arraypermutation("", s));
		System.out.println(permutationCount("", s));

	}

	static void permutation(String newString, String s) { // With each new character from the string s we have different choices to add the characte to positions, either we can add the characte to the 0th position or 1st and soo on till the last index of the newString, so with ech recursion call the number of recursive call increase thats why we increasethe number of recursive call using for loop that will run the length of newString and add the character of String S to it in different position. 
		if (s.isEmpty()) {
			System.out.println(newString);
			return;
		}

		for (int i = 0; i <= newString.length(); i++) {
			String first = newString.substring(0, i);
			String second = newString.substring(i);
			char ch = s.charAt(0);
			permutation(first + ch + second, s.substring(1));
		}
	}


	static ArrayList<String> Arraypermutation(String newString, String s) { // This will return a list of all the possible permutations 
		if (s.isEmpty()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(newString);
			return list;
		}
		ArrayList<String> finalList = new ArrayList<String>();
		for (int i = 0; i <= newString.length(); i++) {
			String first = newString.substring(0, i);
			String second = newString.substring(i);
			char ch = s.charAt(0);
			ArrayList<String> newlist = Arraypermutation(first + ch + second, s.substring(1));
			finalList.addAll(newlist);
		}
		return finalList;
	}


	static int permutationCount(String newString, String s) { // This will return the number of permutations, we can se formula as well this is just for learning purpose
		if (s.isEmpty()) {
			// System.out.println(newString);
			return 1;
		}
		int count = 0;
		for (int i = 0; i <= newString.length(); i++) {
			String first = newString.substring(0, i);
			String second = newString.substring(i);
			char ch = s.charAt(0);
			count += permutationCount(first + ch + second, s.substring(1));
		}
		return count;
	}


	// https://leetcode.com/problems/permutations/
	// https://leetcode.com/problems/permutations-ii/
	// https://leetcode.com/problems/combination-sum/
	// https://leetcode.com/problems/combination-sum-ii/
	// https://leetcode.com/problems/palindrome-partitioning/
	
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/next-permutation/
    public void nextPermutation(int[] nums) {
        int index = -1;
        for (int i = nums.length -2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                break;
            }
        }
        if (index  == -1) {
            Arrays.sort(nums);
            return;
        }
        for (int i = nums.length -1; i > index; i--) {
            if (nums[i] > nums[index]) {
                int t = nums[i];
                nums[i] = nums[index];
                nums[index] = t;
                break;
            }
        }

        int i = index + 1;
        int j = nums.length - 1;
        while (i < j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }   
    }


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/permutations/description/
    void per(List<List<Integer>> list,List<Integer> temp,int[] nums){
        if(temp.size() == nums.length) {
            list.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) continue;
            temp.add(nums[i]);
            per(list, temp, nums);
            temp.remove(temp.size() - 1);
        }
    }
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        per(list, new ArrayList<Integer>(), nums);
        return list;
        
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/permutations-ii/
    void per(List<List<Integer>> list, List<Integer> temp,int[] nums, boolean[] bol) {
        if (temp.size() == nums.length) {
            System.out.println(temp);
            if (!list.contains(temp)) {
                list.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(bol[i])continue;
            temp.add(nums[i]);
            bol[i] = true;
            per(list, temp, nums, bol);
            bol[i] = false;
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        per(list, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        return list;      
    }




}