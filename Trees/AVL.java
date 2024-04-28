/*
Self balancing binary tree
Adelson Velski and landis Tree (AVL Tree)
it will convert unbalanced tree to balanced




How to rotate a tree to balanced tree:
Case 1: (left - left)
If child and grandchild both are to the left we just do right rotate (ScreenShot Image: PCG all left)

Case 2: (left - right)
if child is to the left and grandchild to the right, we first rotate left the child node and then this will form a straight line as in first case, we then rotate right the parent node (ScreenShot Image: 2. C left, GC right)

Case 3: (right - right)
If child and GC both are to the right, so we j rotate left (ScreenShot Image: PCG all right)

Case 4: (right - left)
If child is to the right and GC to the left, we rotate right the child node and then apply case 3 as it will form a straight line to the right side (ScreenShot Image: C right, GC left)

*/


class AVL2 {
	private  class Node {
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
	private  Node root;

	public  int height() {
		return height(root);
	}

	public  int height(Node node){
		if (node == null) {
			return -1;
		}
		return node.height;
	}

	public boolean isEmpty() {
		return root == null;
	}


    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

	public  void populate(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			insert(nums[i]);
		}
	}

	public  void populateSorted(int[] nums) {
		populateSorted(nums, 0, nums.length);
	}

	public  void populateSorted(int[] nums, int start, int end) {
		if (start >= end) return;

		int mid = start + (end - start) / 2;
		insert(nums[mid]);
		populateSorted(nums, start, mid);
		populateSorted(nums, mid + 1, end);
	}
    //----------------------------------------------------------------------------------------------------------------------------------------------------------------
	public  void insert (int key) {
		root = insert(key, root);
	}

	private  Node insert (int value, Node node) {
		if (node == null) {
      node = new Node(value);
      return node;
    }

    if (value < node.val) {
      node.left = insert(value, node.left);
    }

    if (value > node.val) {
      node.right = insert(value, node.right);
    }

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    return rotate(node);
	}


	 private Node rotate(Node node) {
		if (height(node.left) - height(node.right) > 1) {
			// This means it is left heavy 
			if (height(node.left.left) - height(node.left.right) > 0) {
				// This is left - left case (case 1)
				return rightRotate(node);
			}
			if (height(node.left.left) - height(node.left.right) < 0) {
				// This is left - right case (case 2)
				node.left = leftRotate(node.left);
				return rightRotate(node);
			}
		}


		if (height(node.left) - height(node.right) < -1) {
			// This means it is right heavy 
			if (height(node.right.right) - height(node.right.left) > 0) {
				// This is right - right case (case 3)
				return leftRotate(node);
			}
			if (height(node.right.right) - height(node.right.left) < 0) {
				// This is right - left case (case 4)
				node.right = rightRotate(node.right);
				return leftRotate(node);
			}
		}
		return node;
	}

	 public Node rightRotate(Node node) {
		Node child = node.left; 
		Node t = child.right; 
		child.right = node; 
		node.left = t; 

		node.height = Math.max(height(node.left), height(node.right) + 1);
		child.height = Math.max(height(child.left), height(child.right) + 1);
		return child; 
	}


	 public Node leftRotate(Node node) {
		Node child = node.right; 
		Node t = child.left; 
		child.left = node; 
		node.right = t; 

		node.height = Math.max(height(node.left), height(node.right) + 1);
		child.height = Math.max(height(child.left), height(child.right) + 1);
		return child; 
	}

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

	public   void display() {
		display(root, "Root node: ");
	}

	private  void display(Node node, String details) {
		if (node == null) return;
		System.out.println(details + node.getValue());
		display(node.left, "Left child of " + node.getValue() + ": ");
		display(node.right, "Right child of " + node.getValue() + ": ");
	}

    //----------------------------------------------------------------------------------------------------------------------------------------------------------------

    public  boolean isBalance() {
    	return isBalance(root);
    }

    private  boolean isBalance(Node node) {
    	if (node == null) {
    		return true;
    	}

    	return Math.abs(height(node.left) - height(node.right)) <= 1 && isBalance(node.left) && isBalance(node.right);
    }

    public void prettyDisplay () {
		prettyDisplay(root, 0);
	}

	private void prettyDisplay (Node n, int indent) {
		if (n == null) {
			return;
		}
		prettyDisplay(n.right, indent + 1);
		if (indent != 0) {
			for (int i = 0; i < indent ; i++) {
				System.out.print("\t\t");
			}

			System.out.println("|-------->" + n.val);
		} else {
			System.out.println(n.val);
		}

		prettyDisplay(n.left, indent + 1);

	}
    // public  void main(String[] args) {
    // 	// insert(5);
    // 	// insert(4);
    // 	// insert(56);
    // 	// insert(1);
    // 	// insert(1);
    // 	// insert(7);
    // 	// populateSorted(new int[] {1,2,3,4,5,6,7,8,9});
    // 	for (int i = 0; i < 1000; i++) {
    // 		insert(i);
    // 	}
    // 	// display();
    // 	System.out.println(height(root));

    // }

}

class AVL {
	public static void main(String[] args) {
    AVL2 tree = new AVL2();

    for(int i=0; i < 50; i++) {
      tree.insert(i);
    }

    System.out.println(tree.height());
    tree.prettyDisplay();
  }
}