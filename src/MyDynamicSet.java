import java.util.List;

/**
 * @param <T> The type of the satellite data of the elements in the dynamic-set.
 */
public class MyDynamicSet<T> {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private MySortedLinkedList<T> list;

	/**
	 * The constructor should initiate an empty dynamic-set.
	 * @param N The maximum number of elements in the dynamic set at each time.
	 */
	public MyDynamicSet(int N) {
		list = new MySortedLinkedList<>();
	}
	
	public Element<T> search(int k) {
		return list.search(k);
	}
	
	public void insert(Element<T> x) {
		ListLink<T> newLink = new ListLink<>(x);
		list.insert(newLink);
	}
	
	public void delete(Element<T> x) {
		list.delete((ListLink<T>) x);
	}
	
	public Element<T> minimum() {
		return list.head();
	}
	
	public Element<T> maximum() {
		return list.tail();
	}
	
	public Element<T> successor(Element<T> x) {
		return ((ListLink<T>) x).getNext();
	}
	
	public Element<T> predecessor(Element<T> x) {
		return ((ListLink<T>) x).getPrev();
	}
}
