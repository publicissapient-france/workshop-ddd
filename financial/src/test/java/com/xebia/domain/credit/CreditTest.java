package com.xebia.domain.credit;

import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;

import org.junit.Test;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class CreditTest {

    @Test
    public void should_get_active_echeances() {
        //GIVEN
        EcheanceRequest echeanceRequest1 = new EcheanceRequestBuilder().build();
        EcheanceRequest echeanceRequest2 = new EcheanceRequestBuilder().isInactive().build();

        Credit credit = new Credit();
        credit.getEcheanceRequests().add(echeanceRequest1);
        credit.getEcheanceRequests().add(echeanceRequest2);

        //WHEN
        List<EcheanceRequest> echeanceRequestActive = credit.getEcheanceRequestActive();

        //THEN
        assertThat(echeanceRequestActive).hasSize(1);

    }
}
