package digitalreasoning.task1;
import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "digitalreasoning.task1")
public class Words {
	
	/**
	 * Class containing list of extracted words in XML format.
	 * 
	 * @author ricer
	 */
    
	@XmlElementWrapper(name = "WordList")
    @XmlElement(name = "wordinfo")

    private List<Word> words = new ArrayList<Word>();
	/**
	 * Returns list of extracted words
	 * 
	 * @return List<Word>
	 */
    public List<Word> getWords() {
        return words;
    }
	/**
	 * Adds word to list of extracted words
	 * 
	 * @param Word
	 */

    public void addWord(Word word) {
        words.add(word);
    }

}
