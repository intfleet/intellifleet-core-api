package com.intellifleet.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtils {

	public static final String TIMEZONE_ASIA_KOLKATA = "Asia/Kolkata";

	private static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	private static final DateTimeFormatter DEFAULT_TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

	private static final DateTimeFormatter DATE_FORMAT_FOR_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final DateTimeFormatter DATE_TIME_FORMAT_FOR_DB = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/** Convert LocalDate to String with default format */
	public static String dateToString(LocalDate date) {
		return (date == null ? LocalDate.now() : date).format(DEFAULT_DATE_FORMAT);
	}

	/** Convert LocalDate to String with custom format */
	public static String dateToString(LocalDate date, String format) {
		DateTimeFormatter formatter = (format == null) ? DEFAULT_DATE_FORMAT : DateTimeFormatter.ofPattern(format);
		return (date == null ? LocalDate.now() : date).format(formatter);
	}

	/** Convert LocalDate to String for DB format */
	public static String dateToStringForDB(LocalDate date) {
		return (date == null ? LocalDate.now() : date).format(DATE_FORMAT_FOR_DB);
	}

	/** Parse string to LocalDate */
	public static LocalDate parseDate(String strDate) {
		return (strDate == null) ? LocalDate.now() : LocalDate.parse(strDate, DEFAULT_DATE_FORMAT);
	}

	/** Parse string to LocalTime */
	public static LocalTime parseTime(String strDate) {
		return (strDate == null) ? LocalTime.now() : LocalTime.parse(strDate, DEFAULT_TIME_FORMAT);
	}

	/** Parse string to LocalDateTime */
	public static LocalDateTime parseDateTime(String strDate) {
		return (strDate == null) ? LocalDateTime.now() : LocalDateTime.parse(strDate, DEFAULT_DATE_TIME_FORMAT);
	}

	/** Convert LocalDateTime to string */
	public static String dateTimeToString(LocalDateTime dateTime) {
		return (dateTime == null ? LocalDateTime.now() : dateTime).format(DEFAULT_DATE_TIME_FORMAT);
	}

	/** Parse string to LocalDateTime */
	public static LocalDateTime stringToDateTime(String strDate) {
		return (strDate == null) ? LocalDateTime.now() : LocalDateTime.parse(strDate, DEFAULT_DATE_TIME_FORMAT);
	}

	/** Parse string to LocalDateTime (DB format) */
	public static LocalDateTime stringToDateTimeForDB(String strDate) {
		return (strDate == null) ? LocalDateTime.now() : LocalDateTime.parse(strDate, DATE_TIME_FORMAT_FOR_DB);
	}

	/** Extract only time from LocalDateTime */
	public static String retunStringTiming(LocalDateTime dateTime) {
		return (dateTime == null) ? null : dateTime.format(DEFAULT_TIME_FORMAT);
	}

	/** Difference in hours between given date and now */
	public static long timeDifferrenceInHour(String strDate) {
		LocalDate date = LocalDate.parse(strDate, DEFAULT_DATE_FORMAT);
		return ChronoUnit.HOURS.between(date.atStartOfDay(), LocalDateTime.now());
	}

	/** Check if today is a toll-free date (Sat, Sun, or June) */
	public static boolean checkTollFreeDate() {
		LocalDate today = LocalDate.now();
		DayOfWeek day = today.getDayOfWeek();
		Month month = today.getMonth();

		String tollFreeMonth = "JUNE";

		if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY || month.name().equalsIgnoreCase(tollFreeMonth)) {
			return false; // your original code had TRUE then returned FALSE
		}
		return true;
	}

	/** Get month name by index (1-12) */
	public static String getMonthName(int monthIndex) {
		if (monthIndex < 1 || monthIndex > 12) {
			throw new IllegalArgumentException(monthIndex + " is not a valid month index.");
		}
		return Month.of(monthIndex).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	}

	/** Predefined map of month names */
	public static final Map<Integer, String> MONTH_NAME_MAP = new HashMap<>();
	static {
		for (int i = 1; i <= 12; i++) {
			MONTH_NAME_MAP.put(i, Month.of(i).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
		}
	}

	/** Validate string date format */
	public static void checkStringInputDateFormat(String strDate) {
		try {
			LocalDate.parse(strDate, DEFAULT_DATE_FORMAT);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid date format, expected dd-MM-yyyy", e);
		}
	}
}
