package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 
 * @author lisa
 * Request results
 */
public class RideRequest {
	
	private List<RideResult> results;
	
	
	
	public RideRequest() {
		
	}
	
	
	/**
	 * 
	 * @param result
	 */
	public void addResult(RideResult result) {
		if(results == null)
			results = new ArrayList<>();
		
		
		if(isCheaper(result))
			results.add(result);
	}
	
	
	/**
	 * 
	 * @param result
	 * @return true / false
	 * based on whether ride already exists / exists with higher price
	 */
	private boolean isCheaper(RideResult result) {
		for(RideResult r : results) {
			
			if(r.getType().equals(result.getType())) {
				if(r.getPrice() < result.getPrice()) {
					return false;
				} else {
					results.remove(r);
					return true;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * Sorts results by price, descending
	 */
	public void sortResults() {
		Collections.sort(results,new Comparator<RideResult>() {
	         @Override
	        public int compare(RideResult r1, RideResult r2) {
	                return r1.compareTo(r2);
	        }
	    });
		
	}
	

	/**
	 * 
	 * @return results
	 */
	public List<RideResult> getResults() {
		return results;
	}
	
	
	@Override
	public String toString() {		
		StringBuilder output = new StringBuilder();
		
		for(RideResult r : results) {
			output.append(r.toString());
			output.append("/n");
		}
		
		return output.toString();
	}


}
