package client;

import java.util.List;

import controller.RideController;
import model.RideRequest;
import model.RideResult;

/**
 * 
 * @author lisa
 *Run part 1
 */
public class Part1Client {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		RideController controller = new RideController();
		String[] suppliers = new String[]{"dave", "eric", "jeff"};
		
		controller.fillRequest();
		controller.sendRequest(suppliers);
		
		RideRequest request = controller.getRequest();
		List<RideResult> results = request.getResults();
		
		if(results == null) {
			System.out.print("No results found.");
			if(controller.getConnectionErrors())
				System.out.println(" The connection timed out.");
			System.exit(0);
		}
		
		for(RideResult result : results) {
			System.out.println(result.toString());
		}
		
		
		
	}
}
