package technical_test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

	
	public static void main(String[] args) {
		RequestController controller = new RequestController();
		
		Request request = controller.fillRequest();
		controller.sendRequest(request);
		List<Result> results = request.getResults();
		
		if(results == null) {
			System.out.println("No results found.");
			System.exit(0);
		}
		
		Collections.sort(results,new Comparator<Result>() {
	         @Override
	        public int compare(Result r1, Result r2) {
	                return r1.compareTo(r2);
	        }
	    });
		
		
		for(Result r : results) {
			String output = String.format("%s - %s - %d",
					r.getType(), r.getSupplier(), r.getPrice());
			System.out.println(output);
		}
		
		
		
	}
}
