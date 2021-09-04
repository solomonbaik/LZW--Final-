import java.io.FileNotFoundException;
import java.io.IOException;

public class TesterLZW {
	public static void main(String [] args) throws IOException 
	{
		LZW john = new LZW ("lzw-file2.txt"); 
	   System.out.print(john.compress()); 
	}
}
