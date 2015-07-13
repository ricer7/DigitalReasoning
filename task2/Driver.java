package digitalreasoning.task2;

import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
	      try {
	    	  ParserUtil.parseIntoWords();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
