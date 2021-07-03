package Util;

import java.text.Normalizer;

public class removeAcento {
	
	public static String removerAcento(String str) {
	    str = Normalizer.normalize(str, Normalizer.Form.NFD);
	    str = str.replaceAll("[^\\p{ASCII}]", "");
	    return str;
	}
}
