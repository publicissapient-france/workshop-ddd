package com.xebia.domain.credit;

import com.google.common.collect.Lists;
import com.xebia.domain.currency.Currency;
import com.xebia.domain.echeance.EcheanceRequest;
import com.xebia.model.Entity;

import java.util.Date;
import java.util.List;

public class Credit extends Entity {

    private String name;

    private String technicalCode;

    private Date marketDate;

    private Date placeDate;

    private List<EcheanceRequest> echeanceRequests = Lists.newArrayList();

    private List<Currency> currencies = Lists.newArrayList();

    public Credit(String name, String technicalCode) {
        this.setName(name);
        this.setTechnicalCode(technicalCode);
    }

    protected Credit() {
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

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
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

    public List<EcheanceRequest> getEcheanceRequests() {
        return echeanceRequests;
    }

    public void setEcheanceRequests(List<EcheanceRequest> echeanceRequests) {
        this.echeanceRequests = echeanceRequests;
    }
}
