import decimalutils.DecimalUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestDecimalUtils {

	@Test
	public void testTruncate() {
		double roundUp = 12.3456;
		Assert.assertEquals(12.35, DecimalUtils.truncate(roundUp, 2), 0);

		double roundDown = 31.85121;
		Assert.assertEquals(31.851, DecimalUtils.truncate(roundDown,  3), 0);
	}

	@Test
	public void testCurrencyFormat() {
		double roundUp = 3137.2341;
		Assert.assertEquals("$3,137.23", DecimalUtils.formatCurrency(roundUp));

		double roundDown = 98134.981234;
		Assert.assertEquals("$98,134.98", DecimalUtils.formatCurrency(roundDown));
	}

}
