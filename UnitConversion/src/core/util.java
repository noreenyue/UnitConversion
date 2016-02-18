package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 * @author noreen
 *
 */
public class util {
	public static List<String> readFile(String filename){
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	lines.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
