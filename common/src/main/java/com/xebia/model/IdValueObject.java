package com.xebia.model;

import java.io.Serializable;

public class IdValueObject implements Serializable {

    private long id;

    public IdValueObject() {
        super();
        this.setId(-1);
    }

    protected long id() {
        return this.id;
    }

    private void setId(long anId) {
        this.id = anId;
    }
}
