package com.xebia.domain.echeance;


import com.xebia.model.IdValueObject;

import java.math.BigDecimal;
import java.util.Date;

public class EcheanceRequest extends IdValueObject {

    private Date paymentDate;

    private BigDecimal crd;

    private boolean active = true;

    protected EcheanceRequest() {
        super();
    }

    public EcheanceRequest(Date paymentDate, BigDecimal crd) {
        this.paymentDate = paymentDate;
        this.setCrd(crd);
    }

    public BigDecimal crd() {
        return crd;
    }

    public boolean active() {
        return active;
    }

    public Date paymentDate() {
        return paymentDate;
    }

    protected void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    protected void setCrd(BigDecimal crd) {
        this.crd = crd;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }

    public void disable() {
        this.setActive(false);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EcheanceRequest)) return false;

        EcheanceRequest that = (EcheanceRequest) o;

        if (id() != that.id()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id() ^ (id() >>> 32));
    }
}
