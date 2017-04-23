package Util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import Model.Automobile;

public class AutoParser {
	private final String[] KEYTERM = {"Automobile", "OptionSet Collection", 
										"Collection Size", "Model", "Base Price"};
	private final String SUPPORTED_FILE_EXT = ".auto";
	private String inputFile;
	private AutoRegex strEditor;
	private Map<String, String> lookUpTB;
	private Hashtable<String, String[]> opSetTB;

	public AutoParser() {
		this.inputFile = null;
		this.strEditor = new AutoRegex();
		this.lookUpTB = new Hashtable<String, String>();
		this.opSetTB = new Hashtable<String, String[]>();
	}
	
	protected void fillLookUpTB() {
		try {
			FileReader fIn = new FileReader(inputFile);
			BufferedReader fBuff = new BufferedReader(fIn);
			
			/* Reading tools */
			String line;
			String[] operands; //[0] = [1]
		
			/* Checks */
			boolean keepReading = true;
			boolean s2_readCompulsoryFields = false; //boolean s1_readFileHeader = true;
			boolean s3_readOps = false;
			
			
			while (keepReading) {
				line = fBuff.readLine();
				if (line == null)
					break;
				
				operands = strEditor.getAssignmentOperands(line);
				if (s3_readOps) {
					/* Add all options to each optionSet in opSetTB */
					addOptToOptSet(operands[0], operands[1]);
				} else if (s2_readCompulsoryFields) {
					/* Add all values to each compulsory fields in lookUpTB */
					if (strEditor.isCompulsoryLine(line)) {
						lookUpTB.replace(operands[0], operands[1]);
						if (allCompulsoryFieldsPresent()) { 
							/* Put all optionSetName in opSetTB and move to stage 3*/
							fillInOpSetTB();
							s3_readOps = true;
						}
					} else {
						System.err.println("Input file has incorrection format!");
						keepReading = false;
					}
				} else { /* s1_readFileHeader */
					 keepReading = checkFileHeader(operands[0], operands[1]);
					 s2_readCompulsoryFields = keepReading;
				}
			}
			fBuff.close();
		} catch(FileNotFoundException e) {

		} catch(IOException e) {

		}
	}
	
	public Automobile buildAutoObject(String fileName) {
		Automobile car = null;
		if (setInputFile(fileName)) {
			fillLookUpTB();
			try {
				car = new Automobile(
						lookUpTB.get(KEYTERM[3]), 
						Double.valueOf(lookUpTB.get(KEYTERM[4])),
						Integer.parseInt(lookUpTB.get(KEYTERM[2])));
				
				String optSetName;
				Iterator<String> itr = opSetTB.keySet().iterator();
				while (itr.hasNext()) {
					optSetName = itr.next();
					String[] optSet = opSetTB.get(optSetName);
					
					car.addOptionSet(optSetName, optSet.length);
					for (String element : optSet) {
						String[] opt = strEditor.splitNTrimCommaSepStr(element);
						car.addOption(optSetName, opt[0], Double.valueOf(opt[1]));
					}
				}
			}
			catch(NumberFormatException e) {
				
			}
		}
		return car;
	}
	
	/* Helper Methods */
	private boolean setInputFile(String fileName) { //Make sure file type used by the parser is valid
		boolean flag = validFileType(fileName);
		if (flag) {
			this.inputFile = fileName;
		}
		else {
			System.err.println(this.getClass().getName() + " InputFile not supported");
		}
		return flag;
	}
	
	public boolean validFileType(String fileName) {
		if (fileName.endsWith(SUPPORTED_FILE_EXT))
			return true;
		return false;
	}
	
	private boolean checkFileHeader(String leftOperands, String rightOperands) {
		if (leftOperands.equalsIgnoreCase(KEYTERM[0])) { //KEYTERM[0] = "Automobile"
			String[] listOfItem = strEditor.getAllSetElement(rightOperands);
			for (int i = 0; i <listOfItem.length; i++) 
				lookUpTB.put(listOfItem[i], ""); //Put all Compulsory Field's key into lookUpTB
		}
		
		boolean flag = allCompulsoryFieldsKeyPresent();
		if(!flag) 
			System.err.println("Some compulsory fields are missing from file!\n" /*"Make sure file contain " + KEYTERM.toString()*/);
		return flag;
	}
	
	private void fillInOpSetTB() {
		String[] listOfKeys = strEditor.getAllSetElement(lookUpTB.get(KEYTERM[1])); //KEYTERM[1] = "OptionSet Collection"
		String[] keyNsize;
		
		for (int i = 0; i < listOfKeys.length; i++) {
			keyNsize = strEditor.splitNTrimCommaSepStr(listOfKeys[i]);
			opSetTB.put(keyNsize[0], new String[Integer.valueOf(keyNsize[1])]); //Fill in opSetTB
		}
	}
	
	private void addOptToOptSet(String lhs, String rhs) {
		String[] opSet = opSetTB.get(lhs);
		String[] options;

		if (opSet != null) {
			options = strEditor.getAllSetElement(rhs);
			int j = 0;
			for (int i = 0; i < opSet.length; i++) {
				if (opSet[i] == null) {
					if (j < options.length) {
						opSet[i] = options[j];
						j++;
					}
				}
			}
		} else {
			System.err.println(lhs + " is not listed in " + KEYTERM[1]); //KEYTERM[1] = "OptionSet Collection"
		}
	}
	
	protected boolean allCompulsoryFieldsKeyPresent() {
		boolean flag = true;
		for (int i = 1; i < KEYTERM.length; i++) {
			if (!lookUpTB.containsKey(KEYTERM[i])) {
				flag = false;
			}
		}
		return flag;
	}
	
	protected boolean allCompulsoryFieldsPresent() {
		boolean flag = true;
		for (int i = 1; i < KEYTERM.length; i++) {
			if (lookUpTB.get(KEYTERM[i]) == "") {
				flag = false;
			}
		}
		return flag;
	}
	
	/*public void test() {
		setInputFile("AutobileData.auto");
		filllookUpTB();
		
		System.out.println("lookUpTB");
		System.out.println(lookUpTB);
		System.out.println("opSetTB");
		Set<String> keys = opSetTB.keySet();
		Iterator<String> itr = keys.iterator();
		String str;
		while(itr.hasNext()) {
			str = itr.next();
			System.out.print("Key: " + str + "\n\t Value: ");
			String[] display = opSetTB.get(str);
			for(String ele : display) {
				System.out.print(ele + " ; ");
			}
			System.out.print("\n");
		}
		
		//Automobile car = buildAutoObject("AutobileData.auto");
		//System.out.println(car.toString());
	}*/
	
	/*
	 * Doesnt support empty line yet
	 */
	/*
	FileInputStream in = new FileInputStream(inputFile); 
	props.load(in);
	*/
}
