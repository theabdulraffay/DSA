class BinarySearchTree {
	private static class Node {
		private int val; 
		private int height;
		private Node left, right;

		Node (int val) {
			this.val = val;
		}

		int getValue() {
			return val;
		}


	}
	private static Node root;

	public static int height(Node node){
		if (node == null) {
			return -1;
		}
		return node.height;
	}

	public boolean isEmpty() {
		return root == null;
	}


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void populate(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			insert(nums[i]);
		}
	}

	public static void populateSorted(int[] nums) {
		populateSorted(nums, 0, nums.length);
	}

	public static void populateSorted(int[] nums, int start, int end) {
		if (start >= end) return;

		int mid = start + (end - start) / 2;
		insert(nums[mid]);
		populateSorted(nums, start, mid);
		populateSorted(nums, mid + 1, end);
	}
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void insert (int key) {
		root = insert(key, root);
	}

	private static Node insert (int key, Node node) {
		if (node == null) {
			return new Node(key);
		}
		if (key < node.val) {
			node.left = insert(key, node.left);
		} else {
			node.right = insert(key, node.right);
		}

		node.height = Math.max(height(node.left), height(node.right) + 1);
		return node;
	}

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static  void display() {
		display(root, "Root node: ");
	}

	private static void display(Node node, String details) {
		if (node == null) return;
		System.out.println(details + node.getValue());
		display(node.left, "Left child of " + node.getValue() + ": ");
		display(node.right, "Right child of " + node.getValue() + ": ");
	}

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public static boolean isBalance() {
    	return isBalance(root);
    }

    private static boolean isBalance(Node node) {
    	if (node == null) {
    		return true;
    	}

    	return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalance(node.left) && isBalance(node.right);
    }


    // https://leetcode.com/problems/search-in-a-binary-search-tree/
    // https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    public static void main(String[] args) {
    	// insert(5);
    	// insert(4);
    	// insert(56);
    	// insert(1);
    	// insert(1);
    	// insert(7);
    	populateSorted(new int[] {1,2,3,4,5,6,7,8,9});
    	display();

    }
}