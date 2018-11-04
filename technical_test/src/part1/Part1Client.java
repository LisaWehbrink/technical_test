package part1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Part1Client {

	
	public static void main(String[] args) {
		RequestController controller = new RequestController();
		
		RideRequest request = controller.fillRequest();
		controller.sendRequest(request);
		List<RideResult> results = request.getResults();
		
		if(results == null) {
			System.out.println("No results found.");
			System.exit(0);
		}
		
		Collections.sort(results,new Comparator<RideResult>() {
	         @Override
	        public int compare(RideResult r1, RideResult r2) {
	                return r1.compareTo(r2);
	        }
	    });
		
		
		for(RideResult r : results) {
			System.out.println(r.toString());
		}
		
		
		
	}
}
