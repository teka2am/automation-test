package utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtilities {
	private DateTimeUtilities() {
	}

	public enum DateInfoType {
		MONTH, YEAR, DAY_OF_MONTH
	}

	public static String getCurrentDateAsFormatString(String formatString) {
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateAsFormatString(Date date, String formatString) {
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		return dateFormat.format(date);
	}

	public static String getFistDateOfYear(String formatString) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
		LocalDate now = LocalDate.now();

		return formatter.format(now.withDayOfYear(1));
	}

	public static String getLastDateOfYear(String formatString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
		LocalDate now = LocalDate.now();
		return formatter.format(now.withDayOfYear(365));
	}

	public static Date getDateFromString(String dateToConvert, String formatString) {
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		try {
			return dateFormat.parse(dateToConvert);
		} catch (ParseException ex) {
			return null;
		}
	}

	public static int getPartialInfoFromDate(Date dateToGet, DateInfoType dateInfoType) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToGet);
		switch (dateInfoType) {
		case MONTH:
			return calendar.get(Calendar.MONTH);
		case YEAR:
			return calendar.get(Calendar.YEAR);
		default:
			return calendar.get(Calendar.DAY_OF_MONTH);
		}
	}

	public static boolean checkDateInRange(Date dateToCheck, Date startDate, Date endDate) {
		return dateToCheck.compareTo(startDate) >= 0 && dateToCheck.compareTo(endDate) <= 0;
	}

	public static Date getBeforeOrAfterDate(int beforeOrAfterDay) {
		return new Date(System.currentTimeMillis() + beforeOrAfterDay * 24 * 60 * 60 * 1000);
	}
}
