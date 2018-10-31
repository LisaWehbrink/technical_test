package technical_test;



public class Main {

	
	public static void main(String[] args) {
		Request request = new Request("Dave");
		RequestController controller = new RequestController();
		
		controller.fillRequest(request);
		
		System.out.println(request.getPassengers());
		
	}
}
