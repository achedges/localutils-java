package localutils.tests;

import localutils.datetime.DateUtilities;
import localutils.datetime.Timezone;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TestDateUtilities {

	@Test
	public void testCurrentDateDiff() {
		LocalDateTime central = DateUtilities.getDateTime(Timezone.Central);
		LocalDateTime eastern = DateUtilities.getDateTime(Timezone.Eastern);
		Assert.assertEquals(1, eastern.getHour() - central.getHour());
	}

}
