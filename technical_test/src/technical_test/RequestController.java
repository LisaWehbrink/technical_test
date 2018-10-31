package technical_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestController {
	
	public void fillRequest(Request request) {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Please enter the desired pickup location.");
			String pickup = inputReader.readLine();
			request.setPickup(pickup);
			
			System.out.println("Please enter the desired dropoff location.");
			String dropoff = inputReader.readLine();
			request.setDropoff(dropoff);
			
			System.out.println("Please enter the number of passengers.");
			String passengers = inputReader.readLine();
			request.setPassengers(passengers);
			
			inputReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Please observe the required format: latitude,longitude");
			System.exit(1);
		}
	
	}

}
