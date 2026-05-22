public class MySortedArray<T> {
    private ArrayElement<T>[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 20;

    public MySortedArray(int capacity) {
        this.array = (ArrayElement<T>[]) new ArrayElement[capacity];
        this.size = 0;
    }
    
    public MySortedArray() {
        this(DEFAULT_CAPACITY);
    }

    /***
     * Assumes valid input (not null) and non-full array.
     */
    public void insert(ArrayElement<T> element) {
    	// Assumes that insert is always called when there is a place in the array to insert.
        int index = findInsertionIndex(element);

        // Shift elements to make space for the new element
        for (int i = size; i > index; i = i - 1) {
        	array[i] = array[i - 1];
        	array[i].setIndex(i);
        }

        // Insert the new element
        element.setIndex(index);
        array[index] = element;
        
        size = size + 1;
    }

    /***
     * Assumes valid input (pointer to element which is currently in the array).
     */
    public void delete(ArrayElement<T> element) {
    	for (int i = element.index(); i < size - 1; i = i + 1) {
    		array[i] = array[i + 1];
    		array[i].setIndex(i);
        }
    	size = size - 1;
    }

    public ArrayElement<T> search(int k) {
    	int low = 0, high = size - 1;
    	while (low <= high){
	    	int middle = (low+high)/2;
	    	if(array[middle].key() == k)
	    		return array[middle];
	    	else if (k < array[middle].key())
	    		high = middle - 1;
	    	else 
	    		low = middle + 1;
    	}
        return null;
    }

    private int findInsertionIndex(ArrayElement<T> element) {
    	if (size == 0 || element.key() < array[0].key())
    		return 0;
    	if (element.key() > array[size - 1].key())
    		return size;
    	int low = 1, high = size - 1;
    	int ans = 0;
    	boolean found = false;
    	while (!found){
	    	int middle = (low+high)/2;
	    	if(array[middle - 1].key() < element.key() & array[middle].key() >= element.key())
	    		{ans = middle;	found = true;}
	    	else if (element.key() < array[middle].key())
	    		high = middle - 1;
	    	else 
	    		low = middle + 1;
    	}
    	return ans;
    }

    public ArrayElement<T> get(int index){
    	if (index < 0 | index >= size)
    		throw new IllegalArgumentException("The method get, in the class MySortedArray, was called with illegal index: " + index);
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
