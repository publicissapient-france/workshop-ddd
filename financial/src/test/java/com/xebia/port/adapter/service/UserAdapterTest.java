package com.xebia.port.adapter.service;

import com.xebia.domain.financialPerson.Pricer;
import com.xebia.domain.financialPerson.Sales;
import com.xebia.domain.financialPerson.Trader;
import com.xebia.user.User;
import com.xebia.user.UserBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UserAdapterTest {

    private UserAdapter userAdapter;

    @Before
    public void setUp() throws Exception {
        userAdapter = new UserAdapter();
    }

    @Test
    public void should_create_sales() throws Exception {
        User user = new UserBuilder().firstname("sales").lastname("sales").email("salesMail@test.fr").build();

        Sales sales = userAdapter.toFinancialPerson(user, Sales.class);
        assertThat(sales).isNotNull();
    }

    @Test
    public void should_create_pricer() throws Exception {
        User user = new UserBuilder().firstname("pricer").lastname("pricer").email("pricerMail@test.fr").build();

        Pricer pricer = userAdapter.toFinancialPerson(user, Pricer.class);
        assertThat(pricer).isNotNull();
    }

    @Test
    public void should_create_trader() throws Exception {
        User user = new UserBuilder().firstname("trader").lastname("trader").email("traderMail@test.fr").build();

        Trader trader = userAdapter.toFinancialPerson(user, Trader.class);
        assertThat(trader).isNotNull();
    }
}
