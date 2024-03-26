class Strings {
	public static void main(String[] args) {
		// System.out.println(removeACharacter("baccad", ""));
		System.out.println(removeAString("baccappledd"));
	}

	static String removeACharacter (String s, String ans) { // This function will remove all the A's from the string using recursion
		if (s.isEmpty()) {
			return ans;
		}
		if (s.charAt(0) != 'a') {
			ans += s.charAt(0);
		}
		return removeACharacter(s.substring(1), ans);
	}

	static String removeACharacter (String s) { // This function will remove all the A's from the string using recursion
		if (s.isEmpty()) {
			return "";
		}
		if (s.charAt(0) == 'a') {
			return removeACharacter(s.substring(1));
		}
		return s.charAt(0) + removeACharacter(s.substring(1));
	}

	static String removeAString (String s) { // This function will remove a string lets say "Apple" from a given string using recursion
		if (s.isEmpty()) {
			return "";
		}
		if (s.startsWith("apple")) {	
			return removeAString(s.substring(5));
		}
		return s.charAt(0) + removeAString(s.substring(1));
	}

	static String removeAppButNotApple (String s) { // This function will remove "App" only when it is not start with "Apple" using recursion
		if (s.isEmpty()) {
			return "";
		}
		if (s.startsWith("app") && !s.startsWith("apple")) {	
			return removeAppButNotApple(s.substring(3));
		}
		return s.charAt(0) + removeAppButNotApple(s.substring(1));
	}
}