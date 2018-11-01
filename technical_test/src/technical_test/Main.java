package technical_test;



public class Main {

	
	public static void main(String[] args) {
		RequestController controller = new RequestController();
		
		//Request request = controller.fillRequest();
		
		Request request = new Request("dave", "1.23456,-5.6788", "1.235,6.789");
		controller.sendRequest(request);
		
		
	}
}
