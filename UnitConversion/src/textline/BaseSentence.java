package textline;

import core.Constants;

/**
 * 句子基类
 * @author noreen
 *
 */
public class BaseSentence {
	protected String[] words;
	private int type = -1;
	
	public BaseSentence(String sentence) {
		this.type_(sentence);
	}
	
	public void type_(String line){
		String sentence = line.trim();
		
		this.words = sentence.split(" ");
		if (this.words.length < 3){
			this.type = Constants.SENTENCE_TYPE_UNKOWN;
		}else if(sentence.endsWith("?")){
			this.type = Constants.SENTENCE_TYPE_QUESTION;
		}else{	
			this.type = Constants.SENTENCE_TYPE_DEFINITION;
		}
		
	}
	
	public boolean parse(){
		return true;
	}

	public int getType(){
		return this.type;
	}
	
}
