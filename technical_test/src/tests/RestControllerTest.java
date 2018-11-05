package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Testing Rest API
 * Start while Spring app is running
 * @author lisa
 *
 */
public class RestControllerTest {

	
	@Test
	public void testHttpResponse() {
		try {
			URL url = new URL("http://localhost:8080/ride");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			int status = conn.getResponseCode();
			
			assert(status == 200);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testJSONPresence() {
		try {
			URL url = new URL("http://localhost:8080/ride");
			URLConnection conn = url.openConnection();
			
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while((line = reader.readLine()) != null) {
				JSONArray json = new JSONObject(line).getJSONArray("results");
				for(int i = 0; i < json.length(); i++) {
					JSONObject obj = json.getJSONObject(i);
					
					assert(obj.get("type") != null);
					assert(obj.get("supplier") != null);
					assert(obj.get("price") != null);
				}
				
			}		
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testSorted() {
		try { 
			
			URL url = new URL("http://localhost:8080/ride");
			URLConnection conn = url.openConnection();
			
			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line;
			while((line = reader.readLine()) != null) {
				JSONArray json = new JSONObject(line).getJSONArray("results");
				int price = 0;
				
				for(int i = 0; i < json.length(); i++) {
					JSONObject obj = json.getJSONObject(i);
					
					int nextPrice = obj.getInt("price");
					if(price != 0)
						assert(nextPrice < price);
					price = nextPrice;
				}
				
			}		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (JSONException e) {
		e.printStackTrace();
	}
	}
	
}
