package com.xebia.domain.echeance;

import org.junit.Test;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;

public class CreditDecimalTest {

    @Test
    public void should_return_zero_when_adding_null_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.add(null);
        assertThat(result).isEqualTo(CreditDecimal.ZERO);
    }

    @Test
    public void should_add_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.add(CreditDecimal.ONE);
        assertThat(result.getValue()).isEqualByComparingTo(new BigDecimal("11"));
    }

    @Test
    public void should_return_zero_when_sustracting_null_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.substract(null);
        assertThat(result).isEqualTo(CreditDecimal.ZERO);
    }

    @Test
    public void should_substract_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.substract(CreditDecimal.ONE);
        assertThat(result.getValue()).isEqualByComparingTo(new BigDecimal("9"));
    }

    @Test
    public void should_return_zero_when_multiplying_null_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.multiply(null);
        assertThat(result).isEqualTo(CreditDecimal.ZERO);
    }

    @Test
    public void should_multiply_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.multiply(CreditDecimal.ONE);
        assertThat(result.getValue()).isEqualByComparingTo(BigDecimal.TEN);
    }

    @Test
    public void should_return_zero_when_dividing_null_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.divide(null);
        assertThat(result).isEqualTo(CreditDecimal.ZERO);
    }

    @Test
    public void should_return_zero_when_dividing_with_zero_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.divide(CreditDecimal.ZERO);
        assertThat(result).isEqualTo(CreditDecimal.ZERO);
    }

    @Test
    public void should_divide_credit_decimal() throws Exception {
        CreditDecimal result = CreditDecimal.TEN.multiply(CreditDecimal.ONE);
        assertThat(result.getValue()).isEqualByComparingTo(BigDecimal.TEN);
    }
}
