package com.xebia.domain.credit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CreditIdTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void should_not_validate_id_when_id_null() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The id has an invalid format.");

        new CreditId(null, "code");
    }

    @Test
    public void should_not_validate_id_when_bad_uuid_format() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("The id has an invalid format.");

        new CreditId("test", "code");
    }

    @Test
    public void should_not_set_null_reference_code() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Reference code cannot be null or empty");

        new CreditId("1-2-3-4-5", null);
    }
}
