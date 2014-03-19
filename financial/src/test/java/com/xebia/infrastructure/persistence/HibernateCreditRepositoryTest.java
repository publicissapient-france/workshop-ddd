package com.xebia.infrastructure.persistence;

import com.google.inject.Inject;
import com.xebia.AbstractIntegrationTest;
import com.xebia.domain.credit.CreditBuilder;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;
import com.xebia.domain.credit.Credit;
import com.xebia.domain.credit.CreditRepository;
import org.junit.Test;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

public class HibernateCreditRepositoryTest extends AbstractIntegrationTest {

    @Inject
    private CreditRepository creditRepository;

    @Test
    public void testFindOne() {

    }

    @Test
    public void should_save_product() {
        // Given
        EcheanceRequest echeanceRequest = new EcheanceRequestBuilder().withPaymentDate(new Date()).build();
        Credit credit = new CreditBuilder().withName("Save credit test").withMarketDate(new Date()).build();
        credit.addEcheance(echeanceRequest);

        // When
        creditRepository.save(credit);

        // Then
        assertThat(creditRepository.findAll()).isNotEmpty();
    }
}
