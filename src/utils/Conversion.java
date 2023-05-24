package utils;

public class Conversion {
    
    public static String[] stringToArray(String string) {	// string format: "[value1, value2, value3]" 
	String trim = string.replaceAll("\\[|\\]", "");
	
	String[] array = trim.split(", ");
	
	for (int i = 0; i < array.length; i++) {
	    array[i] = array[i].trim();
	}
	
	return array;
    }
}
