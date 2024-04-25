/*
- Why trees?

O (log N)
Ordered Structure
Cost

- Where they are used?
File systems
Data bases
Network Routing
Solve complex Mahematical equations
mechine learning
Compression of files
Data structures (Heap, graph)


- Trees are directed Acyclic graph

- Terminologies / Properties
Size
Child and parent
Sibling 
Edges
Height 
Leaf
Level
Ancestor and descendant
Degree (no of child (0, 1, 2 in case of binary tree))


- Types of Binery Tree
i. Complete Binery Tree
(All level are full apart fom last level and when filling the last level it is filled from left to right)

ii. Full Binary tree/ Strict Binary tree -----> Used in compression
(Each node has either zero or 2 children, no single child)

iii. perfect Binery tree
(All levels are fulled, no empty space)

iv. Height Balanced Binary tree
(Ang Height  = O(logN) )

v. Skewed Binary tree
(Every node has only one Node, height = O(N)) 

vi. Ordered Binary tree
(Every node has some property that it follows ------> Binary Search tree)



- Properties
In perfect BT if height = h, total node = 2 ^ (h + 1) - 1 and number of node at that level = 2 ^ (level of tree)
toal leaf fnode in perfect BT = 2 ^ height
It has maximun number of nodes
if n = no of leaf, we have log(n + 1) levels atleast
if n = no of nodes we have log( n + 1) node atleast

For strict Binery Tree
n = leaf node, internal node (node apart from leaf node) = n + 1
Node of leaf node = 1 + no of internal node with 2 children not including root node
*/

import java.util.Scanner;
class BinaryTree {
	
	private class Node {
		int val; 
		Node left, right;

		Node (int val) {
			this.val = val;
		}

	}
	private Node root; 

	public void insert (Scanner sc) {
		System.out.println("Enter the root value ");
		int val = sc.nextInt();
		root = new Node(val);
		insert(sc, root);
	}

	public void insert(Scanner sc, Node node) {
		System.out.println("Do you want to add to the left of the node " + node.val);
		Boolean left = sc.nextBoolean();
		if (left) {
			System.out.println("Enter the value for the left of the node " + node.val);
			int value = sc.nextInt();
			node.left = new Node(value);
			insert(sc, node.left);
		}


		System.out.println("Do you want to add to the right of the node " + node.val);
		Boolean right = sc.nextBoolean();
		if (right) {
			System.out.println("Enter the value for the right of the node " + node.val);
			int value = sc.nextInt();
			node.right = new Node(value);
			insert(sc, node.right);
		} 
	}

	public void display() {
		display(root, "");
	}

	private void display(Node n, String indent) {
		if (n == null) {
			return;
		}
		System.out.println(indent + n.val);
		display(n.left, indent + "\t");
		display(n.right, indent + "\t");
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
			for (int i = 0; i < indent - 1; i++) {
				System.out.print("|\t\t");
			}

			System.out.println("|-------->" + n.val);
		} else {
			System.out.println(n.val);
		}

		prettyDisplay(n.left, indent + 1);

	}
}


class Tree {
	public static void main(String[] args) {
		BinaryTree b = new BinaryTree();
		b.insert(new Scanner(System.in));

		b.display();
	}
}