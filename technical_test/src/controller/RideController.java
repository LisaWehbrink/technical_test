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


public class RideController {
	
	private RideRequest request;
	private String pickup;
	private String dropoff;
	private int passengers;
	
	
	public RideRequest fillRequest() {
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		request = null;
		
		
		try {
			System.out.println("Please enter the desired pickup location.");
			pickup = inputReader.readLine();
			
			System.out.println("Please enter the desired dropoff location.");
			dropoff = inputReader.readLine();
			
			request = new RideRequest();
			
			System.out.println("Please enter the number of passengers.");
			passengers = Integer.parseInt(inputReader.readLine());
			
			
			inputReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return request;
	
	}
	
	
	public void sendRequest() {	
		String[] suppliers = new String[]{"dave", "eric", "jeff"};
		String path = createPath(pickup, dropoff);
		
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
					addOutput(line, supplier);
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
	
	
	private String createPath(String pickup, String dropoff) {
		String base = "https://techtest.rideways.com/{supplier}"
				+ "/?pickup={pickup}&dropoff={dropoff}";
		
		base = base.replace("{pickup}", pickup);
		String path = base.replace("{dropoff}", dropoff);		
		
		return path;
	}
	
	
	private void addOutput(String line, String supplier) {
		JSONObject json;
		
		try {
			json = new JSONObject(line);
		
			JSONArray entries = json.getJSONArray("options");
			
			for(int i = 0; i < entries.length(); i++) {
				JSONObject entry = entries.getJSONObject(i);
				String type = entry.getString("car_type");
				boolean fits = validateCarSize(type, passengers);
				int price = entry.getInt("price");
				
				RideResult result = new RideResult(supplier, type, price);
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
	
	public RideRequest getRequest() {
		return request;
	}
	
	public void fillTestRequest(String pickup, String dropoff) {
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.passengers = 2;
		request = new RideRequest();
	}
	

}
