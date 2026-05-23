/**
 * @param <T> The type of the satellite data of the elements in the data structure.
 */
public class MyFirstDataStructure<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are:
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private TreeNode<T> root;
	private TreeNode<T> head;
	private TreeNode<T> tail;
	private TreeNode<T> maxNode;

	/***
     * This function is the Init function.
	 * @param N The maximum number of elements in the data structure at each time.
     */
	public MyFirstDataStructure(int N) {
		this.root = null;
		this.head = null;
		this.tail = null;
		this.maxNode = null;
	}
	
	public void insert(Element<T> x) {
		TreeNode<T> newX = new TreeNode<> (x);

		// updates head, tail and maxNode
		if (this.head == null) {
			this.head = newX;
			this.tail = newX;
		} else {
			this.tail.setNext(newX);
			newX.setPrev(this.tail);
			this.tail = newX;
		}

		if (this.maxNode == null || this.maxNode.key() < newX.key()) {
			this.maxNode = newX;
		}

		TreeNode<T> y = null;			// the variable name "y" is consistent with lecture notes
		TreeNode<T> w = this.root;		// the variable name "w" is consistent with lecture notes
		while (w != null) {
			y = w;
			if (newX.key() < w.key())
				w = w.getLeft();
			else
				w = w.getRight();
		}
		newX.setParent(y);
		if (y == null)		// the tree was empty before the insertion
			this.root = newX;
		else if (newX.key() < y.key())
			y.setLeft(newX);
		else
			y.setRight(newX);

		/*
		 * going up the tree from the inserted leaf until one of the following:
		 * - the root
		 * - the first node its height did not change due to the insertion
		 * - the first node which is unbalanced
		 * update the heights of the ancestors of the inserted leaf,
		 * check if any ancestor became unbalanced and balance it
		 */
		boolean foundUnBalancedNode = false;
		boolean foundUnChangedHeight = false;
		while(y != null & !foundUnBalancedNode & !foundUnChangedHeight) {
			TreeNode<T> nextAncestor = y.getParent();

			int heightBeforeInsertion = y.getHeight();
			y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
			foundUnChangedHeight = (heightBeforeInsertion == y.getHeight());

			int balance = getBalance(y);

			//case left-left
			if (balance > 1 && newX.key() < y.getLeft().key()) {
				rightRotate(y);
				foundUnBalancedNode = true;
			}
			//case left-right
			else if (balance > 1 && newX.key() > y.getLeft().key()) {
				leftRotate(y.getLeft());
				rightRotate(y);
				foundUnBalancedNode = true;
			}
			//case right-right
			else if (balance < -1 && newX.key() > y.getRight().key()) {
				leftRotate(y);
				foundUnBalancedNode = true;
			}
			//case right-left
			else if (balance < -1 && newX.key() < y.getRight().key()) {
				rightRotate(y.getRight());
				leftRotate(y);
				foundUnBalancedNode = true;
			}

			y = nextAncestor;
		}
	}

	public void findAndRemove(int k) {
		TreeNode<T> nodeToDelete = search(k);

		if (nodeToDelete != null) {

			// DLL REMOVAL
			if (nodeToDelete.getPrev() != null) {
				nodeToDelete.getPrev().setNext(nodeToDelete.getNext());
			} else {
				this.head = nodeToDelete.getNext();
			}

			if (nodeToDelete.getNext() != null) {
				nodeToDelete.getNext().setPrev(nodeToDelete.getPrev());
			} else {
				this.tail = nodeToDelete.getPrev();
			}

			TreeNode<T> y = null;

			// THE 2-CHILD CASE
			if (nodeToDelete.getLeft() != null && nodeToDelete.getRight() != null) {
				TreeNode<T> successor = minValueNode(nodeToDelete.getRight());
				TreeNode<T> successorParent = successor.getParent();

				TreeNode<T> successorChild = successor.getRight();
				if (successorParent.getLeft() == successor) {
					successorParent.setLeft(successorChild);
				} else {
					successorParent.setRight(successorChild);
				}
				if (successorChild != null) {
					successorChild.setParent(successorParent);
				}

				successor.setParent(nodeToDelete.getParent());
				if (nodeToDelete.getParent() == null) {
					this.root = successor;
				} else if (nodeToDelete.getParent().getLeft() == nodeToDelete) {
					nodeToDelete.getParent().setLeft(successor);
				} else {
					nodeToDelete.getParent().setRight(successor);
				}

				successor.setLeft(nodeToDelete.getLeft());
				if (successor.getLeft() != null) successor.getLeft().setParent(successor);

				successor.setRight(nodeToDelete.getRight());
				if (successor.getRight() != null) successor.getRight().setParent(successor);

				successor.setHeight(nodeToDelete.getHeight());

				if (successorParent == nodeToDelete) {
					y = successor;
				} else {
					y = successorParent;
				}
			}

			// THE 0/1 CHILD CASE
			else {
				y = nodeToDelete.getParent();
				boolean isLeftChild = (y != null && nodeToDelete == y.getLeft());
				boolean isRightChild = (y != null && nodeToDelete == y.getRight());

				// the deleted node has only right child
				if (nodeToDelete.getRight() != null) {
					nodeToDelete.getRight().setParent(y);
					if (isLeftChild) {
						y.setLeft(nodeToDelete.getRight());
					}
					else if (isRightChild) {
						y.setRight(nodeToDelete.getRight());
					}
					else {
						root = nodeToDelete.getRight();
					}
				}
				// the deleted node is a leaf or has only left child
				else {
					if (nodeToDelete.getLeft() != null) {
						nodeToDelete.getLeft().setParent(y);
					}
					if (isLeftChild) {
						y.setLeft(nodeToDelete.getLeft());
					}
					else if (isRightChild) {
						y.setRight(nodeToDelete.getLeft());
					}
					else {
						root = nodeToDelete.getLeft();
					}
				}
			}

			while(y != null) {
				TreeNode<T> nextAncestor = y.getParent();

				y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
				int balance = getBalance(y);

				//case left-left
				if (balance > 1 && getBalance(y.getLeft()) >= 0) {
					rightRotate(y);
				}
				//case left-right
				else if (balance > 1 && getBalance(y.getLeft()) < 0) {
					leftRotate(y.getLeft());
					rightRotate(y);
				}
				//case right-right
				else if (balance < -1 && getBalance(y.getRight()) <= 0) {
					leftRotate(y);
				}
				//case right-left
				else if (balance < -1 && getBalance(y.getRight()) > 0) {
					rightRotate(y.getRight());
					leftRotate(y);
				}

				y = nextAncestor;
			}

			// updates maxNode
			if (this.maxNode == nodeToDelete) {
				this.maxNode = maxValueNode(this.root);
			}

		}
	}

	private TreeNode<T> minValueNode(TreeNode<T> root){
		TreeNode<T> curr = root;
		while (curr.getLeft() != null) {
			curr = curr.getLeft();
		}
		return curr;
	}

	private int getBalance(TreeNode<T> node) {
		if (node == null) {
			return 0;
		}
		return height(node.getLeft()) - height(node.getRight());
	}

	private void rightRotate(TreeNode<T> x) {
		TreeNode<T> y = x.getLeft();		// the variable name "y" is consistent with lecture notes
		TreeNode<T> B = y.getRight();		// the variable name "B" is consistent with lecture notes
		TreeNode<T> u = x.getParent();		// the variable name "u" is consistent with lecture notes

		// Perform rotation
		y.setRight(x);
		x.setLeft(B);
		if (u == null)
			root = y;
		else if (x == u.getLeft())
			u.setLeft(y);
		else
			u.setRight(y);

		x.setParent(y);
		y.setParent(u);
		if (B != null)
			B.setParent(x);

		// Update heights
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
	}

	private void leftRotate(TreeNode<T> x) {
		TreeNode<T> y = x.getRight();		// the variable name "y" is consistent with lecture notes
		TreeNode<T> B = y.getLeft();		// the variable name "B" is consistent with lecture notes
		TreeNode<T> u = x.getParent();		// the variable name "u" is consistent with lecture notes

		// Perform rotation
		y.setLeft(x);
		x.setRight(B);
		if (u == null)
			root = y;
		else if (x == u.getLeft())
			u.setLeft(y);
		else
			u.setRight(y);

		x.setParent(y);
		y.setParent(u);
		if (B != null)
			B.setParent(x);

		// Update heights
		x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);
		y.setHeight(Math.max(height(y.getLeft()), height(y.getRight())) + 1);
	}

	private int height(TreeNode<T> node) {
		if (node == null) {
			return -1;
		}
		return node.getHeight();
	}

	public TreeNode<T> search(int k) {
		return searchRecursive(root, k);
	}

	// Recursive function to search for a node in the tree
	private TreeNode<T> searchRecursive(TreeNode<T> root, int k) {
		if (root == null || root.key() == k) {
			return root;
		}

		// Traverse left subtree if the key of the current node is greater than the searched key
		if (root.key() > k) {
			return searchRecursive(root.getLeft(), k);
		}

		// Traverse right subtree if the key of the current node is less than the search node's key
		return searchRecursive(root.getRight(), k);
	}

	private TreeNode<T> maxValueNode(TreeNode<T> root){
		TreeNode<T> curr = root;
		while (curr.getRight() != null) {
			curr = curr.getRight();
		}
		return curr;
	}

	public Element<T> maximum() {
		return this.maxNode;
	}

	public Element<T> first() {
		return this.head;
	}

	public Element<T> last() {
		return this.tail;
	}

}
