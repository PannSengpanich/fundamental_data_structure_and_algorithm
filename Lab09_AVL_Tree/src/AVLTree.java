import java.util.ArrayList;

public class AVLTree {
	AVLNode root;
	int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(AVLNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public Iterator findMax() {
		return findMax(root);
	}

	public Iterator findMax(AVLNode n) {
		if (n == null)
			return null;
		if (n.right == null) {
			Iterator itr = new AVLTreeIterator(n);
			return itr;
		}
		return findMax(n.right);
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, AVLNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new AVLTreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public AVLNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public AVLNode insert(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode insertNoBalance(int v) {
		return insertNoBalance(v, root, null);
	}

	private AVLNode insertNoBalance(int v, AVLNode n, AVLNode parent) {
		if (n == null) {
			n = new AVLNode(v, null, null, parent, 0);
			size++;
		} else if (v < n.data) {
			n.left = insertNoBalance(v, n.left, n);
		} else if (v > n.data) {
			n.right = insertNoBalance(v, n.right, n);
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public AVLNode remove(int v, AVLNode n, AVLNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n = null;
				size--;
			} else if (n.left != null && n.right == null) {
				n.left.parent = parent;
				n = n.left;
				size--;
			} else if (n.right != null && n.left == null) {
				n.right.parent = parent;
				n = n.right;
				size--;
			} else {
				AVLTreeIterator i = (AVLTreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		n = rebalance(n);
		return n;
	}

	public AVLNode rebalance(AVLNode n) {
		if (n == null)
			return n;
		int balance = AVLNode.tiltDegree(n);
		if (balance >= 2) {
			if (AVLNode.tiltDegree(n.left) <= -1) // 3rd case
				n.left = rotateRightChild(n.left);
			n = rotateLeftChild(n); // 1st case
		} else if (balance <= -2) {
			if (AVLNode.tiltDegree(n.right) >= 1) // 4th case
				n.right = rotateLeftChild(n.right);
			n = rotateRightChild(n); // 2nd case
		}
		AVLNode.updateHeight(n);
		return n;
	}

	public AVLNode rotateLeftChild(AVLNode n) {
		AVLNode l = n.left;
		AVLNode lr = n.left.right; // can be null
		n.left = lr;
		if (lr != null) {
			lr.parent = n;
		}
		l.right = n;
		l.parent = n.parent;
		n.parent = l;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(l);
		return l;
	}

	public AVLNode rotateRightChild(AVLNode n) {
		AVLNode r = n.right;
		AVLNode rl = n.right.left; // can be null
		n.right = rl;
		if (rl != null) {
			rl.parent = n;
		}
		r.left = n;
		r.parent = n.parent;
		n.parent = r;

		AVLNode.updateHeight(n);
		AVLNode.updateHeight(r);
		return r;
	}

	public void storeNodeValues(AVLNode root, ArrayList<Integer> node_values) {
		if (root != null) {
			storeNodeValues(root.left, node_values);
			node_values.add(root.data);
			storeNodeValues(root.right, node_values);
		}
	}

	public AVLNode buildTreeFromArray(ArrayList<Integer> node_values) {
		if (node_values.size() == 0) {
			return null;
		}

		AVLNode root = new AVLNode(node_values.get(node_values.size() / 2));

		ArrayList<Integer> left_arr = new ArrayList<Integer>();
		for (int i = 0; i < node_values.size() / 2; i++) {
			left_arr.add(node_values.get(i));
		}
		root.left = buildTreeFromArray(left_arr);

		ArrayList<Integer> right_arr = new ArrayList<Integer>();
		for (int i = (node_values.size() / 2) + 1; i < node_values.size(); i++) {
			right_arr.add(node_values.get(i));
		}
		root.right = buildTreeFromArray(right_arr);

		return root;
	}

	public void makeAVL() throws Exception {
		ArrayList<Integer> node_values = new ArrayList<Integer>();
		storeNodeValues(root, node_values);
		root = buildTreeFromArray(node_values);
		if (!isAVL(root)) {
			throw new Exception("Error: Tree is not AVL after makeAVL()");
		}
	}

	public boolean isAVL() {
		return isAVL(root);
	}

	private boolean isAVL(AVLNode node) {
		if (node == null) {
			return true;
		}
		int tilt = AVLNode.tiltDegree(node);
		if (tilt > 1 || tilt < -1) {
			return false;
		}
		return isAVL(node.left) && isAVL(node.right);
	}

	public static boolean same(AVLTree t1, AVLTree t2) {
		if (t1 == null && t2 == null) {
			return true;
		} else if (t1 == null || t2 == null) {
			return false;
		} else {
			return sameNode(t1.root, t2.root);
		}
	}

	private static boolean sameNode(AVLNode node1, AVLNode node2) {
		if (node1 == null && node2 == null) {
			return true;
		} else if (node1 == null || node2 == null) {
			return false;
		} else if (node1.data != node2.data) {
			return false;
		} else {
			return sameNode(node1.left, node2.left) && sameNode(node1.right, node2.right);
		}
	}

	public static void main(String[] args) throws Exception {
		// example: print a tree

		AVLTree t = new AVLTree();

		t.root = t.insertNoBalance(33);
		t.root = t.insertNoBalance(4);
		t.root = t.insertNoBalance(1);
		t.root = t.insertNoBalance(66);
		t.root = t.insertNoBalance(2);
		t.root = t.insertNoBalance(6);

		BTreePrinter.printNode(t.root);

	}

}
