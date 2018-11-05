package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import controller.RideController;
import controller.RideRestController;
import model.RideRequest;

/**
 * 
 * @author lisa
 * Spring application for part 2 (Rest API)
 */
@SpringBootApplication
@ComponentScan(basePackageClasses = RideRestController.class)
public class RestClient {
	
	/**
	 * 
	 * @param args
	 */
	 public static void main(String[] args) {
		 	
	        SpringApplication.run(RestClient.class, args);
	    }
	 
	 /**
	  * 
	  * @return request
	  */
	 @Bean
	 public RideRequest createRequest() {
		RideController controller = new RideController();
		String[] suppliers = new String[]{"dave", "eric", "jeff"};
			
		controller.fillRequest(true);
		controller.sendRequest(suppliers);
		
		return controller.getRequest();
	 }
}
