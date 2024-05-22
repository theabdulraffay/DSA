class Heap {
	int h = 0;
	static int i = 1;
	private Heap() {}

	public static Heap h() {
		if(i < 2) {
			i++;
			return new Heap();
		} else {
			return null;
		}
	}
}

class Temp {
	public static void main(String[] args) {
		Heap h = Heap.h();
		Heap s = Heap.h();
		System.out.println(h == null);
		System.out.println(s == null);
	}
}