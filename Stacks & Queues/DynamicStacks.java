class DynamicStacks extends CustomStack{
	public DynamicStacks() {
		super(); // it will call CustomStack() constructor or parent constructor which takes no argument
	}
	public DynamicStacks(int size){
		super(size); // this will call the constructor that takes an int argument of parent class 
	}

	@Override
	public boolean push(int item) {
		if(this.isFull()){
			int[] temp = new int[data.length * 2];

			for (int i = 0; i < data.length; i++) {
				temp[i] = data[i];
				
			}
			data = temp;
		}

		// At this point we are sure that the stack is not full
		return super.push(item);
	}
}