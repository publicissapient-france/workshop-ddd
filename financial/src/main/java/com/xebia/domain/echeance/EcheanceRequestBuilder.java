package com.xebia.domain.echeance;

import java.util.Date;

public class EcheanceRequestBuilder {

    private EcheanceRequest echeanceRequest;

    public EcheanceRequestBuilder() {
        this.echeanceRequest = new EcheanceRequest();
        this.echeanceRequest.setActive(true);
    }

    public EcheanceRequest build() {
        return echeanceRequest;
    }

    public EcheanceRequestBuilder withPaymentDate(Date paymentDate) {
        echeanceRequest.setPaymentDate(paymentDate);
        return this;
    }

    public EcheanceRequestBuilder withCrd(CreditDecimal crd) {
        echeanceRequest.setCrd(crd);
        return this;
    }

    public EcheanceRequestBuilder isInactive() {
        echeanceRequest.setActive(false);
        return this;
    }
}
