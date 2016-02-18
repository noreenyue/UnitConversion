package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import textline.BaseSentence;
import textline.Definition;
import textline.Question;

/**
 * 文件处理
 * @author noreen
 *
 */
public class TextManager {
	private boolean valid_flag = true;
	private List<String> answers = new ArrayList<String>();
	private Map<String, Character> symbolValues = new HashMap<String, Character>(); 
	private Map<String, Float> mentalValues = new HashMap<String, Float>(); 
	
	public TextManager(List<String> lines) {
		this.handler(lines);
	}
	
	public void error(){
		this.valid_flag = false;
	}
	
	public void handler(List<String> lines){
		List<Definition> symbols = new ArrayList<Definition>();
		List<Definition> mentals = new ArrayList<Definition>();
		List<Question> questions = new ArrayList<Question>();
		this.translate(lines, symbols, mentals, questions);
		this.equalCalc(symbols, mentals);
		this.answer(questions);
	}
	
	/**
	 * 解析句子
	 * @param lines 文本内容
	 * @param symbols 文字符号
	 * @param mentals 金属
	 * @param questions 问题列表
	 */
	private void translate(List<String> lines, List<Definition> symbols, List<Definition> mentals, List<Question> questions) {
		for (String line : lines) {
			BaseSentence bs = new BaseSentence(line);
			if (bs.getType() == Constants.SENTENCE_TYPE_DEFINITION){
				Definition def = new Definition(line);
				if (def.isFlag()){
					if (def.getSubType() == Constants.DEFINITION_TYPE_ROMAN){
						symbols.add(def);
					}else if (def.getSubType() == Constants.DEFINITION_TYPE_METAL){
						mentals.add(def);
					}
				}else{
					this.error();
				}
			}else if (bs.getType() == Constants.SENTENCE_TYPE_QUESTION){
				Question que = new Question(line);
				questions.add(que);
			}else{
				this.error();
			}
		}
		
	}

	/**
	 * 等价交换
	 * @param symbols 
	 * @param mentals
	 */
	private void equalCalc(List<Definition> symbols, List<Definition> mentals) {
		// 解析符号数值
		for (Definition symbol : symbols){
			this.symbolValues.put(symbol.getNumbers().get(0), symbol.getRoman_symbol());
		}
		
		// 解析金属价值
		for (Definition mental : mentals){
			String text = "";
			for (String symbol : mental.getNumbers()){
				text += this.symbolValues.get(symbol);
			}
			RomanCalc calc = new RomanCalc(text);
			if (calc.isValid_flag()){
				this.mentalValues.put(mental.getMetal(), Float.valueOf((float) (mental.getValue()*1.0/calc.getValue())));
			}else{
				this.error();
			}
		}
	}

	/**
	 * 问题答案
	 * @param questions
	 */
	private void answer(List<Question> questions){
		for (Question q : questions){
			if (!q.isFlag()){
				this.answers.add( "I have no idea what you are talking about" );
				continue;
			}

			String text = "";
			for (String symbol : q.getNumbers()){
				text += this.symbolValues.get(symbol);
			}
			RomanCalc calc = new RomanCalc(text);
			if (calc.isValid_flag()){
				String answer = "";
				for (String num : q.getNumbers()){
					answer += num + " ";
				}
				if (q.getSubType() == Constants.QUESTION_TYPE_HOWMUCH){
					answer += Constants.EQUAL_SIGN + " " + calc.getValue();
				}else if  (q.getSubType() == Constants.QUESTION_TYPE_HOWMANY){
					int val = (int) (this.mentalValues.get(q.getMental()) * calc.getValue());
					answer += q.getMental() + " " + Constants.EQUAL_SIGN + " " + val + " " + q.getSymbol();
				}
				this.answers.add(answer);
			}else{
				this.error();
				this.answers.add( "I have no idea what you are talking about" );
			}

		}
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public boolean isValid_flag() {
		return valid_flag;
	}
}
