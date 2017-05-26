package Scale;

import Model.Automobile;

public class AutoEditor implements Runnable{
	private Automobile car;
	private int functNo;
	private String[] param;
	
	public AutoEditor(Automobile car, int functNo, String[] param) {
		this.car = car;
		this.functNo = functNo;
		this.param = param;
	}
	
	/* FunctNo = 0 */
	public void updateMaker(String newMaker) {
		synchronized(car) {
			car.setMaker(newMaker);
		}
	}

	/* FunctNo = 1 */
	public void updateModelName(String newName) {
		synchronized(car) {
			car.setModel(newName);
		}
	}
	
	/* FunctNo = 2 */
	public void updateBasePrice(Double newPrice) {
		synchronized(car) {
			car.setBasePrice(newPrice);	
		}
	}
	
	/* FunctNo = 3 */
	public void updateOptionSetName(String currentName, String newName) {
		synchronized(car) {
			car.updateOptionSetName(currentName, newName);	
		}
	}

	/* FunctNo = 4 */
	public void updateOptionName(String opSet, String currentName, String newName) {
		// For Testing only. Undo comment for test code
		/*synchronized(car) {
			for (int i = 0; i < 10; i++) {
				synchronized(System.out) {
					if(i%2 == 0) {
						System.out.println("updating op name to " + newName);
						car.updateOptionName(opSet, currentName, newName);
					} else {
						System.out.println("updating op name to " + currentName);
						car.updateOptionName(opSet, newName, currentName);
					}
				}
				randomWait();
			}
		}*/
		car.updateOptionName(opSet, currentName, newName);
	}
	
	/* FunctNo = 5 */
	public void updateOptionPrice(String opSet, String opt, Double newPrice) {	
		// For Testing only. Undo comment for test code
		/*synchronized(car) {
			for (int i = 0; i < 10; i++) {
				synchronized(System.out) {
					if (car.updateOptionPrice(opSet, opt, newPrice)) {
						System.out.println("Success in changing option price");
					} else {
						System.out.println("Failed to change option price! Could not find option");
					}
				}
				randomWait();
			}
		}*/
		car.updateOptionPrice(opSet, opt, newPrice);
	}
	
	/* FunctNo = 6 */
	public void updateSelectedOption(String opSet, String op) {
		synchronized(car) {
			//System.out.println("Running selectedOp");
			car.setSelectedOption(opSet, op);
			//System.out.println(car.strRepSelectedOps());
		}
	}
	
	public void run() {
		try {
			//System.out.println(car.toString());
			switch(functNo) {
			case 0:
				updateMaker(param[0]);
				break;
			case 1:
				updateModelName(param[0]);
				break;
			case 2:
				updateBasePrice(Double.parseDouble(param[0]));
				break;
			case 3:
				updateOptionSetName(param[0], param[1]);
				break;
			case 4:
				updateOptionName(param[0], param[1], param[2]);
				break;
			case 5:
				updateOptionPrice(param[0], param[1], Double.parseDouble(param[2]));
				break;
			case 6:
				updateSelectedOption(param[0], param[1]);
				break;
			}
		} catch (NullPointerException e) {
			
		} catch (NumberFormatException e) {
			
		}
	}
	
	/* For Testing purpose */
	public void randomWait() {
        try {
            Thread.currentThread().sleep((long)(2000*Math.random()));
        } catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
	}
}