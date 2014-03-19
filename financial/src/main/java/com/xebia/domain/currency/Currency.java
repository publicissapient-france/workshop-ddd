package com.xebia.domain.currency;

import com.xebia.model.IdValueObject;

public class Currency extends IdValueObject {

    public static final String EUR_ISO = "EUR";

    public static final String USD_ISO = "USD";

    private String name;

    private String isoCode;

    protected Currency() {
        super();
    }

    public Currency(String name, String isoCode) {
        this.name = name;
        this.isoCode = isoCode;
    }

    public String name() {
        return name;
    }

    public String isoCode() {
        return isoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency)) return false;

        Currency currency = (Currency) o;

        if (!isoCode.equals(currency.isoCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return isoCode.hashCode();
    }

    public boolean isFundingCurrency() {
        return EUR_ISO.equals(this.isoCode) || USD_ISO.equals(this.isoCode);
    }
}
