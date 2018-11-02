package technical_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

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
			
			request = new Request (pickup, dropoff);
			
			System.out.println("Please enter the number of passengers.");
			String passengers = inputReader.readLine();
			request.setPassengers(passengers);
			
			inputReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return request;
	
	}
	
	
	public void sendRequest(Request request) {	
		String[] suppliers = request.getSuppliers();
		String path = request.getPath();
		
		for(int i = 0; i < suppliers.length; i++) {
			String supplier = suppliers[i];
			path = path.replace("{supplier}", supplier);
			
			try {
				URL url = new URL(path);
				URLConnection connection = url.openConnection();
				connection.setConnectTimeout(10000);
				connection.setReadTimeout(2000);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line;
				while((line = reader.readLine()) != null) {
					addOutput(line, request, supplier);
				}
							
				
			} catch (SocketTimeoutException timeout) {
				//to skip rest of code when response time is too long
				System.out.println("The results list may be incomplete due to abnormal response times.");
			} catch (IOException e) {
				//for broken API
				System.out.println("The results list may be incomplete due to connection errors.");
			}
		}
		
	}
	
	private void addOutput(String line, Request request, String supplier) {
		JSONObject json;
		
		try {
			json = new JSONObject(line);
		
			JSONArray entries = json.getJSONArray("options");
			
			for(int i = 0; i < entries.length(); i++) {
				JSONObject entry = entries.getJSONObject(i);
				String type = entry.getString("car_type");
				boolean fits = validateCarSize(type, request.getPassengers());
				int price = entry.getInt("price");
				
				Result result = new Result(supplier, type, price);
				if(fits)
					request.addResult(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	private boolean validateCarSize(String type, int passengers) {
		if(passengers <= 4)
			return true;
		
		boolean six = type.equals("PEOPLE_CARRIER") 
					|| type.equals("LUXURY_PEOPLE_CARRIER");
		boolean sixteen = type.equals("MINIBUS");
		
		if(passengers <= 6 && six || sixteen) 
			return true;
		if(passengers <= 16 && sixteen)
			return true;
		
		
		return false;
			
	}
	

}
