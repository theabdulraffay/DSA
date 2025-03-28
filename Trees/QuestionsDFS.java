import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
//https://www.geeksforgeeks.org/top-50-tree-coding-problems-for-interviews/
class BST {
	 class TreeNode {
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
        TreeNode left = invertTree2(root.left); // first move to the left
        TreeNode right = invertTree2(root.right);
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
    public TreeNode buildTree(int[] preorder, int[] inorder) { // GOOGLE QUESTION
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
    // https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
    class Codec { // It codes and decodes the tree into string and back to tree
    StringBuffer list = new StringBuffer();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        helper(root);
        return list.toString();
    }

    void helper(TreeNode root) {
        if(root == null) {
            list.append("null ");
            return;
        }
        list.append(String.valueOf(root.val));
        list.append(" ");
        helper(root.left);
        helper(root.right);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] s = data.split(" ");
        return helper2(s);
        
    }
    int index = 0;
    TreeNode helper2(String[] s) {
        if(s[index].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s[index]));
        index++;
        root.left = helper2(s);
        index++;
        root.right = helper2(s);
        return root;
    }
}

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/delete-node-in-a-bst/description/
    TreeNode helperDeleteNode(TreeNode root) {
        if(root.right == null) return root.left;
        else if(root.left == null) return root.right;
        else {
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode topright = left;
            while(topright.right != null) {
                topright = topright.right;
            }
            topright.right = right;
            return left;

        }
    }
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(root.val == key) return helperDeleteNode(root);
        else {
            TreeNode temp = root;
            while(root != null) {
                if(root.val > key) {
                    if(root.left != null && root.left.val == key) {
                        root.left = helperDeleteNode(root.left);
                        break;
                    }
                    root = root.left;
                }
                else {
                    if(root.right != null && root.right.val == key) {
                        root.right = helperDeleteNode(root.right);
                        break;
                    }
                    root = root.right;
                }
            }
            return temp;
        }
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// https://leetcode.com/problems/trim-a-binary-search-tree/
    TreeNode helperTrimBST(TreeNode root) {
        if(root.left == null) return root.right;
        else if(root.right == null) return root.left;
        else {
            TreeNode left = root.left;
            TreeNode right = root.right;
            TreeNode topright = left;
            while(topright.right != null) {
                topright = topright.right;
            }
            topright.right = right;
            return left;
        }
    }
    public TreeNode trimBST(TreeNode root, int low, int high) {
        Queue<TreeNode> que = new LinkedList<>();
        while(root != null && (root.val < low || root.val > high)) {
            root = helperTrimBST(root);
        }
        if(root != null)que.add(root);
        while(!que.isEmpty()) {
            int size = que.size();
            for(int i = 0; i < size; i++) {
                TreeNode temp = que.poll();
                while(temp.left != null && (temp.left.val < low || temp.left.val > high)) {
                    temp.left = helperTrimBST(temp.left);
                }
                if(temp.left != null)que.add(temp.left);

                while(temp.right != null && (temp.right.val < low || temp.right.val > high)) {
                    temp.right = helperTrimBST(temp.right);
                }
                if(temp.right != null)que.add(temp.right);
            }
        }
        return root;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/balance-a-binary-search-tree/
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        tolist(root, list);
        return insert(list, 0, list.size() - 1);
        
    }
    void tolist (TreeNode root, ArrayList<Integer> list) {
        if(root == null) return;
        tolist(root.left, list);
        list.add(root.val);
        tolist(root.right, list);
    }

    TreeNode insert(ArrayList<Integer> nums, int st, int end) {
        if(st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        TreeNode root = new TreeNode(nums.get(mid)); 
        root.left = insert(nums, st, mid - 1);
        root.right = insert(nums, mid + 1, end);
        return root;
    }
    

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    ListNode getmid(ListNode head) {
        if(head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null; 
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;
            slow = slow.next;
        }
        if(prev == null) head = null;
        else prev.next = null;
        return slow;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;

        ListNode mid = getmid(head);
        TreeNode root = new TreeNode(mid.val);
        if(head == mid){
            return root;
        }
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);
        return root;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/maximum-binary-tree/
    TreeNode helper(int[] nums,int st, int en) {
        if(st > en) return null;
        int max = nums[st];
        int index = st;
        for(int i = st; i <= en; i++) {
            if(nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(max);
        root.left = helper(nums, st, index - 1);
        root.right = helper(nums, index + 1, en);
        return root;
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length - 1);
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean isSubPath(ListNode head, TreeNode root) {
        if(head == null) return true;
        if(root == null) return false;
        if(helper(head, root)) return true;
        boolean left = isSubPath(head, root.left);
        boolean right = isSubPath(head, root.right);
        return left || right;
        
    }
    boolean helper(ListNode head, TreeNode root) {
        if(head == null) {
            return true;
        }
        if(root == null) {
            return false;
        }
        if(head.val != root.val) {
            return false;
        }
        boolean left = helper(head.next, root.left);
        boolean right = helper(head.next, root.right);
        
        return left || right;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/evaluate-boolean-binary-tree/
    public boolean evaluateTree(TreeNode root) {
        if(root.val == 1) return true;
        if(root.val == 0) return false;

        boolean left = evaluateTree(root.left);
        boolean right = evaluateTree(root.right);
        if(root.val == 3) return left && right;
        else return left || right;
        
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// https://leetcode.com/problems/delete-leaves-with-a-given-value/
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null) return null;
        if(root.val == target && root.left == null && root.right == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if(root.val == target && root.left == null && root.right == null) {
            return null;
        }

        return root;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/all-elements-in-two-binary-search-trees/description/
    List<Integer> l = new ArrayList<Integer>();
    void insert(TreeNode r1) {
        if(r1 == null) return;
        insert(r1.left);
        l.add(r1.val);
        insert(r1.right);
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        insert(root1);
        insert(root2);
        Collections.sort(l);
        return l;
    }

    void insertElement(TreeNode r1, List<Integer> m ) {
        if(r1 == null) return;
        insertElement(r1.left, m);
        m.add(r1.val);
        insertElement(r1.right, m);
    }



    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        List<Integer> m = new ArrayList<Integer>();
        insertElement(root1, m);
        List<Integer> n = new ArrayList<Integer>();
        insertElement(root2, n);

        List<Integer> toRet = new ArrayList<>();
        int i = 0;
        int j = 0;

        while(i < m.size() && j < n.size()) {
            if(m.get(i) < n.get(j)) {
                toRet.add(m.get(i));
                i++;
            } else {
                toRet.add(n.get(j));
                j++;
            }
        }

        while(i < m.size()) {
            toRet.add(m.get(i++));
        }

        while(j < n.size()) {
            toRet.add(n.get(j++));
        }




        // Collections.sort(l);
        return toRet;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/leaf-similar-trees/
    void insertLeafSimilar(TreeNode r, List<Integer> l) {
        if(r == null) return;
        if(r.left == null && r.right == null) l.add(r.val);
        insertLeafSimilar(r.left, l);
        insertLeafSimilar(r.right, l);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l = new ArrayList<>();
        insertLeafSimilar(root1, l);
        List<Integer> s = new ArrayList<>();
        insertLeafSimilar(root2,s);
        return l.equals(s);  
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    int res;
    public int longestZigZag(TreeNode root) {
        res = 0;
        dfs(root.right, 1, 0);
        dfs(root.left, 0, 0);
        return res;
    }


    public void dfs(TreeNode root, int from, int length){
        if(root == null) return;

        if(from == 1) {
            dfs(root.left, 0, ++length);
            dfs(root.right, 1, 0);
        } else {
            dfs(root.right, 1, ++length);
            dfs(root.left, 0, 0);
        }
        res = Math.max(res, length);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/count-good-nodes-in-binary-tree/
    void helper(TreeNode child, int max, int[] counter) {
        if(child == null) return;
        if(child.val >= max) {
            max = child.val;
            counter[0]++;
        }
        helper(child.left, max, counter);
        helper(child.right, max, counter);
    }
    public int goodNodes(TreeNode root) {
        int[] x = new int[1];
        helper(root, root.val, x);
        return x[0];
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/binary-tree-preorder-traversal/
    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    // https://leetcode.com/problems/binary-tree-postorder-traversal/
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-bottom-left-tree-value/
    public int findBottomLeftValue2(TreeNode root) { // Using iteration
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int toret = 0;
        while(!que.isEmpty()) {
            TreeNode te = que.poll();
            toret = te.val;
            if(te.right != null) que.add(te.right);
            if(te.left != null) que.add(te.left);
        }
        return toret;
    }






    int ans = -1;
    int depth = -1;
    public int findBottomLeftValue(TreeNode root) { // Using recursion 
        helperFindBottomLeftValue(root, 0);
        return ans;
    }
    void helperFindBottomLeftValue(TreeNode root, int height) {
        if(root == null) return;
        if(height > depth) {
            depth = height;
            ans = root.val;
        }

        helperFindBottomLeftValue(root.left, height + 1);
        helperFindBottomLeftValue(root.right, height + 1);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/kth-largest-sum-in-a-binary-tree/
    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> que = new LinkedList<>();
        PriorityQueue<Long> pque = new PriorityQueue<>();
        que.add(root);
        while(!que.isEmpty()) {
            int n = que.size();
            long sum = 0;
            for(int i = 0; i < n; i++) {
                TreeNode temp = que.poll();
                sum += temp.val;
                if (temp.left != null) que.add(temp.left);
                if (temp.right != null) que.add(temp.right);
            }
            pque.add(sum);
            if(pque.size() > k) pque.poll(); 
        }
        return pque.size() < k ? -1 : pque.peek();
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/find-largest-value-in-each-tree-row/
    public List<Integer> largestValues(TreeNode root) { // Using iteration
        List<Integer> toret = new ArrayList<>();
        if(root == null) return toret;

        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        while(!que.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int n = que.size();
            for(int i = 0; i < n;i++) {
                TreeNode te = que.poll();
                if(te.val > max) {
                    max = te.val;
                }
                if(te.left != null) que.add(te.left);
                if(te.right != null) que.add(te.right);
            }
            toret.add(max);
        }
        return toret;
        
    }



    List<Integer> rowsMax;
    public List<Integer> largestValues2(TreeNode root) { // Using recursion
        rowsMax = new ArrayList<>();
        helperLargetValues(root, 0);
        return rowsMax;
    }

    void helperLargetValues(TreeNode root, int depth) {
        if(root == null) return;
        if(depth < rowsMax.size()) {
            int max = Math.max(root.val, rowsMax.get(depth));
            rowsMax.set(depth, max);
        }
        else {
            rowsMax.add(root.val);
        }
        helperLargetValues(root.left, depth + 1);
        helperLargetValues(root.right, depth + 1);
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/deepest-leaves-sum/description/
    public int deepestLeavesSum2(TreeNode root) { // Using iteration
        Queue<TreeNode> que = new LinkedList<>();
        que.add(root);
        int toret = 0;
        while(!que.isEmpty()) {
            int n = que.size();
            int sum = 0;
            for(int i = 0; i < n; i++) {
                TreeNode te = que.poll();
                sum += te.val;
                if(te.left != null) que.add(te.left);
                if(te.right != null) que.add(te.right);
            }
            toret = sum;
        }
        return toret;
    }




    int heightLeafSum(TreeNode root) {
        if(root == null) return 0;
        int l = heightLeafSum(root.left);
        int r = heightLeafSum(root.right);

        return Math.max(l, r) + 1;
    }

    int leaveSum = 0;
    void sum(TreeNode root, int depth, int height) {
        if(root == null) return;
        if(depth == height) {
            leaveSum += root.val;
        }
        sum(root.left, depth + 1, height);
        sum(root.right, depth + 1, height);
    }
    public int deepestLeavesSum(TreeNode root) { // Using recursion
        int n = heightLeafSum(root);
        sum(root, 1, n);
        return leaveSum;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    int height2(TreeNode root) {
        if(root == null) return -1;
        return Math.max(height2(root.left), height2(root.right)) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(height2(root.left) - height2(root.right)) > 1) {
            return false;
        }
        boolean l = isBalanced(root.left);
        if(!l) {
            return false;
        }
        return isBalanced(root.right);
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/convert-bst-to-greater-tree/
    // https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
    int helperConvertBST(TreeNode root, int sum) {
        if(root == null) return sum;
        int right = helperConvertBST(root.right, sum);
        int left = helperConvertBST(root.left, root.val + right);
        root.val += right;
        return left;
    }
    public TreeNode convertBST(TreeNode root) { // Using no additional vairiable
        helperConvertBST(root, 0);
        return root;   
    }



    int sumBST = 0;
    void helperConvertBST(TreeNode root) {
        if(root == null) return;
        helperConvertBST(root.right);
        sumBST += root.val;
        root.val = sumBST;
        helperConvertBST(root.left);
    }
    public TreeNode convertBST2(TreeNode root) { // using one additional variable
        helperConvertBST(root);
        // if(root == null) return null;
        // convertBST2(root.right);
        // sumBST += root.val;
        // root.val = sumBST;
        // convertBST2(root.left);
        return root;   
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/most-frequent-subtree-sum/description/
    
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
                                                                        // Path Questions
// https://leetcode.com/problems/path-sum/
public boolean hasPathSum(TreeNode root, int targetSum) { // AMAZON QUESTION 
        if(root == null) return false;
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        boolean left = hasPathSum(root.left, targetSum - root.val);
        if (left) return true;
        return hasPathSum(root.right, targetSum - root.val);

    }
    // sumTillNow is initially set to 0;
    // boolean helper(TreeNode root, int targetSum, int sumTillNow) {
    //     if(root == null) return false;
    //     if (root.left == null && root.right == null) {
    //         return targetSum == sumTillNow + root.val;
    //     }

    //     boolean left = helper(root.left, targetSum, sumTillNow + root.val);
    //     if (left) {
    //         return true;
    //     }
    //     return helper(root.right, targetSum, sumTillNow + root.val);
    // }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/sum-root-to-leaf-numbers/
    public int sumNumbers(TreeNode root) {
        helpersumNumbers(root, 0);
        return sum;
        // return helper2(root, 0);
    }

    int sum = 0;
    void helpersumNumbers(TreeNode root, int sumTillNow) { // SOLUTION 1 - it will use an extra variable 'sum'
        if(root == null) return;
        if(root.left == null && root.right == null) {
            sumTillNow = root.val + (sumTillNow * 10);
            // sumTillNow += root.val;
            sum += sumTillNow;
        }
        helpersumNumbers(root.left, root.val + (sumTillNow *10));
        helpersumNumbers(root.right, root.val + (sumTillNow * 10));
    }

    int helper2(TreeNode root, int sumTillNow) { // SOLUTION 2
        if(root == null) return 0;
        if(root.left == null && root.right == null) {
            return root.val + (sumTillNow * 10);
        }
        return helper2(root.left, root.val + (sumTillNow *10)) + helper2(root.right, root.val + (sumTillNow * 10));
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/binary-tree-maximum-path-sum/
    int max = 0;
    public int maxPathSum(TreeNode root) { // FACEBOOK QUESTION
        max = root.val;
        helpermaxPathSum(root);
        return max;
    }

    int helpermaxPathSum(TreeNode root) {
        if(root == null) return 0;
        if(root.left == null && root.right == null) { 
            if(root.val > max) max = root.val; 
            return root.val;
        }
        int left = helpermaxPathSum(root.left);
        left = (left < 0) ? 0 : left;

        int right = helpermaxPathSum(root.right);
        right = (right < 0) ? 0 : right;

        int sum = root.val + left + right;
        if(sum > max) max = sum;

        return Math.max(left, right) + root.val;
    }
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public boolean findPath(TreeNode root, int[] path) { // You will be given an array of paths from root to leaf node, check if it is an valid path
        return helperFindPath(root, path, 0);
    }

    boolean helperFindPath(TreeNode root, int[] path, int index) {
        if(root == null) return path.length == 0;
        if(root.left == null && root.right == null && index == path.length - 1) return true;
        if(root.val != path[index]) return false;

        return helperFindPath(root.left, path, index + 1) || helperFindPath(root.right, path, index + 1);
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

    int countPath(TreeNode root, int sum) {
        List<Integer> paths = new ArrayList<Integer>();
        return helper(root, sum, paths);
    }

    int helper(TreeNode root, int sum, List<Integer> paths) {
        if(root == null) return 0;
        paths.add(root.val);
        int count = 0;
        int s = 0; 
        ListIterator<Integer> itr = paths.listIterator(paths.size());
        while(itr.hasPrevious()) {
            s = s + itr.previous();
            if (s == sum) {
                count++;
            }
        }

        count += helper(root.left, sum, paths) + helper(root.right, sum, paths);
        paths.remove(paths.size() - 1);
        return count;
    }



    List<List<Integer>> findPaths(TreeNode root, int sum) {
        List<List<Integer>> path = new ArrayList<List<Integer>>();
        List<Integer> paths = new ArrayList<Integer>();
        helper(root, sum, paths, path);
        return path;
    }

    void helper(TreeNode root, int sum, List<Integer> paths, List<List<Integer>> path) {
        if(root == null) return ;
        paths.add(root.val);
        if(root.val == sum && root.left == null && root.right == null) {
            path.add(new ArrayList<>(paths));

        } else {
            helper(root.left, sum - root.val, paths, path);
            helper(root.right, sum - root.val, paths, path);
        }
        paths.remove(paths.size() - 1);
    }


// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/delete-nodes-and-return-forest/
    List<TreeNode> toret;
    HashSet<Integer> set;
    TreeNode recursion(TreeNode root) {
        if(root == null) return null;
        root.left = recursion(root.left);
        root.right = recursion(root.right);
        if(set.contains(root.val)) {
            if(root.left != null) toret.add(root.left);
            if(root.right != null) toret.add(root.right);
            return null;
        }
        return root;

    }
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        toret= new ArrayList<>();
         set = new HashSet<>();
        for(int i : to_delete)set.add(i);
        recursion(root);
        if(!set.contains(root.val)) toret.add(root);
        return toret;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/cousins-in-binary-tree-ii/
    public TreeNode replaceValueInTree(TreeNode root) {
        Queue<TreeNode> que = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        que.add(root);
        while(!que.isEmpty()) {
            int n = que.size();
            int sum = 0;
            for(int i = 0; i < n; i++) {
                TreeNode temp = que.poll();
                if(temp.left != null) que.add(temp.left);
                if(temp.right != null) que.add(temp.right);
                sum += temp.val;
            }
            list.add(sum);
        }
        que.clear();
        que.add(root);
        int j = 1;
        while(!que.isEmpty()) {
            int n = que.size();
            for(int i = 0; i < n; i++) {
                int sum = 0;
                TreeNode temp = que.poll();
                if(temp.left != null) {que.add(temp.left); sum += temp.left.val;}
                if(temp.right != null) {que.add(temp.right); sum += temp.right.val;}

                if(temp.left != null) temp.left.val = list.get(j) - sum;
                if(temp.right != null) temp.right.val = list.get(j) - sum;
            }
            j++;
        }
        root.val = 0;

        return root;
        
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // https://leetcode.com/problems/number-of-good-leaf-nodes-pairs/
    int k, count;
    HashMap<TreeNode, List<Integer>> map;
    boolean recursionCountPair(TreeNode root) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return true;
        boolean left = recursionCountPair(root.left);
        boolean right = recursionCountPair(root.right);
        if(left && right) {
            List<Integer> l = another(root.left, 1);
            List<Integer> r = another(root.right, 1);

            for(int i = 0; i < l.size(); i++) {
                for(int j = 0; j < r.size(); j++) {
                    if(l.get(i) + r.get(j) <= k) { // Use 
                        count++;
                    }
                }
            }

            List<Integer> lo = new ArrayList<>();
            lo.addAll(r); lo.addAll(l);
            map.put(root, lo);        
        }
        return left || right;
    }

    List<Integer> another(TreeNode root, int length) {
        if(root == null) return null;
        if(map.containsKey(root)) {
            // List<Integer> list = new ArrayList<>();
            List<Integer> toret = map.get(root);
            for(int i = 0; i < toret.size(); i++) {
                toret.set(i, toret.get(i) + length);
            }
            return toret;

        }
        if(root.left == null && root.right == null) {
            return Arrays.asList(length);
        }
        List<Integer> left = another(root.left, length + 1);
        if(left != null) return left;
        return another(root.right, length + 1);
    }
    public int countPairs(TreeNode root, int distance) {
        k = distance;
        count = 0;
        map = new HashMap<>();
        recursionCountPair(root);
        return count;
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// https://leetcode.com/problems/flip-equivalent-binary-trees/description/
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null) return false;
        if(root1.val != root2.val) return false;
            boolean temp = flipEquiv(root1.left, root2.left) || flipEquiv(root1.left, root2.right);
            if(!temp) return false;
            return flipEquiv(root1.right, root2.right) || flipEquiv(root1.right, root2.left);
    }

    public boolean flipEquiv2(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null) return true;
        if(root1 == null || root2 == null || root1.val != root2.val) return false;
            return (flipEquiv2(root1.left, root2.left) || flipEquiv2(root1.left, root2.right)) && (flipEquiv2(root1.right, root2.right) || flipEquiv2(root1.right, root2.left));
    }

// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------

}