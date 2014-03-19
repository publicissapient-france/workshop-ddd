package com.xebia.domain.credit;

import com.xebia.domain.currency.Currency;
import com.xebia.domain.currency.CurrencyBook;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.domain.echeance.EcheanceRequestBook;
import com.xebia.model.Entity;

import java.util.Date;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Credit extends Entity {

    private CreditId creditId;

    private String name;

    private String technicalCode;

    private Date marketDate;

    private Date placeDate;

    private EcheanceRequestBook echeanceRequestBook = new EcheanceRequestBook();

    private CurrencyBook currencyBook = new CurrencyBook();

    public Credit(CreditId creditId, String name, String technicalCode) {
        this.setCreditId(creditId);
        this.setName(name);
        this.setTechnicalCode(technicalCode);
    }

    protected Credit() {
    }

    public CreditId getCreditId() {
        return creditId;
    }

    public void setCreditId(CreditId creditId) {
        checkArgument(creditId != null, "CreditId is required");
        this.creditId = creditId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnicalCode() {
        return technicalCode;
    }

    public void setTechnicalCode(String technicalCode) {
        this.technicalCode = technicalCode;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public void setMarketDate(Date marketDate) {
        this.marketDate = marketDate;
    }

    public Date getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(Date placeDate) {
        this.placeDate = placeDate;
    }

    public void addEcheance(EcheanceRequest echeanceRequest) {
        this.echeanceRequestBook.getEcheanceRequests().add(echeanceRequest);
    }

    public CurrencyBook getCurrencyBook() {
        return currencyBook;
    }

    public void addCurrency(Currency currency) {
        this.currencyBook.getCurrencies().add(currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credit)) return false;

        Credit credit = (Credit) o;

        if (!marketDate.equals(credit.marketDate)) return false;
        if (!name.equals(credit.name)) return false;
        if (!placeDate.equals(credit.placeDate)) return false;
        if (!technicalCode.equals(credit.technicalCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + technicalCode.hashCode();
        result = 31 * result + marketDate.hashCode();
        result = 31 * result + placeDate.hashCode();
        return result;
    }

    public List<EcheanceRequest> getEcheanceRequestActive() {
        return this.echeanceRequestBook.getEcheanceRequestActive();
    }

}
