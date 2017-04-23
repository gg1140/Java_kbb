package Util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class that provide tools to extract information in useful form from heavily formatted String
 * Using regular expression
 * @author Gordonlschan
 */
public class AutoRegex {
	Pattern compulsoryLineRegex = Pattern.compile("\\*([^\\n])+");
	Pattern assignmentRegex = Pattern.compile("\\**([^=\\n\\r]+)=\\s*([^=\\n\\r]+)");
	Pattern parenthesisRegex = Pattern.compile("\\( *([^)]+)(?= *\\))");
	Pattern setNotationRegex = Pattern.compile("\\{ *([^\\{\\}]+)(?= *\\})");
	Matcher m;
	
	public AutoRegex() {}
	
	/**
	 * Determine if a string start with an asterisk symbol
	 * 
	 * @param line
	 * @return true if the string start with an asterisk
	 * @return false if the string does not start with an asterisk
	 */
	public boolean isCompulsoryLine(String line) {
		m = compulsoryLineRegex.matcher(line);
		if(m.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * Remove parenthesis from the first substring that matches the parenthesisRegex
	 * 
	 * @param line
	 * @return String within the parenthesis when a match exist
	 * @return null when no match found
	 */
	public String removeParenthesis(String line) {
		m = parenthesisRegex.matcher(line);
		String output = null;
		if(m.find())
			output  = m.group(1).trim();
		return output;
	}
	
	/**
	 * Remove set notation from the first substring that matches the setNotationRegex
	 * 
	 * @param line
	 * @return String within the set notation when a match exist
	 * @return null when no match found
	 */
	public String removeSetNotation(String line) {
		m = setNotationRegex.matcher(line);
		String output = null;
		if(m.find())
			output = m.group(1).trim();
		return output;
	}
	
	/**
	 * Remove the assignment operator
	 * 
	 * @param line
	 * @return String[] with 2 elements, with the 1st element being left operand and 2nd element being right operand
	 * @return null;
	 */
	public String[] getAssignmentOperands(String line) {
		m = assignmentRegex.matcher(line);
		if(m.find()) {
			String[] output = {m.group(1).trim(), m.group(2).trim()};
			return output;
		}
		return null;
	}
	
	/**
	 *  Remove set notation and parenthesis surrounding all elements in the set
	 *  
	 * @param line
	 * @return String[] ...
	 */
	public String[] getAllSetElement(String line) {
		m = parenthesisRegex.matcher(line);
		ArrayList<String> elements = new ArrayList<String>();
		while(m.find()) {
			elements.add(m.group(1).trim());
		}
		String[] output = new String[elements.size()];
		output = elements.toArray(output);
		return output;
	}
	
	/**
	 * Remove all commas and whitespace in-front or behind each comma symbol
	 * 
	 * @param line
	 * @return String[] of all string in between comma
	 */
	public String[] splitNTrimCommaSepStr(String line) {
		String[] output = line.split(",");
		for(int i = 0; i < output.length; i++) {
			output[i] = output[i].trim();
		}
		return output;
	}
	
	/*
	 * All output will be trimmed
	 */
}

