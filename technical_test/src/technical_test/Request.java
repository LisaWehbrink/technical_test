package technical_test;

public class Request {
	
	private double pickupLatitude;
	private double pickupLongitude;
	private double dropoffLatitude;
	private double dropoffLongitude;
	
	private int passengers;
	
	private String supplier;
	
	public Request(String supplier) {
		this.supplier = supplier;
		
	}
	
	public void setPickup(String input) {
		double[] pickup = parseInput(input);
		
		pickupLatitude = pickup[0];
		pickupLongitude = pickup[1];
	}
	
	public void setDropoff(String input) {
		double[] dropoff = parseInput(input);
		
		dropoffLatitude = dropoff[0];
		dropoffLongitude = dropoff[1];
	}
	
	private double[] parseInput(String input) {
		double[] result = new double[2];
		String[] values = input.split(",");
		
		result[0] = Double.parseDouble(values[0]);
		result[1] = Double.parseDouble(values[1]);
		
		
		return result;
	}
	
	public void setPassengers(String passengers) {
		this.passengers = Integer.parseInt(passengers);
	}

	
	public double getPickupLongitude() {
		return pickupLongitude;
	}

	public double getPickupLatitude() {
		return pickupLatitude;
	}

	public double getDropoffLatitude() {
		return dropoffLatitude;
	}

	public double getDropoffLongitude() {
		return dropoffLongitude;
	}

	public String getSupplier() {
		return supplier;
	}

	public int getPassengers() {
		return passengers;
	}

	
}
