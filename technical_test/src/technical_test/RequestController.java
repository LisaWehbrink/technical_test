package technical_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





public class RequestController {
	
	public Request fillRequest() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		Request request = null;
		
		
		try {
			System.out.println("Please enter the desired pickup location.");
			String pickup = inputReader.readLine();
			
			System.out.println("Please enter the desired dropoff location.");
			String dropoff = inputReader.readLine();
			
			request = new Request ("dave", pickup, dropoff);
			
			System.out.println("Please enter the number of passengers.");
			String passengers = inputReader.readLine();
			request.setPassengers(passengers);
			
			inputReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return request;
	
	}
	
	
	public String sendRequest(Request request) {
		String result = "";
		
		try {
			URL url = new URL(request.getPath());
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line;
			while((line = reader.readLine()) != null) {
				JSONObject json = new JSONObject(line);
				formatOutput(json);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		
		return result;
	}
	
	private void formatOutput(JSONObject json) throws JSONException {
		JSONArray entries = json.getJSONArray("options");
		
		for(int i = 0; i < entries.length(); i++) {
			JSONObject entry = entries.getJSONObject(i);
			System.out.println(entry.getString("car_type") + " - " + entry.getString("price"));
		}
	}
	

}
