package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class RideRequest {
	
	
	
	private List<RideResult> results;
	
	public RideRequest() {
		
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
	

	public List<RideResult> getResults() {
		return results;
	}
	
	@Override
	public String toString() {
		sortResults();
		
		StringBuilder output = new StringBuilder();
		for(RideResult r : results) {
			output.append(r.toString());
			output.append("/n");
		}
		
		return output.toString();
	}
	
	private void sortResults() {
		Collections.sort(results,new Comparator<RideResult>() {
	         @Override
	        public int compare(RideResult r1, RideResult r2) {
	                return r1.compareTo(r2);
	        }
	    });
		
	}

}
