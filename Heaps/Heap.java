import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
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

	//-------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/kth-largest-element-in-an-array/
	public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int i : nums) {
            q.add(i);
        }

        System.out.println(q);
        while(k-- > 1) {
            q.poll();
        }
        return q.peek();   
    }

	//-------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/smallest-number-in-infinite-set/
	
    // class SmallestInfiniteSet {

//     PriorityQueue<Integer> q;
//     public SmallestInfiniteSet() {
//         q = new PriorityQueue<>(); 
//         for (int i = 1; i <= 1000; i++) {
//             q.add(i);
//         }
//     }
    
//     public int popSmallest() {
//         return q.poll();
        
//     }
    
//     public void addBack(int num) {
//         if(!q.contains(num)) {
//             q.add(num);
//         }
        
//     }
// }

	class SmallestInfiniteSet {
	    int k;
	    PriorityQueue<Integer> q;

	    public SmallestInfiniteSet() {
	        q = new PriorityQueue<>();
	        k = 0;
	    }
	    
	    public int popSmallest() {
	        if(!q.contains(++k)) {
	            q.add(k);
	        }
	        return q.poll();   
	    }

	    public void addBack(int num) {
	        if(num <= k && !q.contains(num)) {
	            q.add(num);
	        }   
	    }
	}	


	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/total-cost-to-hire-k-workers/
    public long totalCost(int[] costs, int k, int candidates) {
        if(costs.length == 1) return costs[0];
        PriorityQueue<Integer> front = new PriorityQueue<>();
        PriorityQueue<Integer> back = new PriorityQueue<>();
        int head = 0;
        int tail = costs.length - 1;
        while(head < tail && candidates > 0) {
            front.add(costs[head]);
            back.add(costs[tail]);
            head++;
            tail--;
            candidates--;
        }
        long sum = 0;

        while(k > 0 && front.size() > 0 && back.size() > 0) {
            if(front.peek() <= back.peek()) {
                sum += front.poll();
                if(head <= tail) {
                    front.add(costs[head++]);
                }
            } else {
                sum += back.poll();
                if(head <= tail) {
                    back.add(costs[tail--]);
                }
            }
            k--;
        }

        while(k > 0 && front.size() > 0 ) {
            sum += front.poll();
            k--;
        }

        while(k > 0 && back.size() > 0 ) {
            sum += back.poll();
            k--;
        }

        return sum;
        
    }
}

	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------


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
		// h.display();
		// System.out.println(h.delete());
		// System.out.println(h.delete());
		// System.out.println(h.delete());
		// System.out.println(h.delete());
		// h.display();
		// System.out.println(h.heapSort());
		h.findKthLargest(new int[]{5,2,6,8,1,2}, 5);





	}


}