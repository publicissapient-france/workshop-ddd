package com.xebia.application;

import com.google.common.collect.Lists;
import com.xebia.domain.credit.CreditBuilder;
import com.xebia.domain.currency.Currency;
import com.xebia.domain.echeance.CreditDecimal;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;
import com.xebia.domain.credit.Credit;
import com.xebia.domain.credit.CreditRepository;
import com.xebia.port.adapter.service.CreditDataService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditApplicationServiceTest {

    private CreditApplicationService productService;

    @Mock
    private CreditDataService dataService;

    @Mock
    private CreditRepository creditRepository;

    @Before
    public void init() throws Exception {
        productService = new CreditApplicationService(dataService, creditRepository);
    }

    @Test
    public void should_be_true_when_contains_funding_currencies() {
        Currency euro = new Currency("EURO", Currency.EUR_ISO);
        Currency us = new Currency("US", Currency.USD_ISO);

        Boolean result = productService.containsFundingCurrencies(Lists.newArrayList(euro, us));

        assertThat(result).isTrue();
    }

    @Test
    public void should_be_false_when_no_contains_funding_currencies() {
        Currency gpb = new Currency("GPB", "GPB");

        Boolean result = productService.containsFundingCurrencies(Lists.newArrayList(gpb));

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
        productService.addEcheanceToCredit(productId, echeance);
        // Then

        assertThat(credit.getEcheanceRequestActive()).hasSize(1).contains(echeance);
    }

    @Test
    public void should_return_remaining_echeance_after_date() {
        // Given
        Credit credit = new CreditBuilder().build();

        credit.addEcheance(new EcheanceRequest(new DateTime(2014, 5, 1, 0, 0).toDate(), new CreditDecimal(new BigDecimal("1500"))));
        credit.addEcheance(new EcheanceRequest(new DateTime(2014, 6, 1, 0, 0).toDate(), new CreditDecimal(new BigDecimal("1000"))));
        credit.addEcheance(new EcheanceRequest(new DateTime(2014, 7, 1, 0, 0).toDate(), new CreditDecimal(new BigDecimal("500"))));
        credit.addEcheance(new EcheanceRequest(new DateTime(2014, 8, 1, 0, 0).toDate(), new CreditDecimal(new BigDecimal("0"))));

        // When
        Integer result = productService.countRemainingEcheanceAfter(credit, new DateTime(2014, 6, 2, 0, 0).toDate());

        // Then
        assertThat(result).isEqualTo(2);
    }

}
