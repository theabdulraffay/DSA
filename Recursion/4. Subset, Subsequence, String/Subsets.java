import java.util.ArrayList;
class Subsets{
	public static void main(String[] args) {
		String a = "abc";
		// subset("abc", "");
		System.out.println(subset2("abc", ""));



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



}