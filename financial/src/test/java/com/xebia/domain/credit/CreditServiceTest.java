package com.xebia.domain.credit;

import com.google.common.collect.Lists;
import com.xebia.domain.currency.Currency;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;
import com.xebia.port.adapter.service.DataService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditServiceTest {

    private CreditService creditService;

    @Mock
    private DataService dataService;

    @Mock
    private CreditRepository creditRepository;

    @Before
    public void init() throws Exception {
        creditService = new CreditService(dataService, creditRepository);
    }

    @Test
    public void should_be_true_when_contains_funding_currencies() {
        Currency euro = new Currency("EURO", Currency.EUR_ISO);
        Currency us = new Currency("US", Currency.USD_ISO);

        Boolean result = creditService.containsFundingCurrencies(Lists.newArrayList(euro, us));

        assertThat(result).isTrue();
    }

    @Test
    public void should_be_false_when_no_contains_funding_currencies() {
        Currency gpb = new Currency("GPB", "GPB");

        Boolean result = creditService.containsFundingCurrencies(Lists.newArrayList(gpb));

        assertThat(result).isFalse();
    }

    @Test
    public void should_add_echeance_to_product() {
        // Given
        Credit credit = new CreditBuilder().build();

        EcheanceRequest echeance = new EcheanceRequestBuilder().build();

        long productId = 1L;
        when(creditRepository.findOne(productId)).thenReturn(credit);

        // When
        creditService.addEcheanceToCredit(productId, echeance);
        // Then

        assertThat(credit.getEcheanceRequests()).hasSize(1).contains(echeance);
    }

    @Test
    public void should_return_remaining_echeance_after_date() {
        // Given
        Credit credit = new CreditBuilder().build();

        credit.getEcheanceRequests().add(new EcheanceRequest(new DateTime(2014, 5, 1, 0, 0).toDate(), new BigDecimal("1500")));
        credit.getEcheanceRequests().add(new EcheanceRequest(new DateTime(2014, 6, 1, 0, 0).toDate(), new BigDecimal("1000")));
        credit.getEcheanceRequests().add(new EcheanceRequest(new DateTime(2014, 7, 1, 0, 0).toDate(), new BigDecimal("500")));
        credit.getEcheanceRequests().add(new EcheanceRequest(new DateTime(2014, 8, 1, 0, 0).toDate(), new BigDecimal("0")));

        // When
        Integer result = creditService.countRemainingEcheanceAfter(credit, new DateTime(2014, 6, 2, 0, 0).toDate());

        // Then
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void should_apply_cross_change() throws Exception {
        // Given
        Date date = new DateTime(2014, 6, 2, 0, 0).toDate();
        when(dataService.getCrossChange(date)).thenReturn(BigDecimal.valueOf(2));

        // When
        BigDecimal result = creditService.applyCrossChange(BigDecimal.TEN, date);

        // Then
        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(5));

    }
}
