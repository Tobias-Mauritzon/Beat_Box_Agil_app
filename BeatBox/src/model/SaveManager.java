package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A class created to manage the save data for the game, it can both save and
 * loads game states It uses static methods so a instance of the class is not
 * required. Added functionality to delete files.
 * 
 * @author Andreas Greppe
 * @version 2020-02-19
 * 
 * @author Joachim Antfolk
 * @version 2020-09-24
 */
public class SaveManager {

	/**
	 * Saves the data to a file with the parameter filename as the files name.
	 * 
	 * @param data     the that is saved
	 * @param fileName the name of file that the data is saved in
	 * @throws IOException when the file can't be saved.
	 */
	public static void saveFile(Serializable data, String fileName) throws IOException {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			outputStream.writeObject(data);
		}
	}

	/**
	 * Reads the data from the selected file and loads it
	 * 
	 * @param fileName the name of file that the data is saved in
	 * @return input stream
	 * @throws ClassNotFoundException when the loaded file is wrong format.
	 * @throws IOException            when file is not found
	 */
	public static Object loadFile(String fileName) throws ClassNotFoundException, IOException {
		try (ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {
			return inputStream.readObject();
		}
	}
	
	/**
	 * Deletes the selected file
	 * 
	 * @param fileName the name of file that the data is saved in
	 * @throws NoSuchFileException thrown if the file "fileName" does not exist
	 * @throws DirectoryNotEmptyException is thrown when the directory being deleted is not empty.
	 * @throws IOException thrown when an i/o error occurs
	 */
	public static void deleteFile(String fileName) throws NoSuchFileException, DirectoryNotEmptyException, IOException{
		Path path = Paths.get(fileName);		
		Files.delete(path);
	}
}