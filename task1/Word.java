package digitalreasoning.task1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "text")
/**
 * Class containing extracted word in XML format.
 * 
 * @author ricer
 */
public class Word {
	String text;
	/**
	 * Returns extracted word
	 * 
	 * @return String
	 */
	public String getWord() {
		return text;
	}
	
	@XmlElement
	/**
	 * Sets extracted word
	 * 
	 * 
	 */
	public void setWord(String text) {
		this.text = text;
	}

}
