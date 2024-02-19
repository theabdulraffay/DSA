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
	public void Display(){
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

	//leetcode 0083
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

	//merge
	//leetcode 0021
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


	//fast and slow pointer
	//to find cycle in a list
	//leetcode 141

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


    //node from where the cycle starts 
    //linked list 142
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

    void IterativeRecursion(Node head){
    	Node temp = head; 
    	Node prev = null;
    	while(temp != null){
    		Node n = temp.next; 
    		temp.next = prev; 
    		prev = temp; 
    		temp = n;
    	}



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

	public static void main(String[] args) {
		LL list = new LL();
		list.insertLast(2);
		list.insertLast(3);
		list.insertLast(3);
		list.insertLast(5);
		list.insertLast(6);
		list.insertLast(6);
		list.insert(55,2);
		list.Display();
		list.removeDuplicates();
		list.Display();
		

		list.reverse(list.head);
		list.Display();
	}
}