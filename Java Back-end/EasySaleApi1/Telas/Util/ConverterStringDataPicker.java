package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConverterStringDataPicker {
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}
}
