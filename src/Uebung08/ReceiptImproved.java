public class ReceiptImproved {
	protected static final int MAX_PRODUCT_LENGTH = 20;
	protected String[] products;
	protected Integer[] prices;
	protected int savedProducts;

	public ReceiptImproved(int max) {
		// TODO
		if (max <= 0) {
			throw new IllegalArgumentException("Anzahl der Produkte kann nicht <= 0 sein");
		}
		products = new String[max];
		prices = new Integer[max];

	}

	public void register(String prod, String price) {
		// TODO
		
			if (prod.length() > MAX_PRODUCT_LENGTH) {
				throw new IllegalArgumentException("Produktname zu lang");
			} else if (prod == null || price == null) {
				throw new IllegalArgumentException("Produktname/Preis nicht vorhanden");
			} else if(products.length-1 ==savedProducts){
				throw new RuntimeException("Mehr Produkte als Speicherplatz");
			}
				products[savedProducts] = prod;
				try {
					prices[savedProducts] = Integer.parseInt(price);
				} catch (Exception e) {
					throw new IllegalArgumentException("Preis kein gueltiger Integer");
				}
				savedProducts++;
			}
	

		
			
		
	

	public String getLastProduct() {
		// TODO
		if (savedProducts > 0) {
			return products[savedProducts - 1];
		} else {
			throw new RuntimeException("Kein Produk vorhanden");
		}
	}

	public int sum() {
		// TODO
		int sum = 0;
		for (int i = 0; i < prices.length; i++) {
			sum += prices[i];
		}
		return sum;
	}

	public int average() {
		// TODO
		if (products.length == 0) {
			throw new RuntimeException("Nicht durch 0 teilen");
		}
		int sum = 0;
		int count = 0;
		for (int i = 0; i < savedProducts; i++) {
			sum += prices[i];
			count++;
		}
		return sum / count;
	}

}