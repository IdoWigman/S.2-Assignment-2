/**
 * This class represents a specific element in a specific data structure: 
 * a node in an AVL-tree.
 * @param <T> The type of the satellite data.
 */
public class TreeNode<T> extends Element<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private TreeNode<T> left;
	private TreeNode<T> right;
	private TreeNode<T> p;		//parent
	private int height;

	public TreeNode(int key, T satelliteData) {
		super(key, satelliteData);
		this.left = null;
		this.right = null;
		this.height = 0;
	}
	
	public TreeNode(int key) {
		this(key, null);
	}
	
	public TreeNode(Element<T> element) {
		this(element.key(), element.satelliteData());
	}
	
	public TreeNode<T> getLeft() {
		return this.left;
	}
	
	public TreeNode<T> getRight() {
		return this.right;
	}

	public TreeNode<T> getParent() {
		return p;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}

	public void setParent(TreeNode<T> p) {
		this.p = p;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

}
