package textline;

import java.util.ArrayList;
import java.util.List;

import core.Constants;

/**
 * 定义句
 * @author noreen
 *
 */
public class Definition extends BaseSentence { 
	private int sub_type = -1;
	private boolean flag = false;
	private List<String> numbers = new ArrayList<String>(); 
	
	private int value = 0;
	private String symbol = "";		 
	private String metal = "";		 
	
	private char roman_symbol = ' ';

	public Definition(String sentence) {
		super(sentence);
		this.flag = this.parse();
	}
	
	
	@Override
	public boolean parse() {
		boolean flag = true;
		if(this.words.length == 3 && this.words[1].equals("is")){	// prok is V
			this.sub_type = Constants.DEFINITION_TYPE_ROMAN;
			this.roman_symbol = this.words[2].charAt(0);
			this.numbers.add(this.words[0]);

		}else if(this.words.length > 5 ){		// glob glob Silver is 34 Credits
			this.sub_type = Constants.DEFINITION_TYPE_METAL;
			int isIdx = 0;
			for(; isIdx<this.words.length; isIdx++){
				if (this.words[isIdx].equals("is")){
					break;
				}else if (this.words[isIdx+1].equals("is")){
					this.metal = this.words[isIdx];
				}else{
					this.numbers.add(this.words[isIdx]);
				}
			}
			if(!this.words[isIdx+1].matches("^\\d+$")){
				return false;
			}
			this.value = Integer.parseInt(this.words[isIdx+1]);
			this.symbol = this.words[isIdx+2];
		}else{
			flag = false;
		}
		return flag;
	}

	public int getSubType() {
		return sub_type;
	}


	public List<String> getNumbers() {
		return numbers;
	}


	public int getValue() {
		return value;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getMetal() {
		return metal;
	}

	public char getRoman_symbol() {
		return roman_symbol;
	}


	public boolean isFlag() {
		return flag;
	}
	
}
