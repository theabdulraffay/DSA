import java.util.ArrayList;
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

}