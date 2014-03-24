package com.xebia.domain.currency;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CurrencyTest {

    @Test
    public void should_validate_euro_as_funding_currency() throws Exception {
        // Given
        Currency euro = new Currency("EURO", Currency.EUR_ISO);

        // When / Then
        assertThat(euro.isFundingCurrency()).isTrue();
    }

    @Test
    public void should_validate_usd_as_funding_currency() throws Exception {
        // Given
        Currency dollar = new Currency("DOLLAR", Currency.USD_ISO);

        // When / Then
        assertThat(dollar.isFundingCurrency()).isTrue();
    }

    @Test
    public void should_validate_other_currency_is_not_funding() throws Exception {
        // Given
        Currency yen = new Currency("YEN", "YEN");

        // When / Then
        assertThat(yen.isFundingCurrency()).isFalse();
    }
}
