package mg.ratombotsoa.gamecollection.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static final String FORMAT_PATTERN = "MMM/dd/yyyy";
	
	public static Date parseDate(String date) {
		try {
			return DateFormat.getDateInstance(DateFormat.SHORT).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formatDate(Date date) {
		if (date == null) {
			return null;
		}
		
		return new SimpleDateFormat(FORMAT_PATTERN).format(date);
	}
}
