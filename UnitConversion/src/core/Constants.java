package core;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	public static final Map<Character, Integer> ROMAN_NUMBERALS = new HashMap<Character, Integer>(){
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };
        
   public static final String EQUAL_SIGN = "is";
}

