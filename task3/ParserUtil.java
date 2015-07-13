package digitalreasoning.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * ParserUtil contains methods useful for parsing and otherwise manipulating text.
 * 
 * @author ricer
 *
 */
public class ParserUtil {
	
			private static Properties prop = null;
			private static ConcurrentHashMap<String,String> aggHashMap = null;
			private static ConcurrentHashMap<String,String> NERHashMap = null;
			/**
			 * Loads and returns configuration properties
			 * 
			 * @return Properties
			 * @throws IOException
			 */
			static Properties getProperties() throws IOException {
				if(prop == null) {
					prop = new Properties();
					prop.load(ParserUtil.class.getClassLoader().getResourceAsStream("config.properties"));
				}
				return prop;				
			}
			/**
			 * Extracts words from target and inserts extracted words into HashMap
			 * 
			 * @param target
			 * @param wordIterator
			 * @param hashmap
			 */
		   
			static void extractWordsAndInsertIntoHashMap(String target, BreakIterator wordIterator,
				   Map<String,String> hashmap) {

			      wordIterator.setText(target);
			      int start = wordIterator.first();
			      int end = wordIterator.next();

			      while (end != BreakIterator.DONE) {
			         String word = target.substring(start,end);
			         if (Character.isLetterOrDigit(word.charAt(0))) {
				         if(!hashmap.containsKey(word)) {
				        	 hashmap.put(word, word);
				         }
			         }
			         start = end;
			         end = wordIterator.next();
			      }
			   }
		   /**
		    * Retrieves words from HashMap and inserts words into XML document
		    * 
		    * @param hashmap
		    * @throws IOException
		    */
			static void dataWriterXML(Map<String,String> texthashmap,Map<String,String> nerhashmap) throws IOException {
				 try {
					 	Words words = new Words();
					 	File file = new File(getProperties().getProperty("outputFile"));
						JAXBContext jaxbContext = JAXBContext.newInstance(Words.class);
						Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
						jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				        for (Object key : texthashmap.keySet() ) {

				        	String value = (String) texthashmap.get( key );
				        	Word word = new Word();
				        	word.setWord(value);
				        	if(nerhashmap.containsValue(value))
				        		word.setNerInd();
				        	words.addWord(word);
				        }	
					    jaxbMarshaller.marshal(words, file);
					    jaxbMarshaller.marshal(words, System.out);
				 
					  } catch (JAXBException e) {
						e.printStackTrace();
					  }
			}
			/**
			 * Extracts words from sentences in input data and inserts words into Hashmap
			 * 
			 * @throws FileNotFoundException
			 */

		   static void parseIntoWords() throws FileNotFoundException {

		      Locale currentLocale = new Locale ("en","US");		      
		      Scanner textscnr = null;		
		      Scanner nerscnr = null;
		      try {	
				File text = new File(getProperties().getProperty("inputFile"));		 
				BreakIterator wordIterator = 
					   BreakIterator.getWordInstance(currentLocale);
				      
				textscnr = new Scanner(text);
				Map<String,String> texthashmap = new HashMap<String, String>();
				while(textscnr.hasNextLine()){
			           String line = textscnr.nextLine();
			           extractWordsAndInsertIntoHashMap(line, wordIterator,texthashmap);
				}
				File ner = new File(getProperties().getProperty("nerFile"));				
				nerscnr = new Scanner(ner);
				Map<String,String> nerhashmap = new HashMap<String, String>();
				while(nerscnr.hasNextLine()){
			           String line = nerscnr.nextLine();
			           extractWordsAndInsertIntoHashMap(line, wordIterator,nerhashmap);
				}
				dataWriterXML(texthashmap,nerhashmap);
		      } catch (IOException ex) {
				ex.printStackTrace();
		      } finally {
				textscnr.close();
		      }		        
		   }
		   
		   static void parseIntoWords(File text) throws FileNotFoundException {

			      Locale currentLocale = new Locale ("en","US");		      
			      Scanner textscnr = null;		
			      Scanner nerscnr = null;
			      try {			 
					BreakIterator wordIterator = 
						   BreakIterator.getWordInstance(currentLocale);
					      
					textscnr = new Scanner(text);
					Map<String,String> texthashmap = getAggregatedHashMap();
					while(textscnr.hasNextLine()){
				           String line = textscnr.nextLine();
				           extractWordsAndInsertIntoHashMap(line, wordIterator,texthashmap);
					}
					File ner = new File(getProperties().getProperty("nerFile"));				
					nerscnr = new Scanner(ner);
					Map<String,String> nerhashmap = getNERHashMap();
					while(nerscnr.hasNextLine()){
				           String line = nerscnr.nextLine();
				           extractWordsAndInsertIntoHashMap(line, wordIterator,nerhashmap);
					}
			      } catch (IOException ex) {
					ex.printStackTrace();
			      } finally {
					textscnr.close();
			      }		        
		   }
		   
		   static File[] getDataFiles() throws IOException {
			   File folder = new File(getProperties().getProperty("inputFilesDir"));
			   File[] listOfFiles = folder.listFiles();
			   return listOfFiles;
		   }
		   
		   static ConcurrentHashMap<String,String> getAggregatedHashMap() {
			   if(aggHashMap == null)
				   aggHashMap = new ConcurrentHashMap<String,String>();
			   return aggHashMap;
		   }
		   
		   static ConcurrentHashMap<String,String> getNERHashMap() {
			   if(NERHashMap == null)
				   NERHashMap = new ConcurrentHashMap<String,String>();
			   return NERHashMap;
		   }
}
