package Util;

import java.sql.Timestamp;
import java.util.Calendar;

@SuppressWarnings("serial")
public class AutoException extends Exception{
	private final String eFileName = "ExceptionList.txt";
	private final String eLogFileName = "ExceptionLog.txt";
	
	private Timestamp timeStamp;
	private int errorNo;
	private String errorName;
	private String fixSuggestion;
	private String extraNote;
	
	public AutoException() {
		super();
		initToNonSpecified();
		this.extraNote = "";
	}
	
	public AutoException(int errorNo) {
		super();
		initExceptionFields(errorNo);
		this.extraNote = "";
		log();
	}
	
	public AutoException(int errorNo, String extraNotes) {
		super();
		initExceptionFields(errorNo);
		this.extraNote = extraNotes;
		log();
	}
	
	public int getErrorNo() {
		return errorNo;
	}
	
	public String getErrorName() {
		return errorName;
	}
	
	public String getFixSuggestion() {
		return fixSuggestion;
	}
	
	public void setExtraNote(String extraNotes) {
		this.extraNote = extraNotes;
	}
	
	public String getExtraNote() {
		return extraNote;
	}
	
	/**
	 * Initialize all fields when errorNo is not specified or invalid errorNo was set
	 */
	private void initToNonSpecified() {
		this.errorNo = 0;
		this.errorName = "NonSpecifiedAutoException";
		this.fixSuggestion = "N/A";
		this.timeStamp = new Timestamp(
				Calendar.getInstance().getTime().getTime());
	}
	
	/**
	 * Initiate errorName, errorDescription, fixSuggestion from file according to errorNo
	 * @param errorNo
	 * @return true ~ when errorNo and all fields exists
	 * @return false ~ when errorNo or one field is missing
	 */
	private boolean initExceptionFields(int errorNo) {
		boolean flag = false;
		FileIO fileReader = new FileIO();
		AutoRegex strEditor = new AutoRegex();
		String lineFromFile;
		String[] processedStr;

		lineFromFile = fileReader.findFromExceptionFile(eFileName, errorNo);
		if (lineFromFile != null) {
			processedStr = strEditor.splitNTrimCommaSepStr(lineFromFile);
			if (processedStr.length == 3) {
				this.errorNo = errorNo;
				errorName = processedStr[1];
				fixSuggestion = processedStr[2];
				this.timeStamp = new Timestamp(
						Calendar.getInstance().getTime().getTime());
				flag = true;
			}
			else {
				initToNonSpecified();
			}
		}
		else {
			initToNonSpecified();
		}
		
		return flag;
	}
	
	private void log() {
		FileIO fileEditor = new FileIO();
		String output = timeStamp.toString() + 
				", Error #"  +  errorNo + 
				", ErrorName: " + errorName + 
				", Note:" + extraNote + "\n";
		fileEditor.writeToEndOfFile(eLogFileName, output);
	}
	
	public Object fix() {
		Object output = null;
		Fixable sol;
		if (errorNo < 100) {

		} else if (errorNo < 200) {

		} else if (errorNo < 300) {
			sol = new UtilExceptionSolution(this);
			output = sol.getSolution(errorNo);
		}
		
		return output;	
	}
}
