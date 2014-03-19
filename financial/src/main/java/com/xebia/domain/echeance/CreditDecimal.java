package com.xebia.domain.echeance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

public class CreditDecimal implements Serializable {

    public static final CreditDecimal ZERO = new CreditDecimal(BigDecimal.ZERO);
    public static final CreditDecimal ONE = new CreditDecimal(BigDecimal.ONE);
    public static final CreditDecimal TEN = new CreditDecimal(BigDecimal.TEN);

    private BigDecimal value;

    public CreditDecimal(BigDecimal value) {
        this.value = value;
    }

    public CreditDecimal add(CreditDecimal number) {
        if (number != null) {
            return new CreditDecimal(this.getValue().add(number.getValue(), MathContext.DECIMAL64));
        }
        return CreditDecimal.ZERO;
    }

    public CreditDecimal substract(CreditDecimal number) {
        if (number != null) {
            return new CreditDecimal(this.getValue().subtract(number.getValue(), MathContext.DECIMAL64));
        }
        return CreditDecimal.ZERO;
    }

    public CreditDecimal multiply(CreditDecimal number) {
        if (number != null) {
            return new CreditDecimal(this.getValue().multiply(number.getValue(), MathContext.DECIMAL64));
        }
        return CreditDecimal.ZERO;
    }

    public CreditDecimal divide(CreditDecimal number) {
        if (number != null || this.compareTo(number) != 0) {
            return new CreditDecimal(this.getValue().divide(number.getValue(), MathContext.DECIMAL64));
        }
        return CreditDecimal.ZERO;
    }

    public BigDecimal getValue() {
        return value;
    }

    public int compareTo(CreditDecimal number) {
        if (number != null) {
            return this.getValue().compareTo(number.getValue());
        }
        return -1;
    }
}
