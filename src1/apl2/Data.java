package apl2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Data {
	
	public static String loadTextFileToString(String filename) throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(filename);
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			if (line == null) {
				break;
			}
			sb.append(line).append('\n');
		}
		
		is.close();
		return sb.toString();
	}

	public static void saveStringToTextFile(String filename, String contents) throws FileNotFoundException, IOException {
		OutputStream os = new FileOutputStream(filename);
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);

		bw.write(contents);
		bw.flush();
		os.close();
	}
}