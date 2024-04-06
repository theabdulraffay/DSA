import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Subsets{
	public static void main(String[] args) {
		String a = "abc";
		// subset("abc", "");
		// System.out.println(subset2("abc", ""));

		int[] arr = {1,2,2};
		List<List<Integer>> ans = subset(arr);
		for (List<Integer> i : ans) {
			System.out.println(i);
		}




		/* 
		ans = {a, b, c, ab, ac, bc, abc}	all possible subsets
		In this pattern some element are added and some are removed in the subset
		In each iteration we have 2 options 
			i.  To take the current character in the subset
			ii. To not take the current character in the subset
		With each recursion call we will remove the current character
		This will continuw to do so until all the possible subsets are made and origional string gets empty

		*/
	}

	// how to creat subset using recursion and iteration
	// Make sure to draw the graph for recursion every time before you code this helps alot 


	static void subset(String s, String newString) {
		if (s.isEmpty()) {
			System.out.println(newString);
			return;
		}
		subset(s.substring(1), newString + s.charAt(0)); // With each character we either add it to the new String or we skip it
		subset(s.substring(1), newString); // in this recursion call we skip the current character 

	}

	static ArrayList<String> subset2(String s, String newString) { // returns a list of all the possible subsets
		if (s.isEmpty()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(newString);
			return list;
		}
		ArrayList<String> l1 = subset2(s.substring(1), newString + s.charAt(0)); 
		ArrayList<String> l2 = subset2(s.substring(1), newString);
		ArrayList<String> newlist = new ArrayList<String>();
		newlist.addAll(l1);
		newlist.addAll(l2);
		return newlist; 

	}

	// https://leetcode.com/problems/subsets/
	static List<List<Integer>> subset(int[] arr) { // This works by adding the elements of array ony by one to the half of the original list of list, in this way subsets can be created, when duplicate copy are generated and and the element of the array is added to this duplicate array and then again all these duplicate array are added tp the list of list, in this way with each iteration size of origional array get double 
		List<List<Integer>>list = new ArrayList<List<Integer>>();
		list.add(new ArrayList<Integer>());
		for (int num : arr) {
			int n = list.size();
			for (int i = 0; i < n; i++) {
				ArrayList<Integer> newlist = new ArrayList<Integer>(list.get(i));
				newlist.add(num);
				list.add(newlist);
			}
		}
		return list;
	}

 	// when find a duplocate element inly add it in the newly created subseet in the previous step (ony when duplicates are together), sort the array

 	static List<List<Integer>> subsetDuplicate(int[] arr) { // If array has a duplicate value it will not add it in the previous list of subsets, it will skip it so we do start = end. 
 		Arrays.sort(arr);
		List<List<Integer>>list = new ArrayList<List<Integer>>();
		list.add(new ArrayList<Integer>());
		int start = 0;
		int end = 0;
		for (int i = 0; i < arr.length; i++) {
			if (i > 0 && arr[i] == arr[i - 1]) {
				start = end;
			}
			int n = list.size();
			end = n;
			for (int j = start; j < n; j++) {
				ArrayList<Integer> newlist = new ArrayList<Integer>(list.get(j));
				newlist.add(arr[i]);
				list.add(newlist);
			}
		}
		return list;
	}


	// https://leetcode.com/problems/subsets-ii/description/
	public List<List<Integer>> subsetsWithDup(int[] arr) {
        Arrays.sort(arr);
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		list.add(new ArrayList<Integer>());
		for (int i = 0; i < arr.length; i++) {
			int n = list.size();
			for (int j = 0; j < n; j++) {
				ArrayList<Integer> newlist = new ArrayList<Integer>(list.get(j));
				newlist.add(arr[i]);
				if(!list.contains(newlist)){
                    list.add(newlist);
                }
			}
		}
		return list;
        
    }

}