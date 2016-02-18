package test;

import java.util.List;

import org.junit.Test;

import textline.BaseSentence;
import textline.Definition;
import textline.Question;
import core.Constants;
import core.RomanCalc;
import core.TextManager;
import core.util;

/**
 * 测试类
 * @author noreen
 */
public class ConversionTest {

	@Test
	public void test_romanValue() {
		String[] texts = new String[]{"MCMLXXX", "MCMLXXX ", "MCMXXC", "XLII", "DDD"};
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
	public void test_sentenceParse() {
		String[] strs = new String[]{
						"glob is I", "glob prok Gold is 57800 Credits", "how many Credits is glob prok Silver ?", 
						"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"};
		for (String s : strs) {
			BaseSentence bs = new BaseSentence(s);
			if (bs.getType() == Constants.SENTENCE_TYPE_DEFINITION){
				Definition def = new Definition(s);
				if (def.isFlag()){
					if (def.getSubType() == Constants.DEFINITION_TYPE_ROMAN){
						System.out.println(def.getRoman_symbol());
					}else if (def.getSubType() == Constants.DEFINITION_TYPE_METAL){
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
	
	@Test
	public void test_Translator(){
		List<String> lines = util.readFile("./src/test/content.txt"); 
		TextManager manager = new TextManager(lines);
		System.out.println("Test Output:");
		for (String line : manager.getAnswers()){
			System.out.println(line);
		}
		
		if (manager.isValid_flag()){
//			System.out.println("文本有误");
		}
	}
	
}
