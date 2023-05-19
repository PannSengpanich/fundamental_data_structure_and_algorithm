import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

public class CovidData {
	Hashtable<String, Integer> data;

	public CovidData(String filename) {
		data = new Hashtable<String, Integer>();
		try {
			Scanner in = new Scanner(new File("/Users/pannsengpanich/eclipse-workspace/Lab10/src/" + filename)); // using
																													// my
																													// own
																													// file
																													// path
			// Add your code here
			while (in.hasNextLine()) {
				String line = in.nextLine();
				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(",");

				if (lineScanner.hasNext()) {
					String key = lineScanner.next();
					int value = Integer.parseInt(lineScanner.next());
					data.put(key, value);
				}

				lineScanner.close();
			}

			in.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public int find(String d) {
		// Add your code here
		if (data.containsKey(d)) {
			return data.get(d);
		} else {
			return -1;
		}
	}
}
