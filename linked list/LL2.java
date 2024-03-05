class LL2{
	Node head;
	Node tail;
	int size;

	LL2(){
		this.size = 0;
	}

	private class Node{
		int value;
		Node next; 

		Node(int value){
			this.value = value;
		}

		Node(int value, Node next){
			this.value = value;
			this.next = next;
		}
	}

	void insertFirst(int val){
		Node temp = new Node(val);
		temp.next = head; 
		head = temp;
		if(tail == null){
			tail = head; 
		}
		size++;
	}

	void insertLast(int val){
		Node temp = new Node(val);
		if(tail == null){
			insertFirst(val);
			return;
		}
		tail.next = temp;
		tail = temp;
		size++;
	}


	Node insert_at_tail(Node head, int value){
		Node n = new Node(value);
		Node temp = head; 
		while(temp.next != null){
			temp = temp.next;
		}
		temp.next = n;
		tail = n; 
		return head;
	}


	void removeDuplicates(){
		Node temp = head; 
		while(temp.next != null){
			if(temp.value == temp.next.value){ 
				temp.next = temp.next.next;
				size--;
			}
			temp = temp.next;
		}
		tail = temp; 
		tail.next = null;
		

	}

	void RecursiveInsertion(int val, int index){
		head = RecursiveInsertion(val, index, head);
	}

	private Node RecursiveInsertion(int val, int index, Node node){
		if(index == 0){
			Node n = new Node(val);
			n.next = node;
			size++;
			return n; 
		}
		Node ne = RecursiveInsertion(val, index - 1, node.next);
		node.next = ne;
		return node;

	}


	 public Node mergeTwoLists(Node list1, Node list2) {
        LL2 ans = new LL2();
        Node tail = ans.tail;
        Node head = ans.head;
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
        return head.next;
        
    }


    boolean hasCycle(Node head){
    	Node slow = head; 
    	Node fast = head; 
    	while(fast != null && fast.next != null){
    		if(fast == slow){
    			return true;
    		}
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	return false;
    }

    int size(Node head){
    	Node temp = head;
    	int size = 0;
    	while(temp != null){
    		size++;
    		temp = temp.next;
    	}
    	return size;
    }

    int get(int index){
    	Node temp = head;
    	for(int i = 0;i<= index;i++){
    		temp = temp.next;
    	}
    	return temp.value;
    }

	// -------------------------------------------------------------------------------------------------------------------------------------------

    void reverse(Node n){
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


    Node reversetal(Node n){ // This reverse can also be done no need to creat another function
    	if(n.next == null){
    		head = n;
    		return n;
    	}
    	tail = reversetal(n.next);
    	tail.next = n;
    	tail = n;
    	tail.next = null; 
    	return n; 
    }

    // https://leetcode.com/problems/reverse-linked-list/
    void IterativeRecursion(Node head){
    	Node temp = head; 
    	Node prev = null;
    	while(temp != null){
    		Node n = temp.next; 
    		temp.next = prev; 
    		prev = temp; 
    		temp = n;
    		display();
    		head = temp;
    	}
    }
	// -------------------------------------------------------------------------------------------------------------------------------------------




	void display(){
		Node temp = head; 
		while(temp != null){
			System.out.print(temp.value+ " -> ");
			temp = temp.next;
		}
		System.out.println("END");
	}


	public static void main(String[] args) {
		LL2 list = new LL2();
		list.insertLast(10);

		list.insertFirst(3);
		list.insertFirst(4);
		list.insertFirst(5);
		list.insertFirst(6);
		list.insertFirst(7);
		list.insertFirst(8);
		list.insertLast(2);
		list.insertLast(2);

		// list.insertLast(11);
		// list.display();

		// System.out.println(list.size);


		// list.removeDuplicates();
		// list.display();

		// System.out.println(list.size);
		System.out.println(list.head.value);
		list.display();

		list.IterativeRecursion(list.head);
		// System.out.println(list.head.value);
		list.display();


	}
}