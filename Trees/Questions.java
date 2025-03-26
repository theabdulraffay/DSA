import java.util.*;
/*
Breath first Search
Travelling in a binary tree level by level


*/

class BST {
	private class Node {
		private int val;
		private Node left, right;

		Node(int val) {
			this.val = val;
		}

		// int getValue() {
		// return val;
		// }

	}

	private Node root;

	public void insert(int key) {
		root = insert(key, root);
	}

	private Node insert(int key, Node node) {
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
	public List<List<Integer>> levelOrder() {
		return levelOrder(root);
	}

	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> list = new ArrayList<List<Integer>>(); // Main returnig list
		if (root == null)
			return list;
		Queue<Node> queue = new LinkedList<Node>(); // This will maintain the left to right all the nodes of the a same
													// level
		queue.add(root);

		while (!queue.isEmpty()) {
			List<Integer> currentLevel = new ArrayList<Integer>();
			int levelSize = queue.size(); // At this step the size of the queue is exactly same as that of the level of
											// the binary tree
			for (int i = 0; i < levelSize; i++) { // so we run the loop the number of nodes that this particular level
													// has and add those nodes to the list
				Node temp = queue.poll();
				currentLevel.add(temp.val);
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}
			list.add(currentLevel);
		}
		return list;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
	public List<List<Integer>> levelOrderBottom(Node root) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if (root == null)
			return list;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			List<Integer> currentLevel = new ArrayList<Integer>();
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				Node temp = queue.poll();
				currentLevel.add(temp.val);
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}
			list.add(0, currentLevel); // we just add the new currentLevel ist to the first position if the resulting
										// list in this way we get the resulting list as the list that contains all the
										// levels starting from the bottom of the root node
		}
		return list;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/average-of-levels-in-binary-tree/
	public List<Double> averageOfLevels(Node root) { // GOOGLE QUESTION
		List<Double> list = new ArrayList<>();
		if (root == null)
			return list;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			double sumOfLevel = 0;
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				Node temp = queue.poll();
				sumOfLevel += (temp.val);
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}
			double avg = sumOfLevel / levelSize;
			list.add(avg);
		}
		return list;

	}

	// -----------------------------------------------------------------------------------------------------------------------------
	public int levelOrderSuccessor(int key) { // GOOGLE QUESTION // This will return the value of the node which is
												// present at the right side of that node again we use queue to store
												// levels of binary tree, when we find that particular node of value kep
												// we just retrieve the value which is next to it (value to the right of
												// the node, if the right node is null we just return the left node of
												// one step lower level of binary tree)
		if (root == null)
			return -1;
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			if (temp.left != null)
				queue.add(temp.left);
			if (temp.right != null)
				queue.add(temp.right);
			if (temp.val == key)
				break;

		}
		return queue.peek().val;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
	public List<List<Integer>> zigzagLevelOrder() { // AMAZON, GOOGLE, META, MICROSFT QUESTION, Also known as spiral
													// Level Order
		// In this we travel the Binary tree in zig zag pattern, so we just reverse
		// every second list before we add it to the main list;
		List<List<Integer>> list = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		if (root == null)
			return list;
		int reverse = 1;

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> level = new ArrayList<>(levelSize);
			for (int i = 0; i < levelSize; i++) { // this for loop will always run number of node in a level time
				Node temp = queue.poll();
				level.add(temp.val);
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
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
		if (root == null)
			return list;
		boolean reverse = false;

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			List<Integer> level = new ArrayList<>(levelSize);
			for (int i = 0; i < levelSize; i++) {
				if (!reverse) {
					Node temp = queue.pollFirst();
					level.add(temp.val);
					if (temp.left != null)
						queue.addLast(temp.left);
					if (temp.right != null)
						queue.addLast(temp.right);
				} else { // for every second iteration instead of removing element from the first
							// position we just remove from the last in this was a reverse list is added to
							// the main list
					Node temp = queue.pollLast();
					level.add(temp.val);
					if (temp.right != null)
						queue.addFirst(temp.right);
					if (temp.left != null)
						queue.addFirst(temp.left);
				}
			}
			reverse = !reverse;
			list.add(level);
		}
		return list;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
	public Node connect() { // AMAZON - IMPORTANT QUESTION // connect every node with the node to its right
							// using 'next' pointer
		if (root == null)
			return root;
		Queue<Node> queue = new LinkedList<Node>(); // This will maintain the left to right all the nodes of the a same
													// level
		queue.add(root);

		while (!queue.isEmpty()) {
			int levelSize = queue.size();
			for (int i = 0; i < levelSize; i++) {
				Node temp = queue.poll();
				if (i != levelSize - 1) {
					// temp.next = queue.peek(); // This line is commented arbitrary, copy paste the
					// solution in leetcode to get ur result
				}
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
			}
		}
		return root;

	}

	public Node connect2() { // AMAZON - IMPORTANT QUESTION // connect every node with the node to its right
								// using 'next' pointer
		if (root == null)
			return root;

		Node leftNode = root;
		while (leftNode.left != null) {
			Node current = leftNode;
			while (current != null) {
				// current.left.next = current.right;
				// if (current.next != null) current.right.next = current.next.left; //
				// Commented because absence of 'next' pointer in our class structure
				// current = current.next;
			}
			leftNode = leftNode.left;
		}
		return root;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/binary-tree-right-side-view
	public List<Integer> rightSideView(Node root) { // FACEBOOK, NVIDIA, FLIPCART, AMAZON QUESTION // contains only the
													// far right node of the binary tree
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Queue<Node> que = new LinkedList<>();
		que.add(root);
		while (!que.isEmpty()) {
			int size = que.size();
			for (int i = 0; i < size; i++) {
				Node temp = que.poll();
				if (temp.left != null)
					que.add(temp.left);
				if (temp.right != null)
					que.add(temp.right);
				if (i == size - 1)
					list.add(temp.val);
			}
		}
		return list;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/cousins-in-binary-tree/
	public boolean isCousins(Node root, int x, int y) {
		Queue<Node> que = new LinkedList<>();
		que.add(root);
		while (!que.isEmpty()) {
			int size = que.size();
			boolean first = false;
			boolean second = false;
			for (int i = 0; i < size; i++) {
				Node temp = que.poll();
				if (temp.left != null)
					que.add(temp.left);
				if (temp.right != null)
					que.add(temp.right);
				if ((temp.left != null && temp.left.val == x) || (temp.right != null && temp.right.val == x)) {
					first = true;
				} else if ((temp.left != null && temp.left.val == y) || (temp.right != null && temp.right.val == y)) {
					second = true;
				}
			}
			if (first && second) {
				return true;
			}
		}
		return false;
	}

	public boolean isCousins2(Node root, int x, int k) { // Another way to solve this same question
		Node xx = findNode(root, x);
		Node yy = findNode(root, k);
		return (level(root, xx, 0) == level(root, yy, 0)) && (!isSibling(root, xx, yy));
	}

	boolean isSibling(Node root, Node xx, Node yy) {
		if (root == null) {
			return false;
		}

		return ((root.left == xx && root.right == yy) || (root.right == xx && root.left == yy)
				|| isSibling(root.left, xx, yy) || isSibling(root.right, xx, yy));
	}

	Node findNode(Node root, int x) {
		if (root == null) {
			return null;
		}

		if (root.val == x) {
			return root;
		}

		Node left = findNode(root.left, x);
		if (left != null) {
			return left;
		}
		return findNode(root.right, x);
	}

	int level(Node root, Node xx, int level) {
		if (root == null) {
			return 0;
		}
		if (root == xx) {
			return level;
		}

		int l = level(root.left, xx, level + 1);
		if (l != 0) {
			return l;
		}
		return level(root.right, xx, level + 1);
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/symmetric-tree/description/
	boolean notPalindrome(List<Integer> list) {
		int st = 0;
		int en = list.size() - 1;
		while (st < en) {
			if (list.get(st++) != list.get(en--)) {
				return true;
			}
		}
		return false;
	}

	public boolean isSymmetric(Node root) {
		Queue<Node> que = new LinkedList<>();
		que.add(root);
		while (!que.isEmpty()) {
			int size = que.size();
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < size; i++) {
				Node temp = que.poll();
				if (temp.val == -101) {
					list.add(-101);
					continue;
				}
				if (temp.left != null)
					que.add(temp.left);
				else
					que.add(new Node(-101));

				if (temp.right != null)
					que.add(temp.right);
				else
					que.add(new Node(-101));
				list.add(temp.val);
			}
			if (notPalindrome(list)) {
				return false;
			}
		}
		return true;
	}

	public boolean isSymmetric2(Node root) { // Recursively
		return symmetry(root.left, root.right);
	}

	boolean symmetry(Node l, Node r) {
		boolean a = l == null;
		boolean b = r == null;
		if (a && b)
			return true;
		if (a || b || l.val != r.val)
			return false;
		return symmetry(l.left, r.right) && symmetry(l.right, r.left);

	}

	public boolean isSymmetric3(Node root) { // Iteratively
		Queue<Node> que = new LinkedList<>();
		que.add(root.left);
		que.add(root.right);
		while (!que.isEmpty()) {
			Node l = que.poll();
			Node r = que.poll();
			boolean a = l == null;
			boolean b = r == null;
			if (a && b) {
				continue;
			}
			if (a || b || l.val != r.val) {
				return false;
			}

			que.add(l.left);
			que.add(r.right);
			que.add(l.right);
			que.add(r.left);
		}
		return true;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/maximum-level-sum-of-a-binary-tree/
	public int maxLevelSum(Node root) { // it will return the level that has maximum sum
		Queue<Node> que = new LinkedList<>();
		int counter = 1;
		int level = 0;
		int max = Integer.MIN_VALUE;
		que.add(root);
		while (!que.isEmpty()) {
			int sum = 0;
			int levelSize = que.size();
			for (int i = 0; i < levelSize; i++) {
				Node temp = que.poll();
				sum += temp.val;
				if (temp.left != null)
					que.add(temp.left);
				if (temp.right != null)
					que.add(temp.right);
			}
			if (sum > max) {
				max = sum;
				level = counter;
			}
			counter++;
		}
		return level;

	}

	// -----------------------------------------------------------------------------------------------------------------------------
	// https://leetcode.com/problems/complete-binary-tree-inserter/description/
	class CBTInserter {
		ArrayList<Node> l = new ArrayList<>();

		public CBTInserter(Node root) {
			Queue<Node> que = new LinkedList<Node>();
			l.add(root);
			que.add(root);
			while (!que.isEmpty()) {
				int size = que.size();
				for (int i = 0; i < size; i++) {
					Node temp = que.poll();
					if (temp.left != null) {
						que.add(temp.left);
						l.add(temp.left);
					}

					if (temp.right != null) {
						que.add(temp.right);
						l.add(temp.right);
					}
				}
			}
		}

		public int insert(int val) {
			int parent = (l.size() - 1) / 2;
			Node temp = l.get(parent);
			if (l.size() % 2 == 0) {
				temp.right = new Node(val);
				l.add(temp.right);
			} else {
				temp.left = new Node(val);
				l.add(temp.left);
			}
			return temp.val;
		}

		public Node get_root() {
			return l.get(0);

		}
	}

	class CBTInserter2 {
		Node root;

		public CBTInserter2(Node root) {
			this.root = root;

		}

		public int insert(int val) {
			Queue<Node> que = new LinkedList<Node>();
			que.add(root);
			while (!que.isEmpty()) {
				int size = que.size();
				for (int i = 0; i < size; i++) {
					Node temp = que.poll();
					if (temp.left != null) {
						que.add(temp.left);
					} else {
						temp.left = new Node(val);
						return temp.val;
					}

					if (temp.right != null) {
						que.add(temp.right);
					} else {
						temp.right = new Node(val);
						return temp.val;
					}
				}
			}
			return 0;

		}

		public Node get_root() {
			return root;

		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------------
}

// -----------------------------------------------------------------------------------------------------------------------------

class Questions {
	public static void main(String[] args) {
		BST tree = new BST();
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
		for (List<Integer> i : ins) {
			System.out.println(i);
		}

		System.out.println();
		List<List<Integer>> in = tree.zigzagLevelOrder2();
		for (List<Integer> i : in) {
			System.out.println(i);
		}
		// System.out.println(tree.levelOrderSuccessor(7));

	}
}