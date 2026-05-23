
public class MySecondDataStructure {
	/*
     * You may add any fields that you wish to add.
     * Remember that the use of built-in Java classes is not allowed,
     * the only variables types you can use are: 
     * 	-	the given classes in the assignment
     * 	-	basic arrays
     * 	-	primitive variables
     */
	private MyLinkedList<Product> list;
	private int[] qualityNum;
	private int[] qualityBonus;
	private Product[] maxValues;
	/***
     * This function is the Init function.
	 * @param N The maximum number of elements in the data structure at each time.
     */
	public MySecondDataStructure(int N) {
		this.list = new MyLinkedList<>();
		this.qualityNum = new int[6];
		this.qualityBonus = new int[6];
		this.maxValues = new Product[6];
	}
	
	public void insert(Product product) {
		ListLink<Product> newLink = new ListLink<> (product.id(), product);
		int quality = product.quality();
		int price = product.price();
		qualityNum[quality] ++;
		product.setPrice(price - qualityBonus[quality]);
		if (maxValues[quality] != null) {
			if (product.price() > maxValues[quality].price()) {
				maxValues[quality] = product;
			}
		}
		else
			maxValues[quality] = product;
		list.insert(newLink);
	}
	
	public void findAndRemove(int id) {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}
	
	public int medianQuality() {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}
	
	public double avgQuality() {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}

	public void raisePrice(int raise, int quality) {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}

	public Product mostExpensive() {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}

}
