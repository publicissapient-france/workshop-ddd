package com.xebia.domain.financialPerson;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.Assertions.assertThat;

public class EmailAddressTest {

  @Rule
  public ExpectedException expected = ExpectedException.none();

  @Test
  public void should_mail_not_null_or_empty() {
    expected.expect(IllegalArgumentException.class);
    expected.expectMessage("The email is required.");

    new EmailAddress("");
  }

  @Test
  public void should_validate_when_mail_has_not_a_valid_format() {
    expected.expect(IllegalArgumentException.class);
    expected.expectMessage("Email format is invalid.");

    new EmailAddress("test");
  }

  @Test
  public void should_mail_has_a_valid_format() {
    EmailAddress emailAddress = new EmailAddress("test@test.com");
    assertThat(emailAddress).isNotNull();
  }
}
