package part1;

public class RideResult {
	
	private String supplier;
	private String type;
	private int price;
	
	public RideResult(String supplier, String type, int price) {
		this.supplier = supplier;
		this.type = type;
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public String getType() {
		return type;
	}

	public int getPrice() {
		return price;
	}
	
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

}
