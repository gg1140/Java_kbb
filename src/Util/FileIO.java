package Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.Automobile;

public class FileIO {
	public FileIO() {}
	
	public Automobile buildAutoObject (String fileName) throws AutoException{
		AutoParser parser = new AutoParser();
		try {
			return parser.buildAutoObject(fileName);
		} catch (AutoException e) {
			throw e;
		}
	}
	
	public void serializeObject(Object obj, String fileName) throws IOException {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(obj);
			out.close();
		} catch (IOException e) {
			System.out.print("Error:" + e);
		}
	}
	
	public Object deserializeObject(String fileName) throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			Object output = in.readObject();
			in.close();
			return output;	
		} catch (IOException e) {
			System.err.print("Error:" + e);
		} catch (ClassNotFoundException e) {
			System.err.print("Error:" + e);
		}
		return null;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param errorno
	 * @return
	 */
	protected String findFromExceptionFile(String fileName, int errorno) {
		String output = null;
		try {
			FileReader fIn = new FileReader(fileName);
			BufferedReader fBuff = new BufferedReader(fIn);
			String line;
			while (true) {
				line = fBuff.readLine();
				if (line != null) {
					if (line.startsWith(Integer.toString(errorno))) {
						output = line;
					}
				}
				else {
					break;
				}
			}
			fBuff.close();
		} catch (FileNotFoundException e) {
			System.err.print("Error:" + e);
		} catch (IOException e) {
			System.err.print("Error:" + e);
		}
		return output;
	}
	
	public void writeToEndOfFile(String fileName, String content) {
		try {
			FileWriter file = new FileWriter(fileName, true);
			file.write(content);
			file.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
