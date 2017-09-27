package com.havryliuk.util;

import java.math.BigDecimal;

public class FormatValidator {
    public static boolean priceIsValid(BigDecimal price) {
        return String.valueOf(price).matches("\\d*(.\\d{1,2})*");
    }
}
