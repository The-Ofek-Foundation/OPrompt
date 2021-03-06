import java.util.Scanner;

import java.io.File;
import java.io.IOException;

public class OPrompt {

	private OPrompt() {} // Prevents initialization of class

	public static String getString(String ask) {
		System.out.printf("%s -> ", ask);
		return new Scanner(System.in).nextLine();
	}

	public static int getInt(String ask, int min, int max) {
		ask = String.format("%s (%d - %d)", ask, min, max);
		int num = -1;
		do {
			try {
				num = Integer.parseInt(getString(ask));
			}	catch (NumberFormatException e) {}
		}	while (num < min || num > max);
		return num;
	}

	public static File getFile(String ask, boolean exists) {
		File file;
		do {
			file = new File(getString(ask));
			if (!exists && !file.exists())
				try {
					file.createNewFile();
					if (file.exists())
						return file;
				} catch (IOException e) {}
		} while (file.exists() && file.isDirectory() || exists && !file.exists());
		return file;
	}

	public static File getDirectory(String ask, boolean exists) {
		File file;
		do {
			file = new File(getString(ask));
			if (!exists && !file.exists())
				if (file.mkdir())
					return file;
		} while (file.exists() && !file.isDirectory() || exists && !file.exists());
		return file;
	}
}