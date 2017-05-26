
import Adapter.CreateAuto;
import Adapter.PrintAuto;
import Adapter.UpdateAuto;
import Adapter.kbbFacade;

public class Driver extends Thread{
	private kbbFacade kbb;
	private int functNo;
	
	public Driver(String id, int functNo) {
		super(id);
		kbb = new kbbFacade();
		this.functNo = functNo;
	}
	
	public void run() {
		switch(functNo) {
		case 1:
			operation1();
			break;
		case 2:
			operation2();
			break;
		}
	}
	
	public void operation1() {
		kbb.updateOptionPrice("Ford Wagon ZTW", "Transmission", "manual", -500.0);
	}
	
	public void operation2() {
		kbb.updateOptionName("Ford Wagon ZTW", "Transmission", "manual", "stick shift");
	}
	
	public static void main(String[] args) {
		/** Test Code **/
		String fileName = "FordWagonZTW.auto";
		CreateAuto carBuilder = new kbbFacade();
		carBuilder.buildAuto(fileName);

		System.out.println("Start Test...");

    	try {
    		Driver t1 = new Driver("t1: ", 1);
        	Driver t2 = new Driver("t2: ", 2);
        	t1.start();
        	t2.start();
    		t1.join();
        	t2.join();
        	System.out.println("t1 is dead.");
    		System.out.println("t2 is dead.");
    	} catch (InterruptedException e) {}
		
		PrintAuto carPrint = new kbbFacade();
    	carPrint.printAllAuto();
	}
}
