// import java.util.Stack;
// import java.util.Queue;
// import java.util.LinkedList;

class CustomStack{
	protected int[] data;
	private static final int DEFAULT_SIZE = 10;
	private int ptr = -1;

	public CustomStack(){
		this(DEFAULT_SIZE);
	}
	public CustomStack(int size){
		data = new int[size];
	}

	public boolean isEmpty(){
		return ptr == -1;
	}

	public boolean isFull(){
		return ptr == data.length - 1;
	}

	public boolean push(int item){
		if(isFull()){
			return false;
		}

		ptr++;
		data[ptr] = item;
		return true;
	}

	public int pop() throws Exception {
		if(isEmpty()){
			throw new Exception("Stack is Empty!!");
		}
		// int remove = data[ptr];
		// data[ptr] = 0;
		// ptr--;
		// return remove;
		return data[ptr--];
	}

	public int peak() throws Exception{
		if(isEmpty()){
			throw new Exception("Stack is Empty!!");
		}
		return data[ptr];
	}

}
public class Stacks {
	public static void main(String[] args) {
		// Stack<Integer> stack = new Stack<Integer>();		
		// stack.push(23);
		// stack.push(43);
		// stack.push(45);
		// stack.push(56);
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());


		// Queue<Integer> queue = new LinkedList<>();
		// queue.add(3);
		// queue.add(5);
		// queue.add(7);
		// queue.add(9);

		// System.out.println(queue.remove());
		// System.out.println(queue.remove());
		// System.out.println(queue.remove());
		// System.out.println(queue.remove());
	}
}