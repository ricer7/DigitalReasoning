package digitalreasoning.task3;

import java.io.File;
import java.io.FileNotFoundException;

public class ParsingThread implements Runnable  {
	
	private File file = null;
	
	ParsingThread(File file) {
		this.file = file;
	}
	
	  @Override
	  public void run() {
		  try {
			ParserUtil.parseIntoWords(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	  }

}
