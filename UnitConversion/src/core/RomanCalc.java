package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RomanCalc {
	private List<String> numbers = new ArrayList<String>();
	private int value = 0;
	private String error_msg = "";
	private boolean valid_flag = false;
	
	public RomanCalc(String numberal) {
		this.valid_flag = this.separate(numberal);
		if (this.valid_flag){
			this.calc();
		}
	}

	private int calc() {
		int sum = 0;
		
		return sum;
	}

	private boolean separate(String numberal) {
		numberal = numberal.trim();
		int offset = 0;
		for(int i=0; i<numberal.length(); i++){
			Character currChar = numberal.charAt(i);
			if (!Constants.ROMAN_NUMBERALS.containsKey(currChar)){
				this.error_msg = "Character '" + currChar + "' is not a Roman numberal.";
				return false;
			}
			
			if( i < numberal.length() - 1){
				Character nextChar = numberal.charAt(i+1);
				int nextVal = Constants.ROMAN_NUMBERALS.get(nextChar);
				int currVal = Constants.ROMAN_NUMBERALS.get(currChar);
				System.out.println("next " + nextVal + ", curr " + currVal);
				if(currVal < nextVal){
					if (offset < i){
						this.numbers.add(numberal.substring(offset, i));
						this.numbers.add(numberal.substring(i, i+2));
						offset = i+2;
					}
				}
			}
		}
		
		if (offset != numberal.length()){
			this.numbers.add(numberal.substring(offset));
		}
		System.out.println(this.numbers);
		return true;
	}
	
	
}
