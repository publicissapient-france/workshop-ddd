package com.xebia.domain.echeance;

import com.xebia.domain.currency.Currency;
import com.xebia.domain.exception.AmountException;

public class Amount {

    private CreditDecimal value;

    private Currency currency;

    public Amount() {
    }

    public Amount(CreditDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public CreditDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Amount add(Amount amount) throws AmountException {
        if (this.currency.equals(amount.getCurrency())) {
            CreditDecimal newValue = this.getValue().add(amount.getValue());
            return new Amount(newValue, this.getCurrency());
        } else {
            throw new AmountException("cannot add amount with different currencies");
        }

    }
}
