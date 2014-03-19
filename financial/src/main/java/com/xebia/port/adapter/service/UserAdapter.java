package com.xebia.port.adapter.service;

import com.xebia.domain.financialPerson.EmailAddress;
import com.xebia.domain.financialPerson.FinancialPerson;
import com.xebia.user.User;

import java.lang.reflect.Constructor;

public class UserAdapter {

    public <T extends FinancialPerson> T toFinancialPerson(User user, Class<T> financialPersonClass) {
        T financialPerson;

        try {
            financialPerson = newFinancialPerson(user.getFirstname(), user.getLastname(), new EmailAddress(user.getEmail()), financialPersonClass);
        } catch (Exception e) {
            throw new IllegalStateException("Failed because: " + e.getMessage(), e);
        }

        return financialPerson;
    }

    private <T extends FinancialPerson> T newFinancialPerson(String firstname, String lastname, EmailAddress email, Class<T> financialPersonClass)
            throws Exception {

        Constructor<T> constructor = financialPersonClass.getConstructor(String.class, EmailAddress.class);
        T collaborator = constructor.newInstance((firstname + " " + lastname).trim(), email);

        return collaborator;
    }
}
