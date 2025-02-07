package java20.developia.springJava;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

@Component
public class MyFileReader {

	public String readFromFile(String fileName) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader("files/" + fileName));
		String line = reader.readLine();
		reader.close();
		return line;
	}
}