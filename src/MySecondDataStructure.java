
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
		ListLink<Product> toRemove = list.search(id);
		if (toRemove != null) {
			list.delete(toRemove);
			int removedQuality = toRemove.satelliteData().quality();
			qualityNum[removedQuality] --;
			if (maxValues[removedQuality] == toRemove.satelliteData()) {
				maxValues[removedQuality] = findMax(removedQuality);
			}
		}
	}

	private Product findMax(int quality) {
		Product result = null;
		ListLink<Product> head = list.head();
		while (head != null) {
			int headQuality = head.satelliteData().quality();
			int headPrice = head.satelliteData().price();
			if ((headQuality == quality) && (result == null || headPrice > result.price()))
				result = head.satelliteData();
			head = head.getNext();
		}
		return result;
	}

	public int medianQuality() {
		int sum = 0;
        for (int j : qualityNum) {
            sum += j;
        }
		if (sum == 0) {
			return -1;
		}
		int target = qualityNum[0];
		int medianQuality = 0;
		while (target < (sum+1) / 2) {
			medianQuality++;
			target += qualityNum[medianQuality];
		}
		return medianQuality;
	}
	
	public double avgQuality() {
		int sum = 0;
		double mult = 0.0;
		for (int i = 0; i < qualityNum.length; i++) {
			sum += qualityNum[i];
			mult += qualityNum[i] * i;
		}
		if (sum == 0) {
			return -1;
		}
		return mult / sum;
	}

	public void raisePrice(int raise, int quality) {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}

	public Product mostExpensive() {
		throw new UnsupportedOperationException("Delete this line and replace it with your implementation");
	}

}
