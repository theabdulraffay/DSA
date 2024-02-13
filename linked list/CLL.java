public class CLL{
	Node head;
	Node tail;
	int size;

	public CLL(){
		this.size = size;
		this.head = null;
		this.tail = null;
	}

	class Node{
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

	public void insert(int val){
		Node n = new Node(val);
		if(head == null){
			head = n;
			tail = n;
		}

		tail.next = n;
		n.next = head;
		tail = n; 
		size++;
	}

	public void delete(int val){
		Node node = head;
		if(node == null){
			return;
		}

		if(node.value == val){
			head = head.next;
			tail.next = head;
			return;
		}

		while(node.next.value != val){
			node = node.next;
		}
		node.next = node.next.next;

	}

	public void display(){
		Node temp = head;
		// for(int i = 0; i < size; i++){
		// 	System.out.print(temp.value + "->");
		// 	temp = temp.next;
		// }

		do{
			System.out.print(temp.value + " -> ");
			temp = temp.next;
		}while(temp != head);				
		System.out.println("END");
		//while(temp != null){
			
	}


	public static void main(String[] args) {
		CLL list = new CLL();
		list.insert(0);
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.display();
		list.delete(3);
		list.display();

		
	}
}