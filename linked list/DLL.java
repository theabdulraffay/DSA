import java.util.*;
public class DLL{
	Node head;
	Node tail;
	int size;
	public DLL(){
		this.size = 0;
	}

	class Node{
		int value;
		Node next;
		Node previous;
		Node(int val){
			this.value = val;
		}
		Node(int val, Node next, Node previous) {
			this.value = val;
			this.next = next;
			this.previous = previous;
		}
	}


	public Node get(int index){
		Node temp = head;
		for(int i = 0; i < index; i++){
			temp = temp.next;
		}
		return temp;}


	public void insertFirst(int val){
		Node node = new Node(val);
		node.next = head; 
		node.previous = null;
		if(head != null){
			head.previous = node;
		}
		head = node;
		// Node node = new Node(val, head, null)   -------> this can aso be used to creat this node 
		size++;
		tail = get(size - 1);
	}

	public void insertLast(int val){
		Node node = new Node(val);
		if(head == null){
			head = node;
			head.previous = null;
		}
		node.previous = tail;
		node.next = null;
		if(tail != null){
			tail.next = node;

		}
		tail = node;
		size++;
	}

	public void insertAtIndex(int index, int val){
		if(index == 0){
			insertFirst(val);
			return;
		}
		if(index == size){
			insertLast(val);
			return;
		}
		Node node = new Node(val);
		Node temp = get(index - 1);
		Node n = temp.next;
		temp.next = node;
		node.next = n;
		n.previous = node;
		node.previous = temp;
		size++;


	}
	public void DisplayFromLast(){ //This will print the list in opposite direction
		Node temp = get(size - 1);
		while(temp != null){
			System.out.print(temp.value + "->");
			temp = temp.previous;
		}
		System.out.println("END");
	}

	public void Display(){
		Node temp = head;
		while(temp != null){
			System.out.print(temp.value + "->");
			temp= temp.next;
		}
		System.out.println("END");
	}


	public static void main(String[] args) {
		DLL list = new DLL();
		// list.insertFirst(1);
		// list.insertFirst(2);
		// list.insertFirst(3);
		// list.insertFirst(4);
		// list.insertFirst(5);
		// list.insertFirst(6);
		// list.insertFirst(7);
		// list.insertFirst(8);
		// list.insertFirst(9);
		System.out.println(list.head);
		list.insertLast(32);
		list.insertLast(45);
		list.insertAtIndex(1,23);
		list.Display();
		list.DisplayFromLast();
		System.out.println(list.size);
		System.out.println(list.tail.value);
		System.out.println(list.tail.next);

	}
}