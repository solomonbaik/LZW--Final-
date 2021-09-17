import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class TesterLZW {
	public static void main(String [] args) throws IOException 
	{
		LZW john = new LZW ("smalltest.txt"); 
		//byte[] datas= john.readFileToString("output.byte");
		String compressed = john.compress(); 
		//int[] compressOutput = {97, 97, 98, 257, 256, 98, 261, 0, 0, 0, 0, 0, 0, 0, 0};
		System.out.println(john.decompressFromByteFile("output.byte"));
		//System.out.println("decomp: " + john.decompress(compressOutput));
	 
	   
	}
}
