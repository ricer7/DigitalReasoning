package digitalreasoning.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Driver {

	public static void main(String[] args) {
		
		File[] fileArray = null;
		try {
			fileArray = ParserUtil.getDataFiles();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ExecutorService executor = Executors.newFixedThreadPool(fileArray.length);
	    for (int i = 0; i < fileArray.length; i++) {
	        Runnable worker = new ParsingThread(fileArray[i]);
	        executor.execute(worker);
	     }
	    executor.shutdown();
	    try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	      try {
	    	  ParserUtil.dataWriterXML(ParserUtil.getAggregatedHashMap(),ParserUtil.getNERHashMap());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
