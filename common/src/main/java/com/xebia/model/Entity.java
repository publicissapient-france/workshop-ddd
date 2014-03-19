package com.xebia.model;

import java.io.Serializable;

public class Entity implements Serializable {

    private long id;

    protected Entity() {
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
