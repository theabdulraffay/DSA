import java.util.*;
class Questions {
	public static void main(String[] args) {
		LetterCombinationsofaPhoneNumber("", "28");
		// System.out.println(LetterCombinationsofaPhoneNumberList("", "23"));

		
	}

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


	static List<String> LetterCombinationsofaPhoneNumberList(String newString, String s) {
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
}