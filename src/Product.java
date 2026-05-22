/**
 * This class represents a product,
 * with id and quality.
 */
public class Product {
	private int id;
	private int quality;
	private int price;
	private String name;


	public Product(int id, int quality, int price, String name) {
		this.id = id;
		this.quality = quality;
		this.price = price;
		this.name = name;
	}
	
	public int id() {
		return this.id;
	}
	
	public int quality() {
		return this.quality;
	}

	public int price() {
		return this.price;
	}

	public String name() {
		return this.name;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean equals(Object other){
		boolean ans = false;
		if (other instanceof Product) {
			Product castedOther = (Product) other;
			ans = 	this.id() == castedOther.id();
		}
		return ans;
	}

	public String toString() {
		return ("Product with id: " + id);
	}
	
}
