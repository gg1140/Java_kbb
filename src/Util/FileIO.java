package Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.Automobile;

public class FileIO {
	public FileIO() {}
	
	public Automobile buildAutoObject (String fileName) {
		AutoParser parser = new AutoParser();
		return parser.buildAutoObject(fileName);
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
			return in.readObject();	
		} catch (IOException e) {
			System.err.print("Error:" + e);
		} catch (ClassNotFoundException e) {
			System.err.print("Error:" + e);
		}
		return null;
	}
}
