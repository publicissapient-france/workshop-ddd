package com.xebia.port.adapter.service;

import com.xebia.domain.financialPerson.FinancialPersonService;
import com.xebia.domain.financialPerson.Pricer;
import com.xebia.domain.financialPerson.Sales;
import com.xebia.domain.financialPerson.Trader;
import com.xebia.user.User;

public class TranslatingFinancialPersonService implements FinancialPersonService {

    private UserAdapter userAdapter;

    public TranslatingFinancialPersonService(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public Sales salesFrom(User user) {
        return userAdapter.toFinancialPerson(user, Sales.class);
    }

    @Override
    public Pricer pricerFrom(User user) {
        return userAdapter.toFinancialPerson(user, Pricer.class);
    }

    @Override
    public Trader traderFrom(User user) {
        return userAdapter.toFinancialPerson(user, Trader.class);
    }
}
