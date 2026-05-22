public class MyArray<T> {
	private ArrayElement<T>[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;

    public MyArray(int capacity) {
        this.array = (ArrayElement<T>[]) new ArrayElement[capacity];
        this.size = 0;
    }
    
    public MyArray() {
        this(DEFAULT_CAPACITY);
    }   
    
    /***
     * Implement the following method.
     */
    public void reverse() {
    	throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
    }

    /***
     * Assumes valid input (not null) and non-full array.
     */
    public void insert(ArrayElement<T> element) {
        // Assumes that insert is always called when there is a place in the array to insert.    	
        element.setIndex(size);
        array[size] = element;
        size = size + 1;
    }

    /***
     * Assumes valid input (pointer to element which is currently in the array).
     */
    public void delete(ArrayElement<T> element) {
    	array[size - 1].setIndex(element.index());
    	array[element.index()] = array[size - 1];
    	size = size - 1; 
    }

    public ArrayElement<T> search(int k) {
        for (int i = 0; i < size; i = i + 1) {
            if (array[i].key() == k) {
                return array[i];
            }
        }
        return null;
    }
    
    public ArrayElement<T> get(int index){
    	if (index < 0 | index >= size)
    		throw new IllegalArgumentException("The method get, in the class MyArray, was called with illegal index: " + index);
    	return this.array[index];
    }
    
    public int size() {
        return this.size;
    }
    
    public boolean equals(Object other){
		boolean ans = true;        
		if (other instanceof MyArray<?>) {
			MyArray<?> castedOther = (MyArray<?>) other;
			if (this.size() != castedOther.size())
				ans = false;
			for (int i = 0; i < this.size() & ans; i = i + 1) {
				ans = ans & (this.get(i).equals(castedOther.get(i)));
			}
        }
		else
			ans = false;
        return ans;
	}
    
    public String toString() {
    	String s = "";
    	for (int i = 0; i < size; i = i + 1) {
    		s = s + array[i].toString();
    	}
    	return s;
    }
    
}
