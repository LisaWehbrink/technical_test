package technical_test;


public class Request {
	
	private String pickup;
	private String dropoff;
	
	private int passengers;
	
	private String supplier;
	
	private String path;
	
	public Request(String supplier, String pickup, String dropoff) {
		this.supplier = supplier;
		this.pickup = pickup;
		this.dropoff = dropoff;
		parsePath();
		
	}
	
	
	
	private void parsePath() {
		String base = "https://techtest.rideways.com/" + supplier
				+ "/?pickup={pickup}&dropoff={dropoff}";
		
		base = base.replace("{pickup}", pickup);
		path = base.replace("{dropoff}", dropoff);		
	}
	
	
	public void setPassengers(String passengers) {
		this.passengers = Integer.parseInt(passengers);
	}

	
	
	public String getPickup() {
		return pickup;
	}



	public String getDropoff() {
		return dropoff;
	}


	
	public String getSupplier() {
		return supplier;
	}


	public int getPassengers() {
		return passengers;
	}

	public String getPath() {
		return path;
	}

}
