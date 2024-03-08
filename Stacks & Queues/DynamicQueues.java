class DynamicQueue extends CircularQueue {
    DynamicQueue() {
        super();
    }

    DynamicQueue(int size) {
        super(size);
    }

    @Override

    void push(int value) throws StackException {
    	if(isFull()){
    		int[] temp = new int[data.length * 2];
    		int t = front, i = 0;

    		do{
    			temp[i++] = data[t++];
    			t = t%data.length;
    		}while(t!= end);

    		front = 0;
    		end = data.length;
    		data = temp;
    	}

    	super.push(value);
    }
}
