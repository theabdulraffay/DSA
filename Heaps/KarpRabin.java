class KarpRabin2{
	// It is used to search in a string, it is also in google search
	// String a = "Bob";
	// String b = "WillainMikeBobFrank"
	// Subset of 'b' has 'a'
	// we take take the hashcode of length 'a' in string 'b'
	// hash(string a) = hash string b[i : i + len(s)]
	// Sometimes 2 string have the same hashcode but they are itself both different, this has a propality of 1/ length of string 'a'.
	// Time  = O( len(a) + len(b) * cost of hash function)


	private static final int PRIME = 13;
	private static double hash(String str) {
		double hash = 0;
		for(int i = 0; i <str.length(); i++) {
			hash += str.charAt(i) * Math.pow(PRIME, i);
		}
		return hash;
	}

	private static double updateHash(double hash, char prevChar, char toAdd, int lengthOfString) {
		double newhash = (hash - prevChar) / PRIME;
		newhash = newhash + toAdd * Math.pow(PRIME, lengthOfString - 1);
		return newhash;
	}

	public static void search(String text, String toSearch) {
		int len = toSearch.length();
		double toSearchHash = hash(toSearch);
		double textHash = hash(text.substring(0, len));
		for (int i = 0; i <= text.length() - len; i++) {
			if(toSearchHash == textHash) {
				if(text.substring(i, i+len).equals(toSearch)) {
					System.out.println("Found at index " + i);
				}
			}
			if(i < text.length() - len) {
				textHash = updateHash(textHash, text.charAt(i), text.charAt(i+len), len);
			}
		}
	}
}

class KarpRabin {
	public static void main(String[] args) {
		KarpRabin2 m = new KarpRabin2();
		m.search("pooravfBobWilliam", "William");
	}
}