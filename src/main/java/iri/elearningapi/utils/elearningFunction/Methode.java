package iri.elearningapi.utils.elearningFunction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methode {
	
	public static String upperCaseFirst(String val) {
		if (val!=null && !val.isEmpty()) {
			char[] arr = val.toCharArray();
			arr[0] = Character.toUpperCase(arr[0]);
			return new String(arr).trim();
		}else {
			return val;
		}
	}
	
	public static boolean isValidEmail(String email) {
		if (email==null || email.isBlank()) {
			return false;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
