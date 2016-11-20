package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Test;

import error.LexicalError;
import framework.ITokenList;
import scanner.Scanner;

public class ImlScanner {

	@Test
	public void test() throws IOException, LexicalError {
		// create a path for file named temp.txt from current folder
		Path filePath = Paths.get("src/iml/Factorial.iml");
		// read a complete file in one go as byte array
		// use this to read small files
		byte[] byteArray = Files.readAllBytes(filePath);
		
		/*String path = "/Example01.iml";
		Path file = Paths.get(path);
		byte [] fileArray;
		fileArray = Files.readAllBytes(file);
		
		System.out.println(fileArray);
		*/
		Scanner scanner = new Scanner();
		String text = new String(byteArray);
		
		ITokenList list = scanner.scan(text);
		System.out.println(list.toString());
		
	}
}
