class BST {
	private class TreeNode {
		private int val; 
		private TreeNode left, right;

		TreeNode (int val) {
			this.val = val;
		}

		int getValue() {
			return val;
		}


	}
	private TreeNode root;

	public void insert(int key) {
		root = insert(key, root);
	}
	private TreeNode insert (int key, TreeNode TreeNode) {
		if (TreeNode == null) {
			return new TreeNode(key);
		}
		if (key < TreeNode.val) {
			TreeNode.left = insert(key, TreeNode.left);
		} else {
			TreeNode.right = insert(key, TreeNode.right);
		}

		return TreeNode;
	}

	public void display() {
		display(root);
	}
	private void display(TreeNode n) {
		if (n == null) {
			return;
		}
		System.out.print(n.val + " ");
		display(n.left);
		display(n.right);
	}
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

	// https://leetcode.com/problems/diameter-of-binary-tree/description/
	int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;   
    }
    int height(TreeNode node) { // POST - ORDER traversal
        if (node == null) return 0;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        int currentDiameter =  rightHeight + leftHeight;
        diameter = Math.max(diameter,currentDiameter);
        return Math.max(leftHeight, rightHeight) + 1;
    }


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) { // Using PRE - ORDER // IMPORTANT QUESTION // GOOGLE QUESTION
        if(root == null) return null;
        TreeNode temp = root.left; // first changes the root node
        root.left = root.right;
        root.right = temp;
        invertTree(root.left); // then move to the left
        invertTree(root.right);
        return root;
    }

    public TreeNode invertTree2(TreeNode root) { // Using POST - ORDER
        if(root == null) return null;
        TreeNode left = invertTree(root.left); // first move to the left
        TreeNode right = invertTree(root.right);
        root.left = right; // then changes the root node
        root.right = left;
        return root;

        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) { // GOOGLE QUESTION // post order traversal
        if (root == null) return 0;
        int n = maxDepth(root.left);
        int m = maxDepth(root.right);
        return Math.max(n, m) + 1;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
    public TreeNode sortedArrayToBST(int[] nums) {
        return insert(nums, 0, nums.length -1);
    }

    TreeNode insert(int[] nums, int st, int end) {
        if(st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        TreeNode root = new TreeNode(nums[mid]); 
        root.left = insert(nums, st, mid - 1);
        root.right = insert(nums, mid + 1, end);
        return root;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

}