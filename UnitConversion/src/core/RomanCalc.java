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
		this.calc();
	}
	
	private int getNumValue(String num){
		if (num.length() == 2){
			int num1 = Constants.ROMAN_NUMBERALS.get(num.charAt(0));
			int num2 = Constants.ROMAN_NUMBERALS.get(num.charAt(1));
			if (num1 < num2) {
				return num2-num1;
			}
		}
		
		int sum = 0;
		for (int i=0; i<num.length(); i++){
			sum += Constants.ROMAN_NUMBERALS.get(num.charAt(i));
		}
		return sum;
	}

	private boolean calc() {
		if (! this.valid_flag){
			return false;
		}
		
		int previousVal = 0;
		for (int i=0; i<this.numbers.size(); i++){
			String num = numbers.get(i);
			int value = this.getNumValue(num);
			if (i>0 && value >= previousVal){
				this.error_msg = "Number Value calc error! " + num + ": " + value + ",  prior " + numbers.get(i-1) + ": " + previousVal;
				this.valid_flag = false;
				this.value = -1;
				return this.valid_flag;
			}
			this.value += value;
			previousVal = value;
		}
		
		
		return true;
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
		return true;
	}

	public List<String> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getError_msg() {
		return error_msg;
	}

	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	public boolean isValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(boolean valid_flag) {
		this.valid_flag = valid_flag;
	}
	
}
