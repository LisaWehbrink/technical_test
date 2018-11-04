package tests;


import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import part1.RideRequest;
import part1.RequestController;
import part1.RideResult;

class RequestControllerTest {
	
	private RideRequest request;
	private RequestController controller;
	
	@BeforeEach
	public void createSampleRequest() {
		request = new RideRequest("51.470020,-0.454295", "51.5744,-0.4144");
		controller = new RequestController();
	}

	@Test
	void duplicateOutput() {
		controller.sendRequest(request);
		List<RideResult> results = request.getResults();
		
		Assert.assertEquals(new HashSet<>(results).size(), results.size());
	}
	
	
	
	

}
