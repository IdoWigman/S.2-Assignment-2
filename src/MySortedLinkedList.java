public class MySortedLinkedList<T> {
    private ListLink<T> head;
    private ListLink<T> tail;

    public MySortedLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    /***
     * Assumes valid input (not null).
     */
    public void insert(ListLink<T> element) {
        if (head == null || head.key() > element.key()) {
        	element.setPrev(null);
        	element.setNext(head);
            if (head != null)
                head.setPrev(element);
            head = element;
            if (tail == null)
                tail = element;
        } else {
        	ListLink<T> current = head;
            while (current.getNext() != null && current.getNext().key() <= element.key()) {
                current = current.getNext();
            }
            element.setNext(current.getNext());
            if (current.getNext() != null)
                current.getNext().setPrev(element);
            current.setNext(element);
            element.setPrev(current);
            if (current == tail)
                tail = element;
        }
    }

    /***
     * Assumes valid input (pointer to link which is currently in the linked-list).
     */
    public void delete(ListLink<T> element) {
    	if (element == head && element == tail) {
            head = null;
            tail = null;
        } else if (element == head) {
    		head = head.getNext();
            head.setPrev(null);
        } else if (element == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
        } else {
            element.getPrev().setNext(element.getNext());
            element.getNext().setPrev(element.getPrev());
        }         
    }

    public ListLink<T> search(int k) {
    	ListLink<T> current = head;
        while (current != null) {
            if (current.key() == k) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
    
    public ListLink<T> head(){
    	return head;
    }
    
    public ListLink<T> tail(){
    	return tail;
    }
    
    public boolean equals(Object other){
		boolean ans = true;        
		if (other instanceof MyLinkedList<?>) {
			MyLinkedList<?> castedOther = (MyLinkedList<?>) other;
			ListLink<T> thisCurrent = this.head();
			ListLink<T> otherCurrent = (ListLink<T>)castedOther.head();
			while (thisCurrent != null & otherCurrent != null & ans) {
				ans = ans & thisCurrent.equals(otherCurrent);
				thisCurrent = thisCurrent.getNext();
				otherCurrent = otherCurrent.getNext();
			}
			if (ans & (thisCurrent != null | otherCurrent != null))		// if after the while loop ans is true, and one of the lists was not scanned to the end, the lists are not equal.
				ans = false;
        }		
		else
			ans = false;
		
        return ans;
	}
    
    public String toString() {
    	String s = "";
    	ListLink<T> current = this.head();
    	while (current != null) {
    		s = s + current.toString();
    		current = current.getNext();
    	}
    	return s;
    }
    
}