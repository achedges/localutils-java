package com.adamhedges.utilities.datetime;

import java.time.*;
import java.util.HashMap;

public class DateUtilities {

	private static final HashMap<Timezone, ZoneId> timeZoneIdMap;

    static {
        timeZoneIdMap = new HashMap<>();
        timeZoneIdMap.put(Timezone.Central, ZoneId.of("America/Chicago"));
        timeZoneIdMap.put(Timezone.Eastern, ZoneId.of("America/New_York"));
        timeZoneIdMap.put(Timezone.Universal, ZoneId.of("UTC"));
    }
    
    private DateUtilities() {}

	public static LocalDateTime getDateTime() {
		return LocalDateTime.now();
	}

	public static LocalDateTime getDateTime(Timezone tz) {
		return ZonedDateTime.now(timeZoneIdMap.get(tz)).toLocalDateTime();
	}

	public static LocalDateTime getDateTime(LocalDate date, LocalTime time, Timezone tz) {
		return ZonedDateTime.of(date, time, timeZoneIdMap.get(tz)).toLocalDateTime();
	}

    public static long getInstantDate(Instant instant, Timezone tz) {
        ZonedDateTime dt = ZonedDateTime.ofInstant(instant, timeZoneIdMap.get(tz));
        return (dt.getYear() * 10000L) + (dt.getMonthValue() * 100) + dt.getDayOfMonth();
    }

    public static int getInstantTime(Instant instant, Timezone tz) {
        ZonedDateTime dt = ZonedDateTime.ofInstant(instant, timeZoneIdMap.get(tz));
        return (dt.getHour() * 100) + dt.getMinute();
    }

	public static LocalDateTime getEasternDateTime() {
		return getDateTime(Timezone.Eastern);
	}

	public static LocalDateTime getCentralDateTime() {
		return getDateTime(Timezone.Central);
	}

	public static LocalDateTime getUniversalDateTime() {
		return getDateTime(Timezone.Universal);
	}

	public static LocalDateTime parse(String dateTimeString) {
		return ZonedDateTime.parse(dateTimeString).toLocalDateTime();
	}

	public static LocalDateTime parseToZone(String dateTimeString, Timezone tz) {
		return ZonedDateTime.parse(dateTimeString).withZoneSameInstant(timeZoneIdMap.get(tz)).toLocalDateTime();
	}

}
