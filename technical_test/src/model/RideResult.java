package model;


/**Single result (supplier, type, price)
 * 
 * @author lisa
 *
 */
public class RideResult {
	
	private String supplier;
	private String type;
	private int price;
	
	
	/**
	 * 
	 * @param supplier
	 * @param type
	 * @param price
	 */
	public RideResult(String supplier, String type, int price) {
		this.supplier = supplier;
		this.type = type;
		this.price = price;
	}

	
	/**
	 * 
	 * @return supplier
	 */
	public String getSupplier() {
		return supplier;
	}

	
	/**
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	
	/**
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	
	/**
	 * 
	 * @param result
	 * @return -1 / 0 / 1
	 */
	public int compareTo(RideResult result) {
		if(this.price >= result.getPrice()) 
			return -1;
		if(this.price == result.getPrice())
			return 0;
		else 
			return 1;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s - %s - %d", type, supplier, price);
	}
	
	public String getSingleSupplierString() {
		return String.format("%s - %d", type, price);
	}

}
