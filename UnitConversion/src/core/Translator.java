package core;

import java.util.ArrayList;
import java.util.List;

import textline.BaseSentence;
import textline.Definition;
import textline.Question;

public class Translator {
	private boolean valid_flag = false;
	private List<Question> questions = new ArrayList<Question>();
	private List<Definition> romans = new ArrayList<Definition>();
	private List<Definition> symbols = new ArrayList<Definition>();
	
	public Translator(List<String> lines) {
		this.handler(lines);
	}
	
	public void error(){
		this.valid_flag = false;
		System.out.println("I have no idea what you are talking about");
	}
	
	public void handler(List<String> lines){
		for (String line : lines) {
			BaseSentence bs = new BaseSentence(line);
			if (bs.getType() == Constants.SENTENCE_TYPE_DEFINITION){
				Definition def = new Definition(line);
				if (def.isFlag()){
					if (def.getSubType() == Constants.DEFINITION_TYPE_ROMAN){
//						System.out.println(def.getRoman_symbol());
						romans.add(def);
					}else if (def.getSubType() == Constants.DEFINITION_TYPE_METAL){
//						System.out.println(def.getSymbol()+", " + def.getMetal() + ", " + def.getValue());
						symbols.add(def);
					}
//					System.out.println(def.getNumbers());
				}else{
					this.error();
					return;
				}
			}else if (bs.getType() == Constants.SENTENCE_TYPE_QUESTION){
				Question que = new Question(line);
				if (que.isFlag()){
					//System.out.println("numbers: "+ que.getNumbers() + ", type: " + que.getSubType());
					questions.add(que);
				}else{
					this.error();
					return;
				}
			}else{
				this.error();
				return;
			}
		}
		
		
		// 解析符号数值
		
		// 解析金属价值
	}
}
