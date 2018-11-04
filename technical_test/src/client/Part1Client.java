package client;

import java.util.List;

import controller.RideController;
import model.RideRequest;
import model.RideResult;


public class Part1Client {

	
	public static void main(String[] args) {
		RideController controller = new RideController();
		
		controller.fillRequest();
		controller.sendRequest();
		RideRequest request = controller.getRequest();
		
		List<RideResult> results = request.getResults();
		
		if(results == null) {
			System.out.println("No results found.");
			System.exit(0);
		}
		
		for(RideResult result : results) {
			System.out.println(result.toString());
		}
		
		
		
	}
}
