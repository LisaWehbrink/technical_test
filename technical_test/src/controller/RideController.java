package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.RideRequest;
import model.RideResult;

/**
 * 
 * @author lisa
 * Retrieving request results
 */
public class RideController {
	
	private RideRequest request;
	private String pickup;
	private String dropoff;
	private int passengers;
	private boolean connectionErrors;
	
	
	/**
	 * 
	 * Get pickup / dropoff / passenger input
	 */
	public void fillRequest() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		request = null;
		
		try {
			System.out.println("Please enter the desired pickup location.");
			pickup = inputReader.readLine();
			
			System.out.println("Please enter the desired dropoff location.");
			dropoff = inputReader.readLine();
			
			System.out.println("Please enter the number of passengers.");
			passengers = Integer.parseInt(inputReader.readLine());
						
			inputReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.request = new RideRequest();
	}
	
	
	/**
	 * For test cases to avoid manual input
	 * @param pickup
	 * @param dropoff
	 */
	public void fillTestRequest(String pickup, String dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.passengers = 2;
		
		request = new RideRequest();
	}
	
	
	/**
	 * Get results from REST API
	 */
	public void sendRequest(String[] suppliers) {	
		String path = createPath(pickup, dropoff);
		this.connectionErrors = false;
		
		for(int i = 0; i < suppliers.length; i++) {
			String supplier = suppliers[i];
			path = path.replace("{supplier}", supplier);
			
			try {
				URL url = new URL(path);
				URLConnection connection = url.openConnection();
				connection.setConnectTimeout(2000);
				connection.setReadTimeout(2000);
				
				BufferedReader reader = 
						new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line;
				while((line = reader.readLine()) != null) {
					addOutput(line, supplier);
				}
							
				
			} catch (SocketTimeoutException timeout) {
				//to skip rest of code when response time is too long
			} catch (IOException e) {
				//for broken API
				connectionErrors = true;
			}
		}
	}
	
	
	/**
	 * Create path for request to REST API
	 * @param pickup
	 * @param dropoff
	 * @return path
	 */
	private String createPath(String pickup, String dropoff) {
		String path = "https://techtest.rideways.com/{supplier}"
				+ "/?pickup={pickup}&dropoff={dropoff}";
		
		path = path.replace("{pickup}", pickup);
		path = path.replace("{dropoff}", dropoff);		
		
		return path;
	}
	
	
	/**
	 * Parse output JSON, create Result and add to Request
	 * @param line
	 * @param supplier
	 */
	private void addOutput(String line, String supplier) {
		JSONObject json;
		
		try {
			json = new JSONObject(line);
			
			JSONArray entries = json.getJSONArray("options");
			
			for(int i = 0; i < entries.length(); i++) {
				JSONObject entry = entries.getJSONObject(i);
				
				String type = entry.getString("car_type");
				int price = entry.getInt("price");
				
				if(validateCarSize(type, passengers)) {
					RideResult result = new RideResult(supplier, type, price);
					request.addResult(result);
				}
			}
			request.sortResults();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param type
	 * @param passengers
	 * @return true / false 
	 * depending on whether type fits passengers
	 */
	private boolean validateCarSize(String type, int passengers) {
		if(passengers <= 4)
			return true;
		
		boolean six = type.equals("PEOPLE_CARRIER") || type.equals("LUXURY_PEOPLE_CARRIER");
		boolean sixteen = type.equals("MINIBUS");
		
		if(passengers <= 6 && six || sixteen) 
			return true;
		if(passengers <= 16 && sixteen)
			return true;
		
		return false;	
	}
	
	
	/**
	 * 
	 * @return request
	 */
	public RideRequest getRequest() {
		return request;
	}
	
	
	/**
	 * 
	 * @return connectionErrors
	 */
	public boolean getConnectionErrors() {
		return connectionErrors;
	}
	
	

}
