package cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d. M. yyyy");

	public static String dateToString() {
		return dateToString(new Date());
	}

	public static String dateToString(Date date) {
		return DATE_FORMAT.format(date);
	}

	public static Date stringToDate(String date) throws ParseException {
		if (date == null) {
			return new Date();
		}
		date = date.replace(" ", "").replace(".", ". ");
		Date dateConverted = DATE_FORMAT.parse(date);

		return dateConverted;
	}
}
