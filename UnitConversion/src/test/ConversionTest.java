package test;

import org.junit.Test;

import textline.BaseSentence;
import textline.Definition;
import textline.Question;

import core.Constants;
import core.RomanCalc;

public class ConversionTest {

	@Test
	public void RomanValuetest() {
		String[] texts = new String[]{"MCMLXXX", "MCMLXXX ", "MCMXXC"};
		for (int i=0; i<texts.length; i++){
			RomanCalc calc = new RomanCalc(texts[i]);
			System.out.println("\n" + texts[i] + ": " + calc.getNumbers());
			if(!calc.isValid_flag()){
				System.out.println(calc.getError_msg());
			}else{
				System.out.println(calc.getValue());
			}
		}
	}


	@Test
	public void SentenceParsetest() {
		String[] strs = new String[]{
						"glob is I", "glob prok Gold is 57800 Credits", "how many Credits is glob prok Silver ?", 
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"};
		for (int i=0; i<strs.length; i++) {
			String s = strs[i];
			BaseSentence bs = new BaseSentence(s);
			if (bs.getType() == Constants.SENTENCE_TYPE_DEFINITION){
				Definition def = new Definition(s);
				if (def.isFlag()){
					if (def.getSubType() == Constants.DEFINITION_TYPE_ROMAN){
						System.out.println(def.getRoman_symbol());
					}else if (def.getSubType() == Constants.DEFINITION_TYPE_SYMBOL){
						System.out.println(def.getSymbol()+", " + def.getMetal() + ", " + def.getValue());
					}
					System.out.println(def.getNumbers());
				}else{
					System.out.println(" parse error");
				}
			}else if (bs.getType() == Constants.SENTENCE_TYPE_QUESTION){
				Question que = new Question(s);
				if (que.isFlag()){
					System.out.println("numbers: "+ que.getNumbers() + ", type: " + que.getSubType());
				}else{
					System.out.println(" parse error");
				}
			}else{
				System.out.println("sentence unkown type ");
			}
		}
	}
		
	
}
