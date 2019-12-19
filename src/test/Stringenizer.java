package test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Stringenizer {
	public static int riga = 1;
	public static String s = "";
	
	public static void scrivi(int p) throws IOException {
		File file = new File("C:\\Users\\Vincenzo Parrilla\\Desktop\\output"+p+".txt");
		if(!file.exists())
			file.createNewFile();
		PrintWriter pw = new PrintWriter(file);
		pw.println(s);
		pw.close();
	}
}
