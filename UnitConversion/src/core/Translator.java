package core;

import java.util.ArrayList;
import java.util.List;

public class Translator {
	private String error_msg = "";
	private boolean valid_flag = false;
	private List<String> lines = new ArrayList<String>(); 

	public Translator(List<String> lines) {
		this.lines = lines;
	}
	
	public void error(){
		this.error_msg = "I have no idea what you are talking about";
		this.valid_flag = false;
	}
	
}
