/**
 * This class represents a specific element in a specific data structure: 
 * a link in a linked-list.
 * @param <T> The type of the satellite data.
 */
public class ListLink<T> extends Element<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private ListLink<T> next;
	private ListLink<T> prev;
	
	public ListLink(int key, T satelliteData) {
		super(key, satelliteData);
		this.next = null;
		this.prev = null;
	}
	
	public ListLink(int key) {
		this(key, null);
	}
	
	public ListLink(Element<T> element) {
		this(element.key(), element.satelliteData());
	}
	
	public ListLink<T> getNext() {
		return this.next;
	}
	
	public ListLink<T> getPrev() {
		return this.prev;
	}
	
	public void setNext(ListLink<T> next) {
		this.next = next;
	}
	
	public void setPrev(ListLink<T> prev) {
		this.prev = prev;
	}

}
