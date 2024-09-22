package com.adamhedges.utilities.datetime;

import java.time.*;
import java.util.HashMap;

public class DateUtilities {

	public static final ZoneId CENTRAL_TIMEZONE = ZoneId.of("America/Chicago");
    public static final ZoneId EASTERN_TIMEZONE = ZoneId.of("America/New_York");
    public static final ZoneId UTC_TIMEZONE = ZoneId.of("UTC");
    
    private DateUtilities() {}

	public static LocalDateTime getDateTime() {
		return LocalDateTime.now();
	}

	public static LocalDateTime getDateTime(ZoneId tz) {
		return ZonedDateTime.now(tz).toLocalDateTime();
	}

	public static LocalDateTime getDateTime(LocalDate date, LocalTime time, ZoneId tz) {
		return ZonedDateTime.of(date, time, tz).toLocalDateTime();
	}

    public static Instant getZonedNowInstant(ZoneId tz) {
        return ZonedDateTime.now(tz).toInstant();
    }

    public static long getInstantDate(Instant instant, ZoneId tz) {
        ZonedDateTime dt = ZonedDateTime.ofInstant(instant, tz);
        return (dt.getYear() * 10000L) + (dt.getMonthValue() * 100) + dt.getDayOfMonth();
    }

    public static int getInstantTime(Instant instant, ZoneId tz) {
        ZonedDateTime dt = ZonedDateTime.ofInstant(instant, tz);
        return (dt.getHour() * 100) + dt.getMinute();
    }

	public static LocalDateTime getEasternDateTime() {
		return getDateTime(EASTERN_TIMEZONE);
	}

	public static LocalDateTime getCentralDateTime() {
		return getDateTime(CENTRAL_TIMEZONE);
	}

	public static LocalDateTime getUniversalDateTime() {
		return getDateTime(UTC_TIMEZONE);
	}

	public static LocalDateTime parse(String dateTimeString) {
		return ZonedDateTime.parse(dateTimeString).toLocalDateTime();
	}

	public static LocalDateTime parseToZone(String dateTimeString, ZoneId tz) {
		return ZonedDateTime.parse(dateTimeString).withZoneSameInstant(tz).toLocalDateTime();
	}

}
