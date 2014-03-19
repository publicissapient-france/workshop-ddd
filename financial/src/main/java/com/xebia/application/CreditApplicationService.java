package com.xebia.application;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.xebia.domain.currency.Currency;
import com.xebia.domain.echeance.CreditDecimal;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;
import com.xebia.domain.credit.Credit;
import com.xebia.domain.credit.CreditRepository;
import com.xebia.port.adapter.service.CreditDataService;

import java.util.Date;
import java.util.List;

@Transactional
public class CreditApplicationService {

    private CreditDataService dataService;

    private CreditRepository creditRepository;

    @Inject
    public CreditApplicationService(CreditDataService dataService, CreditRepository creditRepository) {
        this.dataService = dataService;
        this.creditRepository = creditRepository;
    }

    public List<EcheanceRequest> valuationCredit(Credit credit, Date valueDate) {

        List<EcheanceRequest> echeanceRequestActive = credit.getEcheanceRequestActive();
        List<EcheanceRequest> echeanceRequestValuations = Lists.newArrayList();

        for (EcheanceRequest echeanceRequest : echeanceRequestActive) {
            CreditDecimal creditValuation = echeanceRequest.crd();

            if (containsFundingCurrencies(credit.getCurrencyBook().getCurrencies())) {
                creditValuation = applyCrossChange(creditValuation, valueDate);
            }

            EcheanceRequest echeanceRequestValuation = new EcheanceRequestBuilder().withPaymentDate(echeanceRequest.paymentDate())
                    .withCrd(creditValuation)
                    .build();
            echeanceRequestValuations.add(echeanceRequestValuation);
        }

        return echeanceRequestValuations;
    }

    public void addEcheanceToCredit(Long idProduct, EcheanceRequest echeanceRequest) {
        Credit credit = creditRepository.findOne(idProduct);

        credit.addEcheance(echeanceRequest);
    }

    CreditDecimal applyCrossChange(CreditDecimal value, Date date) {
        CreditDecimal crossChange = dataService.getCrossChange(date);
        return value.divide(crossChange);
    }

    Boolean containsFundingCurrencies(List<Currency> currencies) {
        return Iterables.any(currencies, new Predicate<Currency>() {
            @Override
            public boolean apply(Currency currency) {
                return currency.isFundingCurrency();
            }
        });
    }

    Integer countRemainingEcheanceAfter(Credit credit, final Date date) {
        return Lists.newArrayList(Iterables.filter(credit.getEcheanceRequestActive(), new Predicate<EcheanceRequest>() {
            @Override
            public boolean apply(EcheanceRequest echeanceRequest) {
                return echeanceRequest.paymentDate().after(date);
            }
        })).size();
    }

}
