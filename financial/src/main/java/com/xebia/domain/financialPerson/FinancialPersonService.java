package com.xebia.domain.financialPerson;

import com.xebia.user.User;

public interface FinancialPersonService {

    Sales salesFrom(User user);

    Pricer pricerFrom(User user);

    Trader traderFrom(User user);
}
