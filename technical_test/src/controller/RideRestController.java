package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.RideRequest;


/**
 * 
 * @author lisa
 * RequestMapping
 */
@RestController
public class RideRestController {
	
	private final RideRequest request;
	
	/**
	 * 
	 * @param request
	 */
	@Autowired
	public RideRestController(RideRequest request) {
		this.request = request;
	}
	
	/**
	 * Provide results at /ride
	 * @return ResponseEntity<RideRequest>
	 */
	@RequestMapping(value = "/ride", method = RequestMethod.GET)
	public ResponseEntity<RideRequest>  getRestResult() {
		
        return new ResponseEntity<RideRequest>(request, HttpStatus.OK);
	}
	

	
}
