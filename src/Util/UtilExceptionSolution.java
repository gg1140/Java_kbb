package Util;

import java.util.Scanner;

public class UtilExceptionSolution implements Fixable{
	private Scanner sc = new Scanner(System.in);
	private AutoException e;
	
	public UtilExceptionSolution(AutoException e) {
		this.e = e;
	}
	
	public Object getSolution(int errorNo) {
		Object output = null;
		switch (errorNo)  {
		case 200:
			output = fixFileNotFoundException();
			break;
		case 201:
			fixImproperFileFormatException();
			break;
		case 202:
			fixFileTypeNotSupportedException();
			break;
		case 203:
			output = fixPriceFormatException();
			break;
		case 204:
			fixDuplicateItemException();
			break;
		case 205:
			fixItemNotFoundException();
			break;
		default:
			break;
		}
		return output;
	}
	
	/**
	 * 200
	 */
	private String fixFileNotFoundException() {	
		String input;
		
		System.out.println(e.getErrorName() + ": " + e.getExtraNote());
		System.out.println("Please re-enter a valid file name and file extension.");
		input = sc.next();
		return input;
	}
	
	/**
	 * 201
	 */
	private void fixImproperFileFormatException() {
		String note = e.getExtraNote();
		
		System.out.println(e.getErrorName() + ": " + note);
		if (note.contains(".auto")) {
			System.out.println("Please read \'AutoFile_Info.txt\' and follow the specification to edit your .auto file.");
		}
	}
	
	/**
	 * 202
	 */
	private void fixFileTypeNotSupportedException() {
		System.out.println(e.getErrorName() + ": " + e.getExtraNote());
	}
	
	/**
	 * 203 Prompt user for entering a number to replace the incorrect input
	 * @return Double ~ price
	 */
	private String fixPriceFormatException() {
		String input;
		AutoRegex strUtil = new AutoRegex();

		System.err.println(e.getErrorName() + ": " + e.getExtraNote());
		while (true) {
			System.out.println("Please re-enter a NUMBER for the price.");
			input = sc.next();
			if (strUtil.isNumber(input)) {
				return input;
			} else {
				System.out.println("Incorrect input, Please try again!");
			}
		}
	}
	
	/**
	 * 204
	 */
	private void fixDuplicateItemException() {
		System.out.println(e.getErrorName() + ": " + e.getExtraNote());
		System.out.println("The extra item will be ignored.");
	}
	
	/**
	 * 205
	 */
	private void fixItemNotFoundException() {
		System.out.println(e.getErrorName() + ": " + e.getExtraNote());
		System.out.println("Current method will be aborted.");
	}
}
