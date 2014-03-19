package com.xebia.domain.financialPerson;

public final class Trader extends FinancialPerson {

    protected Trader() {
    }

    public Trader(String name, EmailAddress email) {
        super(name, email);
    }
}
