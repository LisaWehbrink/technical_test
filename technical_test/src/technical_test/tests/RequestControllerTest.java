package technical_test.tests;


import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import technical_test.Request;
import technical_test.RequestController;
import technical_test.Result;

class RequestControllerTest {
	
	private Request request;
	private RequestController controller;
	
	@BeforeEach
	public void createSampleRequest() {
		request = new Request("51.470020,-0.454295", "51.5744,-0.4144");
		controller = new RequestController();
	}

	@Test
	void duplicateOutput() {
		controller.sendRequest(request);
		List<Result> results = request.getResults();
		
		Assert.assertEquals(new HashSet<>(results).size(), results.size());
	}
	
	
	
	

}
