package com.xebia.domain.credit;

import java.util.Date;

public class CreditBuilder {

    private Credit credit;

    public CreditBuilder() {
        this.credit = new Credit();
    }

    public Credit build() {
        return credit;
    }

    public CreditBuilder withName(String name) {
        credit.setName(name);
        return this;
    }

    public CreditBuilder withMarketDate(Date marketDate) {
      credit.setMarketDate(marketDate);
      return this;
    }
}
