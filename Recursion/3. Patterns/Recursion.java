class Recursion {
	public static void main(String[] args) {
		pattern(4, 0);
		System.out.println();
		pattern2(4, 0);
		
	}
	static void pattern(int g, int k) {
		if (g == 0 ) {
			return;
		}
		if (k != g) {
			pattern(g, k + 1);
			System.out.print("*" + " ");
		}
		else {
			pattern(g - 1, 0);
			System.out.println();
		}
	}

	static void pattern2(int g, int k) {
		if (g == 0 ) {
			return;
		}
		if (k != g) {
			System.out.print("*" + " ");
			pattern2(g, k + 1);
		}
		else {
			System.out.println();
			pattern2(g - 1, 0);
		}
	}
}