package com.xebia.port.adapter.service;

import com.google.inject.Inject;
import com.xebia.domain.echeance.CreditDecimal;

import java.math.BigDecimal;
import java.util.Date;

// Couche anti corruption de DataService
public class CreditDataService {

    private DataService dataService;

    @Inject
    public CreditDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public CreditDecimal getCrossChange(Date date) {
        BigDecimal crossChange = dataService.getCrossChange(date);
        return new CreditDecimal(crossChange);
    }

}
