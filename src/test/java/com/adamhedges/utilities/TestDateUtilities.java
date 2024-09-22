package com.adamhedges.utilities;

import org.junit.Assert;
import org.junit.Test;

import com.adamhedges.utilities.datetime.DateUtilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TestDateUtilities {

	@Test
	public void testCurrentDateDiff() {
		LocalDateTime central = DateUtilities.getDateTime(DateUtilities.CENTRAL_TIMEZONE);
		LocalDateTime eastern = DateUtilities.getDateTime(DateUtilities.EASTERN_TIMEZONE);
		Assert.assertEquals(1, eastern.getHour() - central.getHour());
	}

    @Test
    public void TestDateTimeHelpers_getInstantTime() {
        ZonedDateTime dt = ZonedDateTime.of(2024, 1, 26, 8, 30, 0, 0, ZoneId.of("America/Chicago"));
        Assert.assertEquals(930, DateUtilities.getInstantTime(dt.toInstant(), DateUtilities.EASTERN_TIMEZONE));
    }

    @Test
    public void TestDateTimeHelpers_getInstantDate() {
        ZonedDateTime dt = ZonedDateTime.of(2024, 1, 26, 8, 30, 0, 0, ZoneId.of("America/Chicago"));
        Assert.assertEquals(20240126, DateUtilities.getInstantDate(dt.toInstant(), DateUtilities.EASTERN_TIMEZONE));
    }

}
