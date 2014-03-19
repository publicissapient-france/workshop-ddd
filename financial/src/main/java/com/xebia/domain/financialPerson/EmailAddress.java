package com.xebia.domain.financialPerson;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.regex.Pattern.matches;

public final class EmailAddress implements Serializable {

  private String mail;

  public EmailAddress(String mail) {
    this.setMail(mail);
  }

  public EmailAddress(EmailAddress emailAddress) {
    this(emailAddress.mail());
  }

  protected EmailAddress() {
  }

  public String mail() {
    return mail;
  }

  private void setMail(String mail) {
    checkArgument(!isNullOrEmpty(mail), "The email is required.");
    checkArgument(matches("\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*", mail), "Email format is invalid.");

    this.mail = mail;
  }
}
