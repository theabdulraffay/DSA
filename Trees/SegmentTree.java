/*
Segment Tree peform a querie on a range (sum of all in a range, max, avg, prod, min)
	Find max number between two indices in an array we use segment tree
	O(log N)
Segment tree is a binary tree that has interval info and operation performing 
ST is full binary tree (all node have 2 childs excpt leaf node)
leaf node = n;
internal node == n- 1;
total node = 2 ^ n-1



you can also update the tree by traversing all the way to the leaf (by checking the range in the node)and updating it then as we move upward we sum the values of left and right node 




*/
class SegmentedTree {
	private class Node {
		int data;
		int start;
		int end;
		Node left;
		Node right;

		Node(int data, int start, int end) {
			this.data = data;
			this.start = start;
			this.end = end;
		}

		Node(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// Node () {
		// this(0, 0, 0);
		// }
	}

	Node root;

	SegmentedTree(int[] arr) {
		this.root = constructTree(arr, 0, arr.length - 1);
	}

	private Node constructTree(int[] arr, int start, int end) {
		if (start == end) { // At this point we are at the leaf node, and its data is same as that of the
							// arr index
			Node leaf = new Node(arr[start], start, end);
			return leaf;
		}

		Node node = new Node(start, end);
		int mid = start + (end - start) / 2;
		node.left = constructTree(arr, start, mid);
		node.right = constructTree(arr, mid + 1, end);
		node.data = node.left.data + node.right.data; // we collect data from left and right ans then add them to the
														// current node, in this way the current node has the sum of
														// array between 'start' and 'end' index
		return node;
	}

	public int query(int start, int end) {
		return query(root, start, end);

	}

	private int query(Node node, int start, int end) {
		if (node.start >= start && node.end <= end) {
			// node is completely iside query
			return node.data;
		} else if (node.start > end || node.end < start) {
			return 0;
		} else {
			return query(node.left, start, end) + query(node.right, start, end);
		}
	}

	public void update(int index, int value) {
		root.data = update(root, index, value);

	}

	private int update(Node node, int index, int value) {
		// check whether the index lies in interval of start and end
		// then check child node, if child range is out of bound donot do anything
		// keep calling recursion calls until you find the index with value and then
		// update the value
		if (index >= node.start && index <= node.end) {
			if (index == node.start && index == node.end) {
				node.data = value;
			} else {
				int leftAns = update(node.left, index, value);
				int rightAns = update(node.right, index, value);
				node.data = leftAns + rightAns;
			}
		}
		return node.data;

	}

}

class SegmentTree {
	public static void main(String[] args) {
		SegmentedTree tree = new SegmentedTree(new int[] { 1, 2, 3, 4, 5, 6, 7 });
		System.out.println(tree.query(0, 3));
		tree.update(0, 6);
		System.out.println(tree.query(0, 3));

	}
}