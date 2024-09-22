package com.adamhedges.utilities.decimal;

import java.math.BigDecimal;

public class Comparisons {

    public static boolean gt(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) > 0;
    }

    public static boolean gte(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) >= 0;
    }

    public static boolean lt(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) < 0;
    }

    public static boolean lte(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) <= 0;
    }

    public static boolean eq(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) == 0;
    }

    public static boolean neq(BigDecimal left, BigDecimal right) {
        return left.compareTo(right) != 0;
    }

}
