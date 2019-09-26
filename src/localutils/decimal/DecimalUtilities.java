package localutils.decimal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class DecimalUtilities {

	public static double truncate(double input, int scale) {
		return BigDecimal.valueOf(input).setScale(scale, RoundingMode.HALF_UP).doubleValue();
	}

	public static String formatCurrency(double input) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(input);
	}

}
