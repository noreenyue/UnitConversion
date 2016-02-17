package core;


public class RomanCalc_bk {
	private String desc_text = "";
	private String symbol = "";
	private int value = 0;
	private String error_msg = "";
	private boolean valid_flag = false;
	
	public RomanCalc_bk(String text) {
		this.desc_text = text;
		this.calc();
	}
	
	// text check and parse
	public boolean calc(){
		String[] cells = this.desc_text.split(" ");
		if (cells.length != 3){
			this.error_msg = "Sentence format error.";
			return false;
		}
		
		if (!cells[1].equals(Constants.EQUAL_SIGN)){
			this.error_msg = "Sentence doesn't have keyword 'is'.";
			return false;
		}
		
		if (!Constants.ROMAN_NUMBERALS.containsKey(cells[2])){
			this.error_msg = "Value of Roman Bumberals not found.";
			return false;
		}
		
		this.symbol = cells[0];
		this.value = Constants.ROMAN_NUMBERALS.get(cells[2]);
		return true;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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
