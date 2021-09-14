
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		int [] compressed = new int [8000000]; 
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

	public byte[] readFileToString(String filename) throws IOException {
		String decompressedText = "";
		String uncompressedString;
		Path fileName = Path.of(filename);
		byte[] data = Files.readAllBytes(fileName);
		//uncompressedString = Files.readString(fileName);

		return data;


	}

	public int[] convertToIntArray(byte[] byteArray) {
		int[] intArray = new int[byteArray.length];
		for(int i = 0; i < byteArray.length; i++) {
			intArray[i] = (int)byteArray[i];
		}
		return intArray;
	}

	public String byteToBinary(byte[] bytes) {

		StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
		for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
			sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		return sb.toString();
	}


	public String decompress(int[] compressedInput) {

		int dictionarySize = 256;
		Map<Integer,String> dictionary = buildDictionaryForDecompression(dictionarySize);

		String word = "" + (char)(int)compressedInput[0];

		StringBuffer result = new StringBuffer(word);

		for (int i = 1; i < compressedInput.length-1; i++) {
			String entry;
			int digit = compressedInput[i];

			if (dictionary.containsKey(digit)) {
				entry =  dictionary.get(digit);
			}
			else if (digit == dictionarySize) {
				entry = word + word.charAt(0);
			} 
			else {
				throw new IllegalArgumentException("Bad compressed digit: " + digit);
			}

			result.append(entry);

			dictionary.put(dictionarySize++, word + entry.charAt(0));

			word = entry;
		}

		return result.toString();
	}

	private Map<Integer,String> buildDictionaryForDecompression(int dictionarySize) {
		Map<Integer,String> dictionary = new HashMap<Integer,String>();
		for (int i = 0; i < dictionarySize; i++) {
			dictionary.put(i, "" + (char)i);
		}
		return dictionary;
	}

}
