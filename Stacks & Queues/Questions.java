// https://leetcode.com/problems/implement-queue-using-stacks/description/
class QueueUsingStack { // This class is basically a que that is build on stacks, using all the function of stacks but is a queue in real.
	CustomStack first = new CustomStack(); // This CustomStack class was buld in stacks.java file
	CustomStack second = new CustomStack();

	void push (int item) {
		first.push(item);
	}

	int pop()  throws StackException {
		while(!first.isEmpty()) {
			second.push(first.pop());
		}

		int removed = second.pop();

		while (!second.isEmpty()) {
			first.push (second.pop());
		}

		return removed;
	}

	int peek() throws StackException {
		while (!first.isEmpty()) {
			second.push(first.pop());
		}

		int removed = second.peak();

		while (!second.isEmpty()) {
			first.push(second.pop());
		}
		return removed;	
	}

	void display() {
		first.display();
	}
}

// when we have a question regarding sequence, answer till here, sthing in back which can be used later we always use stacks and queues

class Questions {
	public static void main(String[] args) throws StackException {
		QueueUsingStack que = new QueueUsingStack();
		que.push(5);
		que.push(6);
		que.push(7);
		que.pop();
		que.push(8);
		que.push(9);
		que.push(10);
		que.pop();
		que.display();
	}
}