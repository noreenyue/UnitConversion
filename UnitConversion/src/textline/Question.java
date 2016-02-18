package textline;

import java.util.ArrayList;
import java.util.List;

import core.Constants;

public class Question extends BaseSentence {
	private String symbol = "";		 
	private boolean flag = false;
	private List<String> numbers = new ArrayList<String>();
	private int sub_type = -1;

	public Question(String sentence) {
		super(sentence);
		this.flag = this.parse();
	}
	
	@Override
	public boolean parse() {
		if(!this.words[0].toLowerCase().equals("how")){
			return false;
		}

		boolean flag = true;
		if(this.words[1].toLowerCase().equals("many") && this.words[3].toLowerCase().equals("is") && this.words.length > 4){	// how many Credits is glob prok Silver ?
			this.symbol = this.words[2];
			for (int i=5; i<this.words.length-1; i++){
				numbers.add(words[i]);
			}
			this.sub_type = Constants.QUESTION_TYPE_HOWMANY;
		}else if(this.words[1].toLowerCase().equals("much") && this.words[2].toLowerCase().equals("is")  && this.words.length > 3){		//how much is pish tegj glob glob ?
			for (int i=4; i<this.words.length-1; i++){
				numbers.add(words[i]);
			}
			this.sub_type = Constants.QUESTION_TYPE_HOWMUCH;
		}else{
			flag = false;
		}
		return flag;
	}

	public String getSymbol() {
		return symbol;
	}


	public int getSubType() {
		return sub_type;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public boolean isFlag() {
		return flag;
	}
}
