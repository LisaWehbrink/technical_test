package tests;


import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.RideController;
import model.RideRequest;
import model.RideResult;

class RequestControllerTest {
	
	private RideRequest request;
	private RideController controller;
	
	@BeforeEach
	public void createSampleRequest() {
		controller = new RideController();
		controller.fillTestRequest("51.470020,-0.454295", "51.50949,-0.59541");
		controller.sendRequest();
		request = controller.getRequest();
	}
	
	@Test
	void outputExists() {
		
		List<RideResult> results = request.getResults();
		
		assert(results != null);
	}

	@Test
	void duplicateOutput() {
		List<RideResult> results = request.getResults();
		
		Assert.assertEquals(new HashSet<>(results).size(), results.size());
	}
	
	@Test
	void duplicateType() {
		List<RideResult> results = request.getResults();
		HashSet<String> types = new HashSet<String>();
		
		for(RideResult r : results) {
			types.add(r.getType());
		}
		
		Assert.assertEquals(types.size(), results.size());
	}
	
	
	
	
	
	

}
