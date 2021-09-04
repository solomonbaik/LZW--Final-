
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class LZW {
	
	private ArrayList <String>  dictionary = new ArrayList(1030) ; 
	private String txt; 

	// reads a text file into a String and returns that String, throws an error if file cannot be inputed 
	private void generateString (String fileNamer) throws IOException
	{
		Path fileName = Path.of(fileNamer);
		txt = Files.readString(fileName);

	}
	// constructs an LZW object using the generateString method, throws an error if file to be read cannot be inputed
	// initializes an arrayList dictionary of traditional letter pairs using ASCII
	public LZW (String fileName) throws IOException
	{
		generateString(fileName); 
		for (int i = 0; i < 256; i++)
		{
			dictionary.add("" + (char)i); 
		}
	}

	// Converts a String text into an array of integers and writes the compressed information to a byte file, throwing errors if the file cannot be written to
	// Returns the String compressed
	public String compress () throws UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String output = ""; 
		int [] compressed = new int [800000]; 
		int k = 0; 
		int i = 0; 
		int j = 2; 
		while (txt.length() != 0)
		{
			if (txt.length() <= i+j && dictionary.contains(txt.substring (i,txt.length())))
			{
				output += (txt.substring(i, txt.length()));
				int comp =  dictionary.indexOf(txt.substring (i,txt.length())); 
				compressed[k] = comp; 
				k++; 
				txt = ""; 
			}
			else
			{
				String input = txt.substring(i,i+j); 
				if (!dictionary.contains(input))
				{
					dictionary.add(input); 
					output += (txt.substring(i, i+j-1)); 
					int comp = (dictionary.indexOf(txt.substring(i, i+j-1))); 
					compressed[k] = comp; 
					k++;
					txt = txt.substring(j-1,txt.length()); 
					j=2; 
				}
				else
				{
					j++; 
				}
			}

		}
		
		byte [] byteOutput = new byte [2*k]; 
		for (int x = 0; x < 2*k; x=x+2)
		{
			byteOutput[x] = (byte) compressed[x]; 
			byteOutput[x+1] = (byte)(compressed[x]/256) ; 
		}
		Path file = Paths.get("output.byte");
		Files.write(file, byteOutput);
		return output; 
	}
	
}
