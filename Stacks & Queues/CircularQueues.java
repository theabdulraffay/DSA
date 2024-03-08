class CircularQueue {
	protected int data[];
	private static final int DEFAULT_QUEUE_SIZE = 10;
	int end = 0;
	int front = 0;
	int size = 0;

	CircularQueue(){
		this(DEFAULT_QUEUE_SIZE);
	}

	CircularQueue(int size){
		data = new int[size];
	}

	boolean isEmpty() {
		return size == 0;
	}

	boolean isFull(){
		return size == data.length;
	}

	void push(int value) throws StackException{
		if(isFull()){
			throw new StackException("Queue is Full!");
		}

		data[end++] = value;
		end  = end % data.length;
		// end++;
		size++;
	}

	void display(){
		int temp = front;
		do{
			System.out.print(data[temp++] + " ");
			temp = temp%data.length;
		}while(temp != end);
		System.out.println("END");
	}

	int pop() throws StackException{
		if(isEmpty()){
			throw new StackException("Queue is empty!");
		}
		int removed = data[front++];
		front = front % data.length;
		size--;
		return removed;
	}

	int head(){
		return data[front];
	}

	int tail() {return data[end];}
}
class CircularQueues{
	public static void main(String[] args) throws StackException {
		DynamicQueue que = new DynamicQueue(5);
		que.push(1);
		que.push(2);
		que.push(3);
		que.push(4);
		que.push(5);
		que.display();
		System.out.println(que.pop());
		que.push(100);
		que.display();
		que.push(101);
		que.push(102);
		que.push(103);
		que.display();
		que.pop();
		que.pop();
		que.pop();
		que.pop();
		que.pop();
		que.pop();
		que.pop();
		que.push(281);
		que.push(281);
		que.push(281);
		que.push(281);
		que.push(281);
		que.display();
	}

}