package com.xebia.domain.financialPerson;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class FinancialPerson {

  private String name;
  private EmailAddress email;

  protected FinancialPerson() {
  }

  public FinancialPerson(String name, EmailAddress email) {
    this.name = name;
    this.email = email;
  }

  public String lastname() {
    return name;
  }

  public EmailAddress getEmail() {
    return email;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setEmail(EmailAddress email) {
    checkArgument(email != null, "The email is required.");
    this.email = email;
  }
}
