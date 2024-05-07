import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
    // https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
    public void flatten(TreeNode root) { // FACEBOOK QUESTION // put all the nodes to the right of the root node and null the left node for each node
        if(root == null) return;
        while(root.left != null || root.right != null) {
            if(root.left != null) {
                TreeNode temp = root.right;
                TreeNode t = root.left;
                while(t.right != null) {
                    t = t.right;
                }
                root.right = root.left;
                t.right = temp;
                root.left = null;
            }
            root = root.right;
        }
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/validate-binary-search-tree/description/
    boolean valid (TreeNode node, int val, boolean left) { // For each node it will check both sides and return boolean value respectively
        if(node == null) return true;
        if(left) {
            if(node.val >= val) {
                return false;
            }
        } else {
            if(node.val <= val) {
                return false;
            }
        }
        boolean leftb = valid(node.left, val, left);
        boolean right = valid(node.right, val, left);
        return leftb && right;
    }
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        boolean left = valid(root.left, root.val, true); // We check for each node left and right and repeat this process for every node
        boolean right = valid(root.right, root.val, false);

        boolean f = left && right;
        boolean l = isValidBST(root.left);
        boolean r = isValidBST(root.right);
        return f & l & r;
        
    }

    public boolean isValidBST2(TreeNode root) {
        return valid(root, null, null);
        
    }

    boolean valid (TreeNode node, Integer low, Integer high) { // For each node it will check both sides and return boolean value respectively
        if(node == null) return true;
        if (low != null && node.val >= low) {
            return false;
        }

        if (high != null && node.val <= high) {
            return false;
        }
        boolean left = valid(node.left, node.val, high);
        boolean right = valid(node.right, low, node.val);
        return left && right;
    }
    
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // Returns the lowest ansestor for p and q
        if (root == null) return null;
        if(root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        else if (left != null) return left;
        return right;
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(TreeNode root, int k) {// FACEBOOK, AMAZON, GOOGLE // returns kth smallest element in BST using IN ORDER TRAVERSAL
        List<Integer> list = new ArrayList<>();
        insert(root, list);
        return list.get(k - 1);
        
    }

    void insert(TreeNode root, List<Integer> list) {
        if(root == null) return;
        insert(root.left, list);
        list.add(root.val);
        insert(root.right, list);
    }


    public int kthSmallest2(TreeNode root, int k) {
        return helper(root, k).val;
        
    }
    int counter = 0;
    TreeNode helper(TreeNode root, int k) {
        if(root == null) {
            return root;
        }

        TreeNode left = helper(root.left, k);
        if(left != null) return left;
        counter++;
        if(counter == k){
            return root;
        }
        return helper(root.right, k);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null; 
        if(preorder.length == 1) return new TreeNode(preorder[0]);
        int r = preorder[0];
        TreeNode root = new TreeNode(r);
        int index = 0;
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == r) {
                index = i; 
                break;

            }
        }

        root.left = buildTree(Arrays.copyOfRange(preorder, 1, index + 1), Arrays.copyOfRange(inorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(preorder, index + 1, preorder.length), Arrays.copyOfRange(inorder, index + 1, inorder.length));
        return root;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    public TreeNode buildTreefromPostorder(int[] inorder, int[] postorder) {
        if(postorder.length == 0) return null; 
        if(postorder.length == 1) return new TreeNode(postorder[0]);
        int r = postorder[postorder.length - 1];
        TreeNode root = new TreeNode(r);
        int index = 0;
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == r) {
                index = i; 
                break;
            }
        }
        root.left = buildTree(Arrays.copyOfRange(inorder, 0, index), Arrays.copyOfRange(postorder, 0, index));
        root.right = buildTree(Arrays.copyOfRange(inorder, index + 1, inorder.length), Arrays.copyOfRange(postorder, index, postorder.length - 1));
        return root;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

}