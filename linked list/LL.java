public class LL{
	Node head;
	Node tail;

	int size;

	public  LL(){
		this.size = 0;
	}
	private class Node{
		int value;
		Node next;

		public Node(int value){
			this.value = value;
		}

		public Node(int value, Node next){
			this.value = value;
			this.next = next;
		}
	}


	public void insertFirst(int val){
		Node node = new Node(val);
		node.next = head;
		head = node;
		if(tail == null){
			tail = head;
		}
		size++;
	}

	public void insertLast(int val){
		if(tail == null){
			insertFirst(val);
			return;
		}
		Node node = new Node(val);
		tail.next = node;
		tail = node;
		size++;

	}

	public void insert(int val, int index){
		if(index == 0){
			insertFirst(val);
			return;
		}
		if(index == size){
			insertLast(val);
			return;
		}
		Node temp = head;
		for(int i = 1; i < index; i++){
			temp = temp.next;
		}
		Node node = new Node(val);
		//Node node = new Node(val, temp.next); yah wala constructor bhi use kar sacty han 
		Node n = temp.next;
		temp.next = node;
		node.next =  n;

		size++;
	}

	public int DeleteFirst(){
		int val = head.value;
		if(head == null){
			tail = null;
		}
		head = head.next;
		size--;
		return val;
	}

	public Node get(int index){
		Node n = head;
		for(int i = 0; i < index; i++){
			n = n.next;
		}
		return n;
	}

	public int DeleteLast(){
		int val = tail.value;
		Node temp = get(size - 2);
		tail = temp;
		tail.next = null;
		size--;
		return val;
	}

	public int DeleteIndex(int index){
		Node temp = get(index - 1);
		int val = temp.next.value;
		//Node temp2 = get(index + 1);
		//temp.next = temp2;
		temp.next = temp.next.next;
		return val;


	}
	public void display(){
		Node temp = head;	
		while(temp != null){
			System.out.print(temp.value + "->");
			temp = temp.next;
		}
		System.out.println("END");
	}

	public void display(Node head){
		Node temp = head;	
		while(temp != null){
			System.out.print(temp.value + "->");
			temp = temp.next;
		}
		System.out.println("END");
	}




	//Questions

	//recursion using linked list
	public void insertRec(int val, int index){
		head = insertRec(val, index,head);

	}
	public Node insertRec(int val, int index, Node n){
		if(index == 0){
			Node temp = new Node(val);
			temp.next = n;
			return temp;
		}
		Node te = insertRec(val, index - 1, n.next);
		n.next = te;
		return n;


	}
    // -------------------------------------------------------------------------------------------------------------------------------------------
	//leetcode 0083
	// https://leetcode.com/problems/remove-duplicates-from-sorted-list
	// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii
	public void removeDuplicates(){ //from sorted list
		Node n = head; 
		while(n.next != null){
			if(n.value == n.next.value){
				n.next = n.next.next;
			}else {
				n = n.next;
			}

		}
		tail = n;
		tail.next = null;

	}    

	// -------------------------------------------------------------------------------------------------------------------------------------------

	//merge
	//leetcode 0021
	// https://leetcode.com/problems/merge-two-sorted-lists
	public static Node merge(LL first, LL second) {
		Node f = first.head;
		Node s = second.head;
		LL ans = new LL();
		while(f != null && s != null){
			if(f.value < s.value){
				ans.insertLast(f.value);
				f = f.next;
			}else{
				ans.insertLast(s.value);
				s = s.next;
			}
		}

		while(f != null){
			ans.insertLast(f.value);
			f = f.next;
		}


		while(s != null){
			ans.insertLast(s.value);
			s = s.next;
		}
		return ans.head;

	}
	 public Node merge(Node list1, Node list2) {
        LL ans = new LL();
        Node tail = ans.tail;
		while(list1 != null && list2 != null){
			if(list1.value < list2.value){
				tail.next = list1;
                tail = list1;
				list1 = list1.next;
			}else{
				tail.next = list2;
                tail = list2;
				list2 = list2.next;
			}
		}

		while(list1 != null){
			tail.next = list1;
                tail = list1;
				list1 = list1.next;
		}


		while(list2 != null){
			tail.next = list2;
                tail = list2;
				list2 = list2.next;
		}
		return ans.head;
        
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------
	//fast and slow pointer
	//to find cycle in a list
	//leetcode 141
    // https://leetcode.com/problems/linked-list-cycle/
	//Both these cycle questions were asked in amazon and microsoft
	public boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast)return true;
        }
        return false;
        
        
    }

    public int lengthOfCycle(Node head) {
		Node fast = head;
        Node slow = head;
        int counter = 0;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
            	do{
        			slow = slow.next;
        			counter++;
        		}while(slow != fast);
        		break;
            }
        }
        
        return counter;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------

    //node from where the cycle starts 
    //linked list 142
    // https://leetcode.com/problems/linked-list-cycle-ii
    // https://leetcode.com/problems/linked-list-cycle/
    // https://github.com/theabdulraffay/Leetcode-gdsc/tree/main/0142-Linked-List-Cycle-II

    public Node detectCycle(Node head){
    	int length = lengthOfCycle(head);
    	if(length == 0)return null;
    	Node slow = head;
    	Node fast = head;
    	while(length > 0){
    		slow = slow.next;
    		length--;
    	}
    	while(fast != slow){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	return fast;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------

    // finding the middle node by using just one loop
    // leet code 876
    // https://leetcode.com/problems/middle-of-the-linked-list/description/

    public Node middleNode(Node head){
    	Node fast = head;
    	Node slow = head;
    	while(fast != null && fast.next != null){
    		fast = fast.next.next;
    		slow = slow.next;

    	}
    	return slow;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/insertion-sort-list/description/
    // https://leetcode.com/problems/sort-list
    public Node mergeSort(Node head){ 
    	if(head == null || head.next == null){
    		return head;
    	}
    	Node mid = middleNode(head);
    	Node left = mergeSort(head);
    	Node right = mergeSort(mid);
    	Node temp = merge(right,left);

    	return temp;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------

    void reverse2(Node n){
    	tail = reversetail(n);
    	tail.next = null;
    }


    private Node reversetail(Node n){
    	if(n.next == null){
    		head = n;
    		return n;
    	}
    	Node temp = reversetail(n.next);
    	temp.next = n;
    	return n; 
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------


    void reverse(Node n){ // This reverse can also be done no need to creat another function
    	if(n.next == null){
    		head = n;
    		return;
    	}
    	reverse(n.next);
    	tail.next = n;
    	tail = n;
    	tail.next = null; 
    }

    // https://leetcode.com/problems/reverse-linked-list/
    // https://leetcode.com/problems/reverse-linked-list-ii/description/
    Node reversetal(Node n){ // This reverse can also be done no need to creat another function
    	if(n.next == null){
    		head = n;
    		return n;
    	}
    	tail = reversetail(n.next);
    	tail.next = n;
    	tail = n;
    	tail.next = null; 
    	return n; 
    }


    // -------------------------------------------------------------------------------------------------------------------------------------------

    Node IterativeRecursion(Node head){
    	Node temp = head; 
    	Node prev = null;
    	while(temp != null){
    		Node n = temp.next; 
    		temp.next = prev; 
    		prev = temp; 
    		temp = n;
    	}
    	return prev;



    	// Node temp = head;
    	// Node prev = null; 
    	// Node next = temp.next; 
    	// while(temp != null){
    	// 	temp.next = prev; 
    	// 	prev = temp; 
    	// 	temp = next;
		// 	if(next != null){
    	// 		next = next.next; 
		// 	}    		
    	// }

    	// return prev; if return type is Node. 
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------

    // https://leetcode.com/problems/reverse-linked-list-ii/
    public Node reverseBetween(Node head, int left, int right) {
        if(left == right){
            return head;
        }
        Node current = head;
        Node prev = null;
        for(int i = 0;current != null && i<left - 1;i++){
            prev = current;
            current = current.next;
        }
        Node newEnd = current;
        Node last = prev;
        Node next = current.next;
        for(int i = 0;current != null && i< right - left + 1;i++){
            current.next = prev;
            prev = current;
            current = next;
            if(next != null){
                next = next.next;
            }
        }
        if(last != null){
            last.next = prev;
        }else{
            head = prev;
        }
        newEnd.next = current;
        return head ;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------


    // https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(Node head) {
        // Node fast = head;
        // Node slow = head;
        // while(fast != null && fast.next != null){
        //     fast = fast.next.next;
        //     slow = slow.next;
        // }

        // Node previous = null;
        // Node next = slow.next;
        // while(slow != null){
        //     slow.next = previous;
        //     previous = slow;
        //     slow = next;
        //     if(next != null){
        //         next = next.next;
        //     }
        // }

        Node slow = middleNode(head); // First find the middle node 
        Node previous = IterativeRecursion(slow); // Then reverse the list from the middle, now two lists will be created, keep comparing both of them the head and the previous, until one of them become null. 

        while(head != null && previous != null){
            if(head.value != previous.value){
                return false;
            }
            head = head.next;
            previous = previous.next;
        }
        return true;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------


    // https://leetcode.com/problems/reorder-list/description/
    void reorder(Node head){// This function will rearrange nodes alternatively between first node and last node for example 1->2->3->4->5->6->7 become 1->7->2->6->3->5->4
    	Node fast = head;
    	Node slow = head;
    	while(fast.next !=null && fast.next.next != null){
    		fast = fast.next.next;
    		slow = slow.next;
    	}
    	Node temp = slow.next;
    	slow.next = null;
		Node previous = null;
		Node current = temp; 
		Node next = current.next; 
		while(current != null){
			current.next = previous;
			previous = current;
			current = next;
			if(next != null){
				next = next.next;
			}
		}
		// display(previous);
		// display(head);
		Node arzy = head; 
		while(previous != null){// as this previous node will always be equal or smaller than the head node, so we only take previous in the while loop as it satisfy both for head and previous itself
			Node t = arzy.next; 
			Node m = previous.next;
			arzy.next = previous;
			previous.next = t;
			arzy = t;
			previous = m;
		}
		// display();
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/rotate-list/
    Node getLast(Node head) {
    	Node temp = head; 
    	while(temp.next.next != null){
    		temp = temp.next;
    	}
    	return temp; 
    }

    int length(Node head){
        int c = 0;
        Node temp = head;
        while(temp != null){
            temp = temp.next;
            c++;
        }
        return c;
    }

    void rotating(int k){
    	if(head == null || head.next == null)return;
        int size = length(head);
        k = k%size;
    	for(int i=0; i<k; i++){
    		Node temp = getLast(head);
    	    Node temp2 = temp.next; 
    	    temp.next = null; 
    	    temp2.next = head; 
    	    head = temp2;
    	}
    	display(head);
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
    // https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list/description/
    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/
    // https://leetcode.com/problems/merge-in-between-linked-lists/
    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/remove-linked-list-elements/description/
    public Node removeElements(Node head, int val) {// head = [1,2,6,3,4,5,6], val = 6 // Output: [1,2,3,4,5]
        Node temp = head;
        Node prev = null;
        while(temp!=null){
            if(prev == null && temp.value == val){
                head = head.next;
                temp = temp.next;
                continue;
            }
            else if(temp.value == val){
                while(temp != null && temp.value == val ){
                    temp = temp.next;
                }
                prev.next = temp;
                continue;
                
            }
            prev = temp;
            temp = temp.next;
        }
        return head;
        
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/reverse-nodes-in-k-group/description/
     boolean yes(Node n, int k) {
        for (int i = 0; i < k; i++) {
            if (n == null)
                return false;
            n = n.next;
        }
        return true;
    }


    public Node reverseKGroup(Node head, int k) {// This function will reverse every k nodes // head = [1,2,3,4,5], k = 2 // Output: [2,1,4,3,5]
        Node prev = null;
        Node last = null;
        Node temp = head;
        Node current = head;
        while (temp != null) {
            if (yes(temp, k)) { // this check whether the next k node are avalible ot not, because we can only
                                // swap k nodes, if k nodes are not avalible we donot reverse
                for (int i = 0; i < k; i++) {
                    if (temp != null) {
                        Node n = temp.next;
                        temp.next = prev;
                        prev = temp;
                        temp = n;
                    }
                }
            } else {
                break; 
            }
            if (last == null) {
                head = prev;
                last = prev;
            } else {
                last.next = prev;
                last = prev;
            }
            current.next = temp;
            last = current;
            current = temp;

        }
        return head;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/swap-nodes-in-pairs/
    Node swapInpair(Node head){ // Input: head = [1,2,3,4], Output: [2,1,4,3]
    	Node prev = null;
        Node last = null;
        Node temp = head;
        Node current = head;
        while (current != null) {
            for (int i = 0; i < 2 && temp != null; i++) {
                Node n = temp.next;
                temp.next = prev;
                prev = temp;
                temp = n;
            }
            if (last != null) {
                last.next = prev;
            } else {
                head = prev;
            }
            current.next = temp;
            last = current;
            current = temp;
        }
        return head;
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/delete-node-in-a-linked-list/

    public void deleteNode(Node node) {// Input: head = [1,2,3,4,5], node = 3; Output: [1,2,4,5]
    									   // we have to delete the node given, but for deletion we need a predecessor
                                           // node, as we cannot go back in a Linked List, so we store the value of next
                                           // node in current node and delete the next node ;) (wink emoji)
        node.value = node.next.value;
        node.next  = node.next.next;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------

    // https://leetcode.com/problems/odd-even-linked-list/
    public Node oddEvenList(Node head) { // Input: head = [1,2,3,4,5], Output: [1,3,5,2,4]
        if (head == null || head.next == null)
            return head;
        Node odd = head;
        Node even = head.next;
        Node even2 = head.next; // taking this Node because after the loop when odd is at last we have to
                                    // connect the two node, so we store head.next in a temp variable
        while (odd.next != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = even2;
        return head;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------

	// https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public Node deleteDuplicates(Node head) { // Input: head = [1,2,3,3,4,4,5], Output: [1,2,5]
        Node prev = null;
        Node temp = head;
        while (temp != null && temp.next != null) {
            if (temp.value == temp.next.value) {
                do{
                    temp = temp.next;
                }while(temp.next != null && temp.value == temp.next.value);
                temp = temp.next;
                if (prev != null) {
                    prev.next = temp;
                }else{
                    head = temp;
                }
            } else {
                prev = temp;
                temp = temp.next;
            }
        }
        return head;
    }
    // -------------------------------------------------------------------------------------------------------------------------------------------

    // https://leetcode.com/problems/partition-list/
    public Node partition(Node head, int x) { // Input: head = [1,4,3,2,5,2], x = 3, Output: [1,2,2,4,3,5]
        if (head == null || head.next == null)
            return head;
        Node odd = head, even = head;
        while (odd != null) {// This function will find the first node that is smaller than x, then we will
                             // say this node as head because this is where the LL will start
            if (odd.value < x)
                break;
            odd = odd.next;
        }
        while (even != null) {// This function will find the first node that is greater or equal to x, then we
                              // will keep adding new nodes to this nodes that are greater or equal to this
                              // one
            if (even.value >= x)
                break;
            even = even.next;
        }
        if (odd == null || even == null)
            return head;// this any of these nodes are null this means the there is no element smaller
                        // or greater than x so we return list as it is
        Node newEven = even;// newEven will be the first node greater or equal to x, so after all the
                                // modiications we connect odd with newEven
        Node temp = head;
        head = odd; // as odd is the first node that is smaller than x, so we start the list from
                    // there sayn it as head
        while (temp != null) {
            if (temp == odd || temp == even) {
                temp = temp.next;
                continue;
            } else if (temp.value < x) {
                odd.next = temp;
                odd = temp;
            } else if (temp.value >= x) {
                even.next = temp;
                even = temp;
            }
            temp = temp.next;
        }
        odd.next = newEven;
        even.next = null;// we make this null to complete(null the tail.next) the list other wise it
                         // forms a circle
        return head;

    }

	public static void main(String[] args) {
		LL list = new LL();
		list.insertLast(1);
		list.insertLast(2);
		list.insertLast(3);
		list.insertLast(4);
		list.insertLast(5);
		list.insertLast(6);
		list.insertLast(7);
		list.display();
		

		
	}
}