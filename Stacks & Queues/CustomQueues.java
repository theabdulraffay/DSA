class CustomQueue{
	protected int data[];
	private static final int DEFAULT_QUEUE_SIZE = 10;
	int ptr = 0;

	CustomQueue(){
		this(DEFAULT_QUEUE_SIZE);
	}
	CustomQueue(int size){
		data = new int[size];
	}

	boolean isEmpty() {
		return ptr == 0;
	}

	boolean isFull(){
		return ptr == data.length;
	}

	void push(int v) throws StackException{
		if(isFull()){
			throw new StackException("Queue is full!");
		}
		for(int i = ptr++; i > 0; i--){
			data[i] = data[i - 1];
		}
		data[0] = v;
	}

	int pop() throws StackException {
		if(isEmpty()){
			throw new StackException("Queue is empty!");
		}
		return data[--ptr];
	}

	void display(){
		for (int i = ptr-1; i>=0 ; i-- ) {
			System.out.print(data[i] + " <- ");
		}
		System.out.println("END");
	}
}
class CustomQueues {
	public static void main(String[] args) throws StackException {
		CustomQueue que = new CustomQueue();
				que.display();

		que.push(5);
		que.push(6);
		que.push(7);
		que.push(8);
		que.push(9);
		que.display();

		System.out.println(que.pop());
		que.display();

		// System.out.println(que.pop());
		// System.out.println(que.pop());
		// System.out.println(que.pop());
		// System.out.println(que.pop());
	}

}