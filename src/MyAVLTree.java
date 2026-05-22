public class MyAVLTree<T> {
    private TreeNode<T> root;

    public MyAVLTree() {
    	this.root = null;
    }
    
    /***
     * Implement the following method.
     */
    public int depthOfMin() {
    	throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
    }
    
    
    /***
     * Insert a node into the tree.
     * Assumes valid input (not null).
     */
    public void insert(TreeNode<T> newNode) {    	
    	TreeNode<T> y = null;			// the variable name "y" is consistent with lecture notes
    	TreeNode<T> x = this.root;		// the variable name "x" is consistent with lecture notes
    	while (x != null) {
    		y = x;
    		if (newNode.key() < x.key())
    			x = x.getLeft();
    		else
    			x = x.getRight();
    	}
    	newNode.setParent(y);
    	if (y == null)		// the tree was empty before the insertion
    		this.root = newNode;
    	else if (newNode.key() < y.key())
    		y.setLeft(newNode);
    	else
    		y.setRight(newNode);
    	
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
            if (balance > 1 && newNode.key() < y.getLeft().key()) {
                rightRotate(y);
                foundUnBalancedNode = true;
            }
            //case left-right
            else if (balance > 1 && newNode.key() > y.getLeft().key()) {
                leftRotate(y.getLeft());
                rightRotate(y);
                foundUnBalancedNode = true;
            }
            //case right-right
            else if (balance < -1 && newNode.key() > y.getRight().key()) {
                leftRotate(y);
                foundUnBalancedNode = true;
            }
            //case right-left
            else if (balance < -1 && newNode.key() < y.getRight().key()) {
                rightRotate(y.getRight());
                leftRotate(y);
                foundUnBalancedNode = true;
            }
            
            y = nextAncestor;
    	}
    }
 
    /***
     * Delete a node from the tree.
     * Assumes valid input (pointer to node which is currently in the tree).
     */
    public void delete(TreeNode<T> nodeToDelete) {

    	// the deleted node has two children	
    	if (nodeToDelete.getLeft() != null & nodeToDelete.getRight() != null) {	
    		TreeNode<T> successor = minValueNode(nodeToDelete.getRight());
            nodeToDelete.setKey(successor.key()); nodeToDelete.setSatData(successor.satelliteData());
            delete(successor);
    	}
    	
    	// the deleted node has at most one child
    	else {
    		TreeNode<T> y = nodeToDelete.getParent();
        	boolean isLeftChild = (y != null && nodeToDelete == y.getLeft());
        	boolean isRightChild = (y != null && nodeToDelete == y.getRight());
        	
        	// the deleted node has only right child	
            if (nodeToDelete.getRight() != null) {
            	nodeToDelete.getRight().setParent(y);
    		    if (isLeftChild) {
    		    	y.setLeft(nodeToDelete.getRight());
    		    }
    		    else if (isRightChild)
    		    	y.setRight(nodeToDelete.getRight());
    		    else
    		    	root = nodeToDelete.getRight();
            }
            
            // the deleted node is a leaf or has only left child
            else {
            	if (nodeToDelete.getLeft() != null)
            		nodeToDelete.getLeft().setParent(y);
    	        if (isLeftChild) {
    	        	y.setLeft(nodeToDelete.getLeft());
    	        }
    	        else if (isRightChild)
    	           	y.setRight(nodeToDelete.getLeft());
    	        else
    		        root = nodeToDelete.getLeft();
            }
        	
           /*
        	* going up the tree from the deleted node until one of the following:
        	* - the root
        	* - the first node its height did not change due to the insertion
        	* update the heights of the ancestors of the inserted leaf,
        	* check if any ancestor became unbalanced and balance it
        	*/    	
        	boolean foundUnChangedHeight = false;
        	while(y != null & !foundUnChangedHeight) { 
        		TreeNode<T> nextAncestor = y.getParent();
        		
        		int heightBeforeInsertion = y.getHeight();
        		y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        		foundUnChangedHeight = (heightBeforeInsertion == y.getHeight());
                
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
    	}	 	    	
    }

    private TreeNode<T> minValueNode(TreeNode<T> root){
    	TreeNode<T> curr = root;
    	while (curr.getLeft() != null) {
    		curr = curr.getLeft();
    	}
    	return curr;
    }

     // Search for a key in the tree
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

    // Get the balance factor of the node
    private int getBalance(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }

    // Right rotate subtree rooted in x
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

    // Left rotate subtree rooted in x
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
    
    // Get the height of the node
    private int height(TreeNode<T> node) {
        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }
    
    public TreeNode<T> root(){
    	return this.root;
    }
    
}
