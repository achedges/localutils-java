package com.adamhedges.utilities;

import org.junit.Assert;
import org.junit.Test;

import com.adamhedges.utilities.datetime.DateUtilities;
import com.adamhedges.utilities.datetime.Timezone;

import java.time.LocalDateTime;

public class TestDateUtilities {

	@Test
	public void testCurrentDateDiff() {
		LocalDateTime central = DateUtilities.getDateTime(Timezone.Central);
		LocalDateTime eastern = DateUtilities.getDateTime(Timezone.Eastern);
		Assert.assertEquals(1, eastern.getHour() - central.getHour());
	}

}
