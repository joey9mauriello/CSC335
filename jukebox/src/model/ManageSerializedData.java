// Jake Bode
// Joey Mauriello

package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ManageSerializedData {
	
	private final static String accDataFileName = "accountData.ser";
	private final static String playlistFileName = "playlistData.ser";
	
	public static AccountController loadAccountData() {
		AccountController data = null;
		try {
			FileInputStream rawBytes = new FileInputStream(accDataFileName);
    		ObjectInputStream inFile = new ObjectInputStream(rawBytes);
    		data = (AccountController) inFile.readObject();
    		inFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
		return data;
	}
	
	public static void store(AccountController data) {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(accDataFileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(data);
			outFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	public static void store(ArrayList<Song> playlist) {
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(playlistFileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);
			outFile.writeObject(playlist);
			outFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Song> loadPlaylist() {
		ArrayList<Song> data = null;
		try {
			FileInputStream rawBytes = new FileInputStream(playlistFileName);
    		ObjectInputStream inFile = new ObjectInputStream(rawBytes);
    		data = (ArrayList<Song>) inFile.readObject();
    		inFile.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		}
		return data;
	}
	
}
