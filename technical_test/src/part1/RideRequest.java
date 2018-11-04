package part1;

import java.util.ArrayList;
import java.util.List;



public class RideRequest {
	
	private String pickup;
	private String dropoff;
	
	private int passengers;
	private String[] suppliers;
	
	private String path;
	
	private List<RideResult> results;
	
	public RideRequest(String pickup, String dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
		parsePath();
		
		suppliers = new String[]{"dave", "eric", "jeff"};
	}
	
	
	
	private void parsePath() {
		String base = "https://techtest.rideways.com/{supplier}"
				+ "/?pickup={pickup}&dropoff={dropoff}";
		
		base = base.replace("{pickup}", pickup);
		path = base.replace("{dropoff}", dropoff);		
	}
	
	
	public void setPassengers(String passengers) {
		this.passengers = Integer.parseInt(passengers);
	}
	
	public void addResult(RideResult result) {
		if(results == null)
			results = new ArrayList<>();
		
		if(!isCheaper(result))
			results.add(result);
	}
	
	private boolean isCheaper(RideResult result) {
		for(RideResult r : results) {
			if(r.getType().equals(result.getType())) {
				if(r.getPrice() < result.getPrice()) {
					return true;
				} else {
					results.remove(r);
					return false;
				}
			}
		}
		
		return false;
	}
	
	
	public String getPickup() {
		return pickup;
	}
	

	public String getDropoff() {
		return dropoff;
	}


	
	public int getPassengers() {
		return passengers;
	}
	
	public String[] getSuppliers() {
		return suppliers;
	}

	public String getPath() {
		return path;
	}
	
	public List<RideResult> getResults() {
		return results;
	}

}
