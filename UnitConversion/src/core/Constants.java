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

   public static final int SENTENCE_TYPE_UNKOWN = -1;
   public static final int SENTENCE_TYPE_DEFINITION = 0;
   public static final int SENTENCE_TYPE_QUESTION = 1;

   public static final int QUESTION_TYPE_HOWMUCH = 0;
   public static final int QUESTION_TYPE_HOWMANY = 1;
   public static final int DEFINITION_TYPE_ROMAN = 0;
   public static final int DEFINITION_TYPE_METAL = 1;
}

