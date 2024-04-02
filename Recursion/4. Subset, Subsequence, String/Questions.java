import java.util.*;
class Questions {
	public static void main(String[] args) {
		// LetterCombinations("", "28");
		// System.out.println(LetterCombinationsofaPhoneNumberList("", "23"));
		// diceRoll("", 4);
		System.out.println(diceRollRet("", 5));
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static void LetterCombinationsofaPhoneNumber(String newString, String s) {
		if (s.isEmpty()) {
			System.out.println(newString);
			return;
		}
		int first = Integer.parseInt(s.substring(0, 1));
		int rotations = (first == 7 || first == 9) ? 4 : 3;
        int c = (first > 7) ? 1 : 0;
		int index = (first - 2) * 3;
		for(int i = 0; i < rotations; i++) {
			char temp = (char)('a' + index + i + c);
			LetterCombinationsofaPhoneNumber(newString + temp, s.substring(1));
		}
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
	static List<String> LetterCombinationsofaPhoneNumberList(String newString, String s) { // Input: digits = "23", Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
		if (s.isEmpty()) {
			List<String> newstr = new ArrayList<String>();
            if(newString.isEmpty()) return newstr;

			newstr.add(newString);
			return newstr;
		}
		int first = Integer.parseInt(s.substring(0, 1));
		
		int rotations = (first == 7 || first == 9) ? 4 : 3; // if the number is 7 or 9 as they both have four, four characters each sp we run loop 4 times.

        int c = (first > 7) ? 1 : 0; // if the number is 8 or 9, as the number 7 have 4 characters (pqrs) so we add one more character to the number 8 and 9 to equal their respective characters

		first = (first - 2) * 3; // this will five the index of characters with their respective number, i.e. for 2 it has a, b and c so indexes are 0 1 and 2, to find that we minus it with 2 and then multiple it with 3, this will give zero as the number it self was 2, (2 - 2 = 0) so we get the starting index which is zero in this case now we know except for number 7 and 9 all number on keppad have 3 characters so we run a loop 3 times to get all the characters of the keypad number.

		List<String> list = new ArrayList<String>();
		for(int i = 0; i < rotations; i++) {
			char temp = (char)('a' + first + i + c);
			List<String> returned = LetterCombinationsofaPhoneNumberList(newString + temp, s.substring(1));
			list.addAll(returned);
		}
		return list;
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static void LetterCombinations(String newString, String s) { // A much cleaner code
		if (s.isEmpty()) {
			System.out.println(newString);
			return;
		}
		int digit = s.charAt(0) - '0';
		int rotations = (digit == 7 || digit == 9) ? 4 : 3;
        int c = (digit > 7) ? 1 : 0;
		int index = (digit - 2) * 3;
		for(int i = index; i < rotations + index; i++) {
			char temp = (char)('a' + i + c);
			LetterCombinations(newString + temp, s.substring(1));
		}
	}

	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	static void diceRoll(String xp, int n) { // all possible combination that add up to form n
		if (n == 0) {
			System.out.println(xp);
		}
		for (int i = 1; i <= 6 && i <= n; i++) {
			diceRoll(xp + i, n - i);
		}
	}


	static List<String> diceRollRet(String xp, int n) { // all possible combination that add up to form n
		if (n == 0) {
			List<String> list = new ArrayList<String>();
			list.add(xp);
			return list;
		}
		 List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 6 && i <= n; i++) {
			list.addAll(diceRollRet(xp + i, n - i));
		}
		return list;
	}
}