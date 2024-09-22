package com.adamhedges.utilities;

import com.adamhedges.utilities.decimal.Comparisons;
import com.adamhedges.utilities.decimal.DecimalUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TestDecimalUtilities {

	@Test
	public void testTruncate() {
		double roundUp = 12.3456;
		Assert.assertEquals(12.35, DecimalUtilities.truncate(roundUp, 2), 0);

		double roundDown = 31.85121;
		Assert.assertEquals(31.851, DecimalUtilities.truncate(roundDown,  3), 0);
	}

	@Test
	public void testCurrencyFormat() {
		double roundUp = 3137.2341;
		Assert.assertEquals("$3,137.23", DecimalUtilities.formatCurrency(roundUp));

		double roundDown = 98134.981234;
		Assert.assertEquals("$98,134.98", DecimalUtilities.formatCurrency(roundDown));
	}

    @Test
    public void TestComparisons_BigDecimal_inequality() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(2);

        Assert.assertTrue(Comparisons.lt(a, b));
        Assert.assertFalse(Comparisons.gt(a, b));

        Assert.assertFalse(Comparisons.lt(b, a));
        Assert.assertTrue(Comparisons.gt(b, a));

        Assert.assertTrue(Comparisons.lte(a, b));
        Assert.assertFalse(Comparisons.gte(a, b));

        Assert.assertFalse(Comparisons.lte(b, a));
        Assert.assertTrue(Comparisons.gte(b, a));

        Assert.assertFalse(Comparisons.eq(a, b));
        Assert.assertTrue(Comparisons.neq(a, b));

    }

    @Test
    public void TestComparisons_BigDecimal_equality() {
        BigDecimal a = BigDecimal.valueOf(1);
        BigDecimal b = BigDecimal.valueOf(1);

        Assert.assertFalse(Comparisons.lt(a, b));
        Assert.assertFalse(Comparisons.lt(b, a));

        Assert.assertFalse(Comparisons.gt(a, b));
        Assert.assertFalse(Comparisons.gt(b, a));

        Assert.assertFalse(Comparisons.neq(a, b));

        Assert.assertTrue(Comparisons.eq(a, b));
        Assert.assertTrue(Comparisons.lte(a, b));
        Assert.assertTrue(Comparisons.gte(a, b));
    }

}
