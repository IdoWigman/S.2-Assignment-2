/**
 * This class represents a general element in a general data structure, 
 * with key and satellite data.
 * @param <T> The type of the satellite data.
 */
public class Element<T> {	
//--------------------fields-------------------------------------------
	private int key;
	private T satelliteData;

//--------------------constructors-------------------------------------
	public Element(int key, T satelliteData) {
		this.key = key;
		this.satelliteData = satelliteData;
	}
	
	public Element(int key) {
		this(key, null);
	}
	
	public Element(Element<T> element) {
		this(element.key(), element.satelliteData());
	}

//--------------------methods-------------------------------------
	public int key() {
		return this.key;
	}
	
	public T satelliteData() {
		return this.satelliteData;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public void setSatData(T satelliteData) {
		this.satelliteData = satelliteData;
	}
	
	public boolean equals(Object other){
		boolean ans = false;        
		if (other instanceof Element<?>) {
			Element<?> castedOther = (Element<?>) other;  
			boolean sameSatData;
			if (this.satelliteData() == null)
				sameSatData = (castedOther.satelliteData() == null);
			else {
				sameSatData = this.satelliteData().equals(castedOther.satelliteData());
			}
            ans = this.key() == castedOther.key() & sameSatData;
        }        
        return ans;
	}
	
	public String toString() {
		return "[" + this.key() + "]";
		//return "[" + this.key() + ", " + this.satelliteData() + "]";
	}
	
}
