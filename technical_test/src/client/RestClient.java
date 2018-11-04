package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import controller.RideController;
import controller.RideRestController;
import model.RideRequest;

@SpringBootApplication
@ComponentScan(basePackageClasses = RideRestController.class)
public class RestClient {
	 public static void main(String[] args) {
		 	
	        SpringApplication.run(RestClient.class, args);
	    }
	 
	 @Bean
	 public RideRequest createRequest() {
		 RideController controller = new RideController();
			
		controller.fillRequest();
		controller.sendRequest();
		
		return controller.getRequest();
	 }
}
