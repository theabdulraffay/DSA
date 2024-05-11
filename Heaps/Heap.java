import java.util.ArrayList;
class MaxHeap {
	// collection of item get retrieve in log(N) time
	// parent[i] = (i - 1) / 2;
	// left[i] = 2(i) + 1
	// right[i] = 2(i) + 2
	int[] list;
	int ptr;

	MaxHeap() {
		list = new int[20];
		ptr = -1;
	}

	boolean isFull() {
		return ptr == list.length;
	}
	boolean isEmpty() {
		return ptr == -1;
	}
	int parent(int i) {
    	return (i - 1) / 2;
	}

	int right(int i) {
		return 2 * i + 2;
	}

	int left(int i) {
		return 2 * i + 1;
	}

	void swap(int n, int m) {
		
	}

	void insert(int key) {
		if(isFull()) {
			System.out.println("Full");
			return;
		}
		list[++ptr] = key;
		upheap(ptr);
		// int parent = (ptr - 1) / 2;
		// int tempptr = ptr;
		// while(list[parent] < list[tempptr]) {
		// 	int temp = list[parent];
		// 	list[parent] = list[tempptr];
		// 	list[tempptr] = temp;

		// 	tempptr = parent;
		// 	parent = (tempptr - 1) / 2;
		// }
	}

	void upheap(int index) {
		if(index == 0) return;
		int parent = parent(index);
		if(list[parent] < list[index]) {
			int temp = list[parent];
			list[parent] = list[index];
			list[index] = temp;
			upheap(parent);
		}

	}

	int delete() {
		if(isEmpty()) {
			System.out.println("Empty");
			return -1;
		}
		int te = list[0];
		list[0] = list[ptr--];
		downHeap(0);
		return te;
	}

	void downHeap(int index) {
		if(index > ptr) {
			return;
		}
		int left = left(index);
		int right = right(index);
		int toSwap = index;
		if(right <= ptr && list[left] < list[right]) {
			toSwap = right;

		} else if (right <= ptr && list[left] > list[right]){
			toSwap = left;
		} else if(left <= ptr && list[index] < list[left]) {
			toSwap = left;
		}

		if(toSwap != index) {
			int temp = list[toSwap];
			list[toSwap] = list[index];
			list[index] = temp;
			downHeap(toSwap);
		}

	}

	void display() {
		for (int i = 0; i <= ptr; i++) {
			System.out.print(list[i] + " ");
		}
		System.out.println();
	}

	public ArrayList<Integer> heapSort() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(!isEmpty()) {
			list.add(delete());
		}
		return list;
	}
	
}

class Heap {
	public static void main(String[] args) {
		MaxHeap h = new MaxHeap();
		h.insert(10);
		h.insert(8);
		h.insert(9);
		h.insert(5);
		h.insert(12);
		h.insert(13);
		h.insert(11);
		h.insert(14);
		h.insert(18);
		h.insert(37);
		h.display();
		System.out.println(h.delete());
		System.out.println(h.delete());
		System.out.println(h.delete());
		System.out.println(h.delete());
		h.display();
		System.out.println(h.heapSort());





	}


}