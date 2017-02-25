package test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;


public class Test {

	public static void main(String[] args) throws IOException, URISyntaxException {
		
		//System.out.println("[[#Java 0|1989]]".matches("^[!#:\\-\\|a-z A-Z0-9\\]\\[]*"));
		String[] d = "#:-".split("|");
		System.out.println(Arrays.toString(d));
		
	}
}
