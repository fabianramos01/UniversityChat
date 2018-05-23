package persistence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import network.ConstantList;

public class FileManager {

	public static ArrayList<String> loadWords() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(ConstantList.WORD_FILE));
		return (ArrayList<String>) lines;
	}
}