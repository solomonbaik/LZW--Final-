


import java.util.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
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
	public int [] compressed = new int [80000]; 
	public List<Integer> derp = new ArrayList<Integer>(80000);

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
		//int [] compressed = new int [1000];//
		int k = 0; //index in int[] compressed array.
		int i = 0; //index in input text string.
		int j = 2; //character length of the current + next string.
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
		//System.out.println("compressed: "+ Arrays.toString(compressed));
		//System.out.println();
		
		byte [] byteOutput = new byte [2*k]; 
		for (int x = 0; x < 2*k; x=x+2)
		{
			byteOutput[x+1] = (byte) compressed[x];
			byteOutput[x] = (byte)(compressed[x]/256);

		}
		String bin = "";
		//Print compressed/reformatted binary
			//for(byte b: byteOutput) {//
			for(int n: compressed) { 
			//String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');//
			bin = Integer.toBinaryString(0x10000 | n).substring(1);
			//System.out.println("bint: " + bin);
			//System.out.println(s1);
			}
			
		Path file = Paths.get("output.byte");
		FileWriter writer = new FileWriter ("output.byte");
		writer.write(bin);
		//Files.write(file, bin);//
		writer.close();
		return output; 
	}
	
	//Read file to  by reading byte file and converting to int array
	public  ArrayList<Integer> readFile(String filename) throws IOException {
		String decompressedText = "";
		String uncompressedString;
		Path fileName = Path.of(filename);
		byte[] readByteArray = Files.readAllBytes(fileName);
		//Print compressed/reformatted binary
		System.out.println("Read in ByteArray");
		for(byte b: readByteArray) {
		String s1 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
		System.out.println(s1);
		}
		
		System.out.println("Read byteArray: " + Arrays.toString(readByteArray));
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for(int i =0; i< readByteArray.length; i+=2) {
			int val = ((readByteArray[i] & 0xff) << 8) | (readByteArray[i+1] & 0xff);
			
			intList.add(val);
			//int val = ((data[2] & 0xff) << 8) | (data[3] & 0xff);
		}
		
		return intList;


	}

	

	public String byteToBinary(byte[] bytes) {

		StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
		for( int i = 0; i < Byte.SIZE * bytes.length; i+=2 )
			sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		return sb.toString();
	}
	
	public String decompressFromByteFile(String fileName) throws IOException {
		ArrayList<Integer> intList = readFile(fileName);
		System.out.println("IntList:" + intList.toString());
		String decompressed = decompressFromInput(intList);
		System.out.println("decompressed: ");
		return decompressed;
		
	}
	//decompresses from Array of ints (ex: [97,98,257])
	public String decompressFromInput(List<Integer> compressedInput) {

		int dictionarySize = 256;
		Map<Integer,String> dictionary = buildDictionaryForDecompression(dictionarySize);

		String word = "" + (char)(int)compressedInput.get(0);

		StringBuffer result = new StringBuffer(word);

		for (int i = 1; i < compressedInput.size()-1; i++) {
			String entry;
			int digit = compressedInput.get(i);

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
