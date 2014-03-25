package com.xebia.domain.credit;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.xebia.domain.currency.Currency;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBuilder;
import com.xebia.port.adapter.service.DataService;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Transactional
public class CreditService {

    private DataService dataService;

    private CreditRepository creditRepository;

    @Inject
    public CreditService(DataService dataService, CreditRepository creditRepository) {
        this.dataService = dataService;
        this.creditRepository = creditRepository;
    }

    public List<EcheanceRequest> generateRepaymentSchedule(Credit credit, BigDecimal creditFund, BigDecimal interestRate, int monthTimeDuration) {
        BigDecimal monthlyPayment = computeMonthlyPayment(creditFund, interestRate, monthTimeDuration);
        BigDecimal remainingCredit = creditFund;

        for (int i = 0; i < monthTimeDuration; i++) {
            BigDecimal interest = remainingCredit.multiply(interestRate.divide(new BigDecimal(monthTimeDuration), MathContext.DECIMAL32)).setScale(2, RoundingMode.HALF_EVEN);

            remainingCredit = remainingCredit.subtract(monthlyPayment.subtract(interest)).setScale(2, RoundingMode.HALF_EVEN);

            credit.getEcheanceRequests().add(new EcheanceRequest(null, remainingCredit.setScale(2, RoundingMode.HALF_EVEN).max(BigDecimal.ZERO)));
        }

        return credit.getEcheanceRequests();
    }

    BigDecimal computeMonthlyPayment(BigDecimal creditFund, BigDecimal interestRate, int monthDuration) {
        MathContext mc = MathContext.DECIMAL32;

        BigDecimal result = creditFund.multiply(interestRate, mc)
                .divide(new BigDecimal("12"), mc)
                .divide(BigDecimal.ONE.subtract(BigDecimal.ONE.add(interestRate.divide(new BigDecimal("12"), mc)).pow(-monthDuration, mc), mc), mc);

        return result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public List<EcheanceRequest> valuationCredit(Credit credit, Date valueDate) {
        List<EcheanceRequest> echeanceRequestActive = credit.getEcheanceRequests();
        List<EcheanceRequest> echeanceRequestValuations = Lists.newArrayList();

        for (EcheanceRequest echeanceRequest : echeanceRequestActive) {
            BigDecimal creditValuation = echeanceRequest.crd();

            if (!containsFundingCurrencies(credit.getCurrencies())) {
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

        credit.getEcheanceRequests().add(echeanceRequest);
    }

    BigDecimal applyCrossChange(BigDecimal value, Date date) {
        BigDecimal crossChange = dataService.getCrossChange(date);
        if (value != null) {
            return value.divide(crossChange);
        }
        else {
            return BigDecimal.ZERO;
        }
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
        return Lists.newArrayList(Iterables.filter(credit.getEcheanceRequests(), new Predicate<EcheanceRequest>() {
            @Override
            public boolean apply(EcheanceRequest echeanceRequest) {
                return echeanceRequest.paymentDate().after(date);
            }
        })).size();
    }

}
