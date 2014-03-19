package com.xebia.domain.currency;

import com.google.common.collect.Lists;

import java.util.List;

public class CurrencyBook {

    private List<Currency> currencies = Lists.newArrayList();

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

}
