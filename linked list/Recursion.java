public class Recursion{
	Node head;
	Node tail;
	int size;

	Recursion(){
		this.size = 0;
	}

	class Node{
		int value;
		Node next; 

		Node(int value, Node next){
			this.value = value;
			this.next = next;
		}

		Node(int value){
			this.value = value;
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

	public void display(){
		Node n = head;
		while(n != null){
			System.out.print(n.value + " -> ");
			n = n.next; 
		}
		System.out.println("END");
	}

	public void insertindex(int val, int index){
		Node n = new Node(val);
		Node temp = head; 
		for(int i=1; i<index;i++){
			temp = temp.next;
		}
		n.next = temp.next;
		temp.next = n; 
		size++;

	}

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

	public static void main(String[] args) {
		Recursion list = new Recursion();
		list.insertFirst(467);
		list.insertFirst(0);
		list.insertFirst(1);
		list.insertFirst(2);
		list.insertFirst(3);

		list.display();
		list.insertRec(21,0);
		list.display();


	}
}