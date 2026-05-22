/**
 * This class represents a specific element in a specific data structure: 
 * an element in an array.
 * @param <T> The type of the satellite data.
 */
public class ArrayElement<T> extends Element<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private int index;		//The index of the element in the array.
	
	public ArrayElement(int key, T satelliteData) {
		super(key, satelliteData);
	}
	
	public ArrayElement(int key) {
		this(key, null);
	}
	
	public ArrayElement(Element<T> element) {
		this(element.key(), element.satelliteData());
	}

	public int index() {
		return this.index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
}
