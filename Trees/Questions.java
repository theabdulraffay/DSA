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
    public List<Double> averageOfLevels(Node root) {
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
		
	}
}