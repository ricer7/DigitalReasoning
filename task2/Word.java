package digitalreasoning.task2;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "word")
/**
 * Class containing extracted word in XML format.
 * 
 * @author ricer
 */
public class Word {
	private String word;
	private String nerInd;
	/**
	 * Returns extracted word
	 * 
	 * @return String
	 */
	public String getWord() {
		return word;
	}
	/**
	 * Retrieves NER indicator
	 * 
	 * @return String
	 */
	public String getNerInd() {
		return nerInd;
	}
	
	@XmlElement
	/**
	 * Sets extracted word
	 * 
	 * 
	 */
	public void setWord(String word) {
		this.word = word;
	}
	
	/**
	 * Sets NER indicator
	 * 
	 * 
	 */
	public void setNerInd() {
		this.nerInd = "true";
	}

}
