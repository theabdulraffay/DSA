import java.util.*;
/*
Breath first Search
Travelling in a binary tree level by level


*/

class BST {
	private class Node {
		private int val; 
		private Node left, right;

		Node (int val) {
			this.val = val;
		}

		int getValue() {
			return val;
		}


	}
	private Node root;

	public void insert(int key) {
		root = insert(key, root);
	}
	private Node insert (int key, Node node) {
		if (node == null) {
			return new Node(key);
		}
		if (key < node.val) {
			node.left = insert(key, node.left);
		} else {
			node.right = insert(key, node.right);
		}

		return node;
	}

	public void display() {
		display(root);
	}
	private void display(Node n) {
		if (n == null) {
			return;
		}
		System.out.print(n.val + " ");
		display(n.left);
		display(n.right);
	}

// -----------------------------------------------------------------------------------------------------------------------------

	// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
	public List<List<Integer>> levelOrder(){
		return levelOrder(root);
	}
	public List<List<Integer>> levelOrder(Node root) {
     	List<List<Integer>> list = new ArrayList<List<Integer>>(); // Main returnig list
     	if(root == null) return list;
     	Queue<Node> queue = new LinkedList<Node>(); // This will maintain the left to right all the nodes of the a same level
     	queue.add(root);

     	while(!queue.isEmpty()) {
     		List<Integer> currentLevel = new ArrayList<Integer>();
     		int levelSize = queue.size(); // At this step the size of the queue is exactly same as that of the level of the binary tree
     		for(int i = 0; i < levelSize; i++) { // so we run the loop the number of nodes that this particular level has and add those nodes to the list
     			Node temp = queue.poll();
     			currentLevel.add(temp.val);
     			if(temp.left != null) queue.add(temp.left);
     			if(temp.right != null) queue.add(temp.right);
     		}
     		list.add(currentLevel);
     	}
     	return list;
    }


// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
    public List<List<Integer>> levelOrderBottom(Node root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
     	if(root == null) return list;
     	Queue<Node> queue = new LinkedList<Node>();
     	queue.add(root);

     	while(!queue.isEmpty()) {
     		List<Integer> currentLevel = new ArrayList<Integer>();
     		int levelSize = queue.size(); 
     		for(int i = 0; i < levelSize; i++) { 
     			Node temp = queue.poll();
     			currentLevel.add(temp.val);
     			if(temp.left != null) queue.add(temp.left);
     			if(temp.right != null) queue.add(temp.right);
     		}
     		list.add(0, currentLevel); // we just add the new currentLevel ist to the first position if the resulting list in this way we get the resulting list as the list that contains all the levels starting from the bottom of the root node
     	}
     	return list;       
    }

	// ------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/average-of-levels-in-binary-tree/
    public List<Double> averageOfLevels(Node root) { // GOOGLE QUESTION
        List<Double> list = new ArrayList<>();
     	if(root == null) return list;
     	Queue<Node> queue = new LinkedList<Node>();
     	queue.add(root);

     	while(!queue.isEmpty()){
            double sumOfLevel = 0;
     		int levelSize = queue.size(); 
     		for(int i = 0; i < levelSize; i++) {
     			Node temp = queue.poll();
     			sumOfLevel += (temp.val);
     			if(temp.left != null) queue.add(temp.left);
     			if(temp.right != null) queue.add(temp.right);
     		}
            double avg = sumOfLevel / levelSize;
     		list.add(avg);
     	}
     	return list;
        
    }

// -----------------------------------------------------------------------------------------------------------------------------
    public int levelOrderSuccessor(int key) { // GOOGLE QUESTION // This will return the value of the node which is present at the right side of that node again we use queue to store levels of binary tree, when we find that particular node of value kep we just retrieve the value which is next to it (value to the right of the node, if the right node is null we just return the left node of one step lower level of binary tree)
     	if(root == null) return -1;
     	Queue<Node> queue = new LinkedList<Node>();
     	queue.add(root);

     	while(!queue.isEmpty()){
     		Node temp = queue.poll();
     		if(temp.left != null) queue.add(temp.left);
     		if(temp.right != null) queue.add(temp.right);
     		if(temp.val == key) break;
     		
     	}
     	return queue.peek().val;
    }

// -----------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
    public List<List<Integer>> zigzagLevelOrder() { // AMAZON, GOOGLE, META, MICROSFT QUESTION, Also known as spiral Level Order 
    // In this we travel the Binary tree in zig zag pattern, so we just reverse every second list before we add it to the main list;
    	List<List<Integer>> list = new ArrayList<>();
    	Queue<Node> queue = new LinkedList<>();
    	queue.add(root);
    	if (root == null) return list;
    	int reverse = 1;

    	while (!queue.isEmpty()) {
    		int levelSize = queue.size();
    		List<Integer> level = new ArrayList<>(levelSize);
    		for (int i = 0; i < levelSize; i++) { // this for loop will always run number of node in a level time
    			Node temp = queue.poll();
    			level.add(temp.val);
    			if(temp.left != null) queue.add(temp.left);
    			if(temp.right != null) queue.add(temp.right);
    		}

    		if (reverse == 1) {
    			reverse = 0;
    		} else {
    			Collections.reverse(level);
    			reverse = 1;
    		}
    		list.add(level);
    	}
    	return list;   
    }


    public List<List<Integer>> zigzagLevelOrder2() { // The same problem can be solved using deque
    	List<List<Integer>> list = new ArrayList<>();
    	Deque<Node> queue = new LinkedList<>();
    	queue.add(root);
    	if (root == null) return list;
    	boolean reverse = false;

    	while (!queue.isEmpty()) {
    		int levelSize = queue.size();
    		List<Integer> level = new ArrayList<>(levelSize);
    		for (int i = 0; i < levelSize; i++) { 
    			if (!reverse) {
    				Node temp = queue.pollFirst();
	    			level.add(temp.val);
	    			if(temp.left != null) queue.addLast(temp.left);
	    			if(temp.right != null) queue.addLast(temp.right);
    			} else { // for every second iteration instead of removing element from the first position we just remove from the last in this was a reverse list is added to the main list
    				Node temp = queue.pollLast();
	    			level.add(temp.val);
	    			if(temp.right != null) queue.addFirst(temp.right);
	    			if(temp.left != null) queue.addFirst(temp.left);
    			}
    		}
    		reverse = !reverse;
    		list.add(level);
    	}
    	return list;   
    }

// -----------------------------------------------------------------------------------------------------------------------------





}

// -----------------------------------------------------------------------------------------------------------------------------




class Questions {
	public static void main(String[] args) {
		BST tree= new BST();
		tree.insert(5);
		tree.insert(3);
		tree.insert(4);
		tree.insert(2);
		tree.insert(1);
		tree.insert(8);
		tree.insert(6);
		tree.insert(7);
		tree.insert(9);
		tree.insert(10);
		tree.display();
		List<List<Integer>> ins = tree.levelOrder();
		for(List<Integer> i : ins) {
			System.out.println(i);
		}


		System.out.println();
		List<List<Integer>> in = tree.zigzagLevelOrder2();
		for(List<Integer> i : in) {
			System.out.println(i);
		}
		// System.out.println(tree.levelOrderSuccessor(7));
		
	}
}