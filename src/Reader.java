
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Load the file located at the filename specified. If the filename is empty,
 * return/throw an error. If the file does not exist, return/throw an error.
 * 
 * Ignore empty lines and those that begin with #.
 * 
 * Return the values contained in the file.
 * 
 * Log errors that occur while reading to the error file, and log the number of
 * elements loaded to the debug/info log file.
 * 
 */
public class Reader {

	private static Logger log = Logger.getLogger(Reader.class.getName());

	public List<String> loadTemplates(String fileName) throws FileNotFoundException {
		if (fileName == null || fileName.length() < 1) {
			throw new IllegalArgumentException("Filename may not be null or empty");
		}
		File file = new File(fileName);
		if (!file.exists()) {
			throw new FileNotFoundException(file.getAbsolutePath());
		}
		List<String> data = new ArrayList<String>();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(file));
			while (in.ready()) {
				String line = in.readLine();
				if (!line.startsWith("#") && line.length() > 0) {
					data.add(line);
				}
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
		log.log(Level.INFO, data.size() + " elements loaded.");
		return data;
	}
}
